require "rexml/document"
require "schema_exchange"
require "xml_exchange_parser"
require "relation"

class XmlToSchemaexchange
  attr_reader :schemaexchanges
  def initialize()
    @schemaexchanges=[]
  end
  
  def parse_dir(dir_absolute_path)
    dir = Dir.new(dir_absolute_path)
    dir.each { |e| if /.*\.xml/=~e
                    then
                      file_path=dir.path+"/"+e
                      parse_files(file_path)
                    end 
              }
  end
  
  def parse_files(*files)
      files.each { |file_name|  
        begin
          
          file = File.new file_name
          schema=create_schemaexchange(file_name,file) 
          schemaexchanges << schema if schema!=nil
        rescue Exception => e
          puts e
        end
      }
  end
  
  def each_schemaexchange()
    @schemaexchanges.each{ |schema|  
      yield schema
    }
  end
  
  private
  
  def create_schemaexchange(file_name,file)
    doc = XmlExchangeParser.new :schema,file
    if doc.is_exchange?
    then  
      se=SchemaExchange.new(file_name,doc.get_target)
      doc.each_relation { |relation|  se.add_to_source(relation) }
      se
    end
  end
end

if __FILE__ == $0
  	xtoe = XmlToSchemaexchange.new
#  	xtoe.parse_files("../testPapotti/company1.xml","../testPapotti/company2.xml","../testPapotti/company3.xml")
	xtoe.parse_dir("/home/fefe/Desktop/Ferret_on_DB/testPapotti")
=begin
	xtoe.schemaexchanges[0].source.each { |value|
		puts "#{value.identify}"
	}
=end
  	xtoe.each_schemaexchange{ |schema| puts schema.toString  

	schema.each_ref_cod { |element| puts element }
	}

end
