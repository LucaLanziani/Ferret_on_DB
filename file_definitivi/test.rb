require "rubygems"
require "ferret"
require "index_populator"
include Ferret::Index
include Ferret::Search



if __FILE__==$0 
  query_parser=Ferret::QueryParser.new()
  
#  query=query_parser.parse("n_rel: 3 AND n_tot_key: 3 AND n_tot_fkey: 0 AND ((cod_rels: 010000aa AND cod_rels: 010000aa AND cod_rels 010000aaa) OR (cod_rels: 010000aa* AND cod_rels: 010000aa* AND cod_rels 010000aaa*)")) 
  query=query_parser.parse("n_rel: 3 AND n_tot_key: 3 AND n_tot_fkey: 2 AND ((cod_rels: 010001aa AND cod_rels: 010001aaa AND cod_rels 010200aa) OR (cod_rels: 010100aa* AND cod_rels: 010100aaa* AND cod_rels 010200aa*)) AND ((cod_fkey: 010200aa_010001aa_010001aaa) OR (cod_fkey: 010200aa*_010001aa*_010001aaa*))") 
  
  ip = IndexPopulator.new("/home/ivanagloriosi/Desktop/CODE/testPapotti","./unary_index/")
  index= IndexReader.new("./unary_index/")
  searcher = Searcher.new(index)
  field_sort = SortField.new(:cod_rels,:type => :integer, :reverse => true)
  sort = Sort.new([field_sort])
  searcher.search_each(query,:limit => 100,:sort=>sort ) { |doc_id,score| 
                                              mydoc=index.get_document(doc_id) 
                                              puts    "
 		n_rel: #{mydoc[:n_rel]} 
 		n_tot_key: #{mydoc[:n_tot_key]} 
 		n_tot_fkey: #{mydoc[:n_tot_fkey]}
 		cod_rels: #{mydoc[:cod_rels]}
 		cod_fkey: #{mydoc[:cod_fkey]}
 		target: #{mydoc[:cod_fkey]}
 		xml_file_name: #{mydoc[:xml_file_name]}
 		score: #{score}"
                                         }
end
