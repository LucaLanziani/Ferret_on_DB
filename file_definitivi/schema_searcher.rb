require "pp"
require "xml_to_dataexchange"

class SchemaSearcher

  def initialize()
    @xtd=XmlToDataexchange.new
    @dataexchange=nil
  end
  
  def from_file(file)
    @xtd.parse_file(file)
    @dataexchange=@xtd.dataexchange
  end
  
  def create_query
    @dataexchange.toString
  end
  
end

if __FILE__ == $0
  ss=SchemaSearcher.new
  ss.from_file("../testPapotti/S1.xml")
  ss.create_query
end