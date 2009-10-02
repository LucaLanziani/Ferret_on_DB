require "rubygems"
require "ferret"
include Ferret::Index
include Ferret::Search

=begin
  table_class type ActiveRecord::Base
  fieldboost type Hash ex: {:fieldname => boost, :fieldname => boost, ...}
=end
 

  def create_indexes(tuples) 
           index = Index.new(:path => "unary_index", :create => true)  
           #tables è un hash con :nometabella => table_params
           populate_index(index,tuples)
           return index
  end
  
=begin
  Un documento rappresenta la parte sinistra di uno Schema Exchange
  I campi saranno 
    - # delle Relazioni
    - <#Att-#Key-#EKey> <#Att-#Key-#EKey> 
    - <#Att-#Key-#EKey><#Att-#Key-#EKey>
    - <#Att-#Key-#Ekey><Costante> <#Att-#Key-#Ekey><Costante>
=end
	def populate_index(index,tuples)
  element=[["aaaabbbbcccc","aabbcc","aaabbbccc"],
           ["aaaabbbbcccc","aabbcc","aaabbbccc","aaabbbccc"],
           ["aaaabbbbcccc","aaabbbccc","aabbcc"],
           ["aaaabbbbcccc","aaabbbccc","aabbc"],
           ["aaabbbccc","aaabbbccc","aaabbbccc"],
           ["abc","aabbcc","abbcc"],
           ["aabbcc","abc","abbcc"],
           ["aaaabbbbcccc","aabbcc","aaabbbcccc"],
           ["abbcc","abc","aabbcc"],
           ["aabcc","aaabbc","aaaabc"],
           ["abccc","abccccc","aabbcccc"],
           ["aaaaabbbbbbbcccccc","aaaaaaaaabbbbbbbbbccccccc","aaaaaaaaabbbbbbccc"],
           ["aaaaabbbbbbbcccccc","aaaaaaaaabbbbbbbbbccccccc","aaaaaaaaabbbbbbbcccc"]
          ]
  element.each { |e| document = Ferret::Document.new()
                  document[:field]=Ferret::Field.new e.sort.sort_by{|k| k.length}.join(" ")
                  #document[:number]=Ferret::Field.new rand(50) 
                  index << document
                }
=begin    
    tuples.times {  document = Ferret::Document.new()
                    rel="#{"a"*(rand(5)+1)}#{"b"*(rand(3)+1)}#{"c"*(rand(2)+1)}"
                    rel1="#{"a"*(rand(5)+1)}#{"b"*(rand(3)+1)}#{"c"*(rand(2)+1)}"
                    rel2="#{"a"*(rand(5)+1)}#{"b"*(rand(3)+1)}#{"c"*(rand(2)+1)}"
                    document[:field]=Ferret::Field.new "#{rel} #{rel1} #{rel2}"
                    document[:number]=Ferret::Field.new rand(50) 
                    index << document
                 }
      
                 document = Ferret::Document.new()
                 rel="abc"
                 rel1="aabbcc"
                 rel2="aaabbbccc"
                 document[:field]=Ferret::Field.new "#{rel} #{rel1} #{rel2}"
                 document[:number]=Ferret::Field.new rand(50) 
                index << document       
=end
   	index.optimize()
   	index.close()
	end
	
	def load_config
	  YAML.load(File.open("config.yaml"))
	end
	
	def save_config(config)
	  File.open("config.yaml","w") {|f| YAML.dump(index_format,f)}
  end
  


if __FILE__==$0 
  
  config=load_config
  create_indexes(1000)
  query_parser=Ferret::QueryParser.new()
  #query=query_parser.parse("field: (11*2*3*111*2*3*01*2*3)")
  #query=PhraseQuery.new(:field)
  #query=WildcardQuery.new(:field,"aaa*bbb*ccc*aaa*bbb*ccc*aaa*bbb*ccc*")
  query=query_parser.parse("(field: aaaabbbbcccc AND field: aabbcc AND field:aaabbbccc) OR (field: aaaabbbbcccc* AND field: aabbcc* AND field:aaabbbccc*)")
  index= IndexReader.new("./unary_index/")
  searcher = Searcher.new(index)
  field_sort = SortField.new(:field,:type => :integer, :reverse => true)
  sort = Sort.new([field_sort])
  searcher.search_each(query,:limit => 100,:sort=>sort ) { |doc_id,score| 
                                              mydoc=index.get_document(doc_id) 
                                              puts "field: #{mydoc[:field]} number: #{mydoc[:number]} score: #{score}"
                                         }
end