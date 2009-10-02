include Ferret::Index
include Ferret::Search

module ForIndexing
=begin
  table_class type ActiveRecord::Base
  fieldboost type Hash ex: {:fieldname => boost, :fieldname => boost, ...}
=end
 

  def create_indexes(tuples) 
           index = Index.new(:path => "random_index", :create => true)  
           #tables è un hash con :nometabella => table_params
           populate_index(index,tuples)
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
  	count = 0
    
    tuples.times {  document = Ferret::Document.new()
                    rel=rand(3)+1
                    arel = []
                    rel.times {
                        tmp_rel="<#{rand(6)+1}-#{rand(2)+1}-#{rand(2)}>"
                        arel << tmp_rel
                    }
                    document[nrelazioni]=Ferret::Field.new rel
                    document[relazioni]=Ferret::Field.new(arel.join(" "))
                    index << document
                 }
  	puts count	                              
   	index.optimize()
   	index.close()
	end
	
	
	
	def config_example() 
    config={:index1=> {:directory =>"./db_index",
                       :tables =>{:searching_elements => { :boost => 5,
                                                            :fields => { :nome=>0,
                                                                         :cognome=>2,
                                                                         :eta=>5
                                                                       }
                                                         },
                                  :table2 =>  { :boost => 3,
                                                            :fields => { :nome2=>0,
                                                                         :cognome2=>2,
                                                                         :eta2=>5
                                                                       }
                                              }
                                 }

                      },
            :index2 => {:directory =>"./db_index",
                        :tables =>{:searching_elements => { :boost => 5,
                                                            :fields => { :nome=>0,
                                                                         :cognome=>2,
                                                                         :eta=>5
                                                                       }
                                                         },
                                  :table2 =>  { :boost => 3,
                                                            :fields => { :nome2=>0,
                                                                         :cognome2=>2,
                                                                         :eta2=>5
                                                                       }
                                              }
                                 }

                      }
           }
  end
	
	def load_config
	  YAML.load(File.open("config.yaml"))
	end
	
	def save_config(config)
	  File.open("config.yaml","w") {|f| YAML.dump(index_format,f)}
  end
  
end

if __FILE__==$0 
  include ForIndex
  config=load_config
  create_indexes(1000)
  
  #SearchingElement
  #populate(1000)
  #create_index(SearchingElement,{:nome=>1,:cognome=>3,:eta=>6})
  #term query
  #query_parser=Ferret::QueryParser.new()
  #term_query=query_parser.parse("nome: Pietro OR cognome: Pietro*")
  #search(term_query,10)
end
