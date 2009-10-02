require "rexml/document"
require "pp"

class SchemaExchange
  attr_accessor :source,:target
  def initialize(source={},target=nil)
    @source=source
    @target=target
  end
  
  def add_source(*relation)
    relation.each { |rel| @source[rel.name]=rel }
    
  end
  
  def add_target(*relation)
    relation.each { |rel| @target[rel.name]=rel }
  end
  
  def each_fkey
    @source.each_pair { |key, rel|
      rel.fkey.each_pair { |key, val|
              val.each { |fkey| yield "#{rel.identify}-#{source[fkey].identify}" }
          }  
    }
  end

end

class Relation
=begin
  una relazione viene modellata attraverso:
  * nome
  * numero degli attributi
  * numero delle chiavi
  * numero delle chiavi esterne
  * un hash che come chiave ha il nome della chiave esterna e come valore una lista dei nomi delle relazioni a cui quella chiave si riferisce
      es: {"fk1"=>["r1","r2"],"fk2"=>["r3","r4"]}
  * una hash delle costanti reggruppate secondo il tipo (attributo, chiave, chiave esterna)
      es: {:a=>["nome","cognome"],:k=>["id"],:f=>[]}
=end
  attr_accessor :natt, :nkey, :nfkey, :name
  attr_accessor :fkey,:constant
  def initialize(name,natt=0,nkey=0,nfkey=0,fkey={})
    @name,@natt,@nkey,@nfkey=name,natt,nkey,nfkey
    @fkey=fkey
    @constant={:a=>[],:k=>[],:f=>[]}
  end
  
  def each_constants
    @constant.each_pair { |key, val| 
      val.each { |e| yield "#{identify}#{key.to_s.upcase}<#{e}>"}
    }
  end
  
  def identify
    "<#{"a"*@natt}-#{"k"*@nkey}-#{"f"*@nfkey}>"
  end

  def add_fkey(key,value)
    @fkey[key.to_sym] << value 
  end
  
  def add_constant(key,value)
    @constant[key]<<value
  end
  
  def nfkey
    raise RuntimeError,"inconsistence" if @nfkey!=@fkey.size 
    @nfkey
  end
  
end

class SchemaExchangeXMLParser
  
  def parse_dir()
  end
  
  def parse_files(*files)
      files.each { |file_name|  
        begin
          
          file = File.new file_name
        
        rescue Exception => e
          puts e
        end
      }
  end
  
  private
  
  def parse_xml(file)
    doc = DocParser.new file
    se=SchemaExchange.new({},doc.get_target)
  end
  
end

class DocParser < REXML::Document
  def get_target
    REXML::XPath.match(self,"/schemaExchange/target")
  end
  
  def source_relation_number
    REXML::XPath.match(self,"count(/schemaExchange/source/relation)")
  end
  
  def relation_descriptor
    relation=[]
    REXML::XPath.each(self,"/schemaExchange/source/relation") { |cur_rel|
        temp=Relation.new(cur_rel.attributes['name'])
        temp.natt = cur_ele.elements['attribute'].size
        temp.nkey = cur_ele.elements['key'].size
        temp.nfkey = cur_ele.elements['fkey'].size
=begin
  DEVO INIZIALIZZARE LE CHIAVI ESTERNE E LE COSTANTI 
=end        
        cur_ele.elements['fkey'].each { |fkey|
            fkey.elements[]
        }
    }
  end
  
end

r1=Relation.new("r1",5,2,1)
puts r1.identify

file= File.new "testPapotti/company1.xml"

doc = DocParser.new file

REXML::XPath.each(doc,"/schemaExchange/source/relation") { |rel|
  pp rel.attributes['name']
  pp rel.elements['attribute']
}