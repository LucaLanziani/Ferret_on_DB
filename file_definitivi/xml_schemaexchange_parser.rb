require "rexml/document"
require "relation"
require "pp"

class  XMLSchemaexchangeParser < REXML::Document
  def get_target
    REXML::XPath.match(self,"/schemaExchange/target")
  end
  
  def source_relation_number
    REXML::XPath.match(self,"count(/schemaExchange/source/relation)")
  end
  
  def each_relation
    relation=[]
    REXML::XPath.each(self,"/schemaExchange/source/relation") { |cur_rel|
        re_temp=Relation.new(cur_rel.attributes['name'])
        re_temp.nkey = REXML::XPath.match(cur_rel,"count(./key)")[0]
        re_temp.natt = REXML::XPath.match(cur_rel,"count(./attribute)")[0]
        re_temp.nfkey = REXML::XPath.match(cur_rel,"count(./fkey)")[0]
        re_temp.ninarcs = REXML::XPath.match(self,"count(/schemaExchange/source//fkey[./refRelName='#{cur_rel.attributes['name']}']/refRelName)")[0]
       REXML::XPath.each(cur_rel,"./fkey") { |fkey|
            REXML::XPath.each(fkey,"./refRelName/text()") { |refname|
              re_temp.add_fkey(fkey.attributes['name'],refname)
            }
        }
            
        REXML::XPath.each(cur_rel,"./key") { |key|
              REXML::XPath.each(key,"./@constant") { |constant|   
                re_temp.add_constant(:k,constant)
              }
        }
        
        REXML::XPath.each(cur_rel,"./attribute") { |attribute|
              REXML::XPath.each(attribute,"./@constant") { |constant|   
                re_temp.add_constant(:a,constant)
              }
        }
        
        yield re_temp
    }
  end
  
end

if __FILE__==$0
  file = File.new "../testPapotti/company1.xml"
  doc = XMLSchemaexchangeParser.new file
  doc.each_relation{ |variable| puts variable.identify }
end