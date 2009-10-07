require "rexml/document"
require "relation"
require "pp"

class  XmlExchangeParser < REXML::Document

	def initialize(exchange_type, source = nil, context = {} )
		exchange_field=[:data,:schema]
		super(source,context)
		raise ArgumentError,"il primo parametro deve essere uno dei seguenti: :data, :schema" if !exchange_field.member? exchange_type
		@exchange=exchange_type
		raise ArgumentError,"si è richiesto un #{@exchange.to_s.capitalize}exchange e si è passato un file che non lo contiene" if REXML::XPath.match(self,"/#{@exchange.to_s}Exchange")==[]
		
	end
	
  def is_exchange?
    REXML::XPath.match(self,"/#{@exchange}Exchange")!=[]
  end
  
  def get_target
    REXML::XPath.match(self,"/schemaExchange/target")
  end
  
  def source_relation_number
    REXML::XPath.match(self,"count(/#{@exchange}Exchange/source/relation)")
  end
  
  def each_relation
    relation=[]
    REXML::XPath.each(self,"/#{@exchange}Exchange/source/relation") { |cur_rel|
        re_temp=Relation.new(cur_rel.attributes['name'])
        re_temp.nkey = REXML::XPath.match(cur_rel,"count(./key)")[0]
        re_temp.natt = REXML::XPath.match(cur_rel,"count(./attribute)")[0]
        re_temp.nfkey = REXML::XPath.match(cur_rel,"count(./fkey)")[0]
        re_temp.ninarcs = REXML::XPath.match(self,"count(/schemaExchange/source//fkey[./refRelName='#{cur_rel.attributes['name']}']/refRelName)")[0]
       REXML::XPath.each(cur_rel,"./fkey") { |fkey|
            REXML::XPath.each(fkey,"./refRelName/text()") { |refname|
              re_temp.add_fkey(fkey.attributes['name'],refname.to_s)
            }
        }
            
        REXML::XPath.each(cur_rel,"./key") { |key|
              REXML::XPath.each(key,"./@constant") { |constant|   
                re_temp.add_constant(:k,constant.to_s)
              }
        }
        
        REXML::XPath.each(cur_rel,"./attribute") { |attribute|
              REXML::XPath.each(attribute,"./@constant") { |constant|   
                re_temp.add_constant(:a,constant.to_s)
              }
        }
        
        yield re_temp
    }
  end
  
end

if __FILE__ == $0
	  file = File.new "../testPapotti/company1.xml"
  	  doc = XmlExchangeParser.new :schema,file
  	  doc.each_relation{ |variable| puts variable.identify }
end
