require "rexml/document"
require "schema_exchange"
require "xml_schemaexchange_parser"
require "relation"

class SchemaExchangeXMLParser
  
  def parse_dir()
  end
  
  def parse_files(*files)
      files.each { |file_name|  
        begin
          
          file = File.new file_name
          puts "\nSchemaexchange #{file_name}"
          parse_xml(file)
        rescue Exception => e
          puts e
        end
      }
  end
  
  private
  
  def parse_xml(file)
    doc = XMLSchemaexchangeParser.new file
    
    se=SchemaExchange.new({},doc.get_target)
    doc.each_relation { |relation|  pp relation.fkey.values.flatten }
  end
  
end
