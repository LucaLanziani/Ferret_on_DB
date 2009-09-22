require "rubygems"
require "active_record"
require "pp"
require "create_table"
require "ferret"
include Ferret
include Ferret::Index
include Ferret::Search

module ForSearch
	def search(query,limit)
  		index= IndexReader.new("./db_index/")
  		searcher = Searcher.new(index)
 		 result={}
 		 searcher.search_each(query, :limit => limit) { |doc_id,score| 
                                                  mydoc=index.get_document(doc_id) 
                                                  puts "ID: #{doc_id+1} Score: #{score} Nome: #{mydoc[:nome]} Cognome: #{mydoc[:cognome]} Eta: #{mydoc[:eta]}"
                                             }
 		 index.close
	end
end


if ARGV[0]=="init"
  populate
else
  #SearchingElement
  #populate(1000)
  #create_index(SearchingElement,{:nome=>1,:cognome=>3,:eta=>6})
  #term query
  #query_parser=Ferret::QueryParser.new()
  #term_query=query_parser.parse("nome: Pietro OR cognome: Pietro*")
  #search(term_query,10)
end

