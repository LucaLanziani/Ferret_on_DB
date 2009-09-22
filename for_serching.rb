require "rubygems"
require "active_record"
require "pp"
require "create_table"
require "ferret"
include Ferret
include Ferret::Index
include Ferret::Search

module ForSearching
	def search(query,limit)
  		index= IndexReader.new("./db_index/")
  		searcher = Searcher.new(index)
 		  result={}
 		  count=0
 		  searcher.search_each(query, :limit => limit) { |doc_id,score| 
                                                  mydoc=index.get_document(doc_id) 
                                                  #puts "ID: #{doc_id+1} Score: #{score} Nome: #{mydoc[:nome]} Cognome: #{mydoc[:cognome]} Eta: #{mydoc[:eta]}"
                                                  count+=1
                                             }
 		  index.close
 		  puts count
	end
end

if __FILE__ == $0
  include ForSearching
  

  query_parser=Ferret::QueryParser.new()
  query=query_parser.parse("eta: >80")
  search(query,:all)
end

