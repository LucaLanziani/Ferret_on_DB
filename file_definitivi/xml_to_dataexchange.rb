require "data_exchange"
require "xml_exchange_parser"
=begin
  restituisce un dataexchange raccogliendo le informazioni da un file xml
=end 
class XmlToDataexchange
  attr_reader :xmlfile_name
  attr_reader :dataexchange
  def initialize(xmlfile_name=nil)
    @xmlfile_name=xmlfile_name
    @dataexchange=nil
  end
  
  def parse_file(xmlfile_name=nil)
      raise ArgumentError,"non è stato specificato ancora nessun file da parsare" if @xmlfile_name==nil and xmlfile_name==nil
      @xmlfile_name = xmlfile_name if xmlfile_name!=nil
      begin
          file = File.new @xmlfile_name
          data=create_dataexchange(@xmlfile_name,file) 
          @dataexchange = data if data!=nil
      rescue Exception => e
          puts e
      end
  end
  
  
  private
  
  def create_dataexchange(file_name,file)
    doc = XmlExchangeParser.new :data,file
    if doc.is_exchange?
    then  
      de=DataExchange.new(file_name,doc.get_target)
      doc.each_relation { |relation|  de.add_to_source(relation) }
      de
    end
  end
  
end

if __FILE__==$0
  xtd = XmlToDataexchange.new("../testPapotti/S1.xml")
  xtd.parse_file
  puts xtd.dataexchange.toString
end