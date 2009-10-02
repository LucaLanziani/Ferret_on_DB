require "pp"







sep = SchemaExchangeXMLParser.new
sep.parse_files("./testPapotti/company1.xml","./testPapotti/company2.xml")

=begin
r1=Relation.new("r1",5,2,1)
puts r1.identify

file= File.new "testPapotti/company1.xml"

doc = DocParser.new file

REXML::XPath.each(doc,"/schemaExchange/source/relation") { |rel|
  pp rel.attributes['name']
  pp rel.elements['attribute']
}
=end