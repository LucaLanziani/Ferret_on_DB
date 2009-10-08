require "pp"
require "xml_to_dataexchange"
require "rubygems"
require "ferret"
require "index_populator"
include Ferret::Index
include Ferret::Search

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
    query=""
    query+="n_rel: #{@dataexchange.source.length} AND "
    query+="n_tot_key: #{@dataexchange.totkey} AND " 
    query+="n_tot_fkey: #{@dataexchange.totfkey}"
    query+=" AND ("+rel_cod+" OR "+rel_cod("*")+")" if @dataexchange.source.length>0
    query+=" AND "+"("+ref_cod()+" OR "+ref_cod("*")+")" if @dataexchange.totfkey>0
    query
  end
  
  def rel_cod(ending="")
    if @dataexchange.source.length>0
    then
    result="("
    i=0
    @dataexchange.each_rel{ |x| if i==0 
                                then result+= "cod_rels: #{x.identify}#{ending}" 
                                     i+=1
                                else result+= " AND cod_rels: #{x.identify}#{ending}"
                                end
                          }
    result+=")"
  end
  end
  
  def ref_cod(ending="")
    if @dataexchange.totfkey>0
    then
      result= "(" 
      i=0
      @dataexchange.each_ref_cod(ending){ |x| if i==0
                                    then result+= "cod_fkey: #{x}"
                                        i+=1
                                    else result+= " AND cod_fkey: #{x}"
                                    end}
      result+= ")"
    end
  end
  
end

if __FILE__ == $0
  XML_SCHEMA_DIR="../testPapotti"
  XML_INDEX_DIR="./unary_index/"
  XML_INPUT_FILE="../testPapotti/S3.xml"
  ip = IndexPopulator.new(XML_SCHEMA_DIR,XML_INDEX_DIR)
  ss=SchemaSearcher.new
  ss.from_file(XML_INPUT_FILE)
  
  query_parser=Ferret::QueryParser.new()
  
  #query=query_parser.parse("n_rel: 3 AND n_tot_key: 3 AND n_tot_fkey: 2 AND ((cod_rels: 010001aa AND cod_rels: 010001aaa AND cod_rels 010200aa) OR (cod_rels: 010100aa* AND cod_rels: 010100aaa* AND cod_rels 010200aa*)) AND ((cod_fkey: 010200aa_010001aa_010001aaa) OR (cod_fkey: 010200aa*_010001aa*_010001aaa*))") 
  
  query=query_parser.parse(ss.create_query)
  
  index= IndexReader.new("./unary_index/")
  searcher = Searcher.new(index)
  field_sort = SortField.new(:o_cod_rels,:type => :byte)
  sort = Sort.new([field_sort,SortField::SCORE])
  puts "==== QUERY ====\n\n#{ss.create_query}"
  puts "\n\n====== RESULT ======"
  printed_field=[:n_rel,:n_tot_key,:n_tot_fkey,:cod_rels,:cod_fkey,:target]
  t_start=Time.now
  searcher.search_each(query,:limit => 100, :sort=>sort ) { |doc_id,score| 
                                              mydoc=index.get_document(doc_id)
=begin
                                              puts  "\n\tn_rel: #{mydoc[:n_rel]}"
                                              puts  "\tn_tot_key: #{mydoc[:n_tot_key]}" 
                                              puts  "\tn_tot_fkey: #{mydoc[:n_tot_fkey]}" 
                                              puts  "\tcod_rels: #{mydoc[:cod_rels]}" 
                                              puts  "\tcod_fkey: #{mydoc[:cod_fkey]}" 
                                              puts  "\ttarget: #{mydoc[:target]}" 
                                              puts  "\txml_file_name: #{mydoc[:xml_file_name]}" 
=end
                                              puts "\n\n"
                                              printed_field.each{|field| puts  "\t#{field}: #{mydoc[field]}"}
                                              puts  "\tscore: #{score}"
                                         }
  
  puts "\n\n====== Tempo per la ricerca: #{Time.now-t_start} seconds ======"
end