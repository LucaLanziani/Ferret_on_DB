require "rubygems"
require "active_record"
require "pp"
require "ferret"
require "yaml"
include Ferret
include Ferret::Index
include Ferret::Search

module ForIndexing
=begin
  table_class type ActiveRecord::Base
  fieldboost type Hash ex: {:fieldname => boost, :fieldname => boost, ...}
=end
  class Table < ActiveRecord::Base 
  end

  def create_indexes(config) 
    #config è un hash con coppie nomeindice=>index_params
    config.each_pair { |index_n, index_params|
           #params è un hash con :directory => directory dell'indice 
           #                     :tables => tables
           index = Index.new(:path => index_params[:directory], :create => true)  
           #tables è un hash con :nometabella => table_params
            index_params[:tables].each_pair { |table_n, table_params|
                    #creo una classe ActiverRecord associata alla tabella attuale
                    Table.set_table_name(table_n)
                    #popolo l'indice creato
                    populate_index(index,table_params[:boost],Table,table_params[:fields])
            }
      }
  end
  
	def populate_index(index,doc_boost,table_class,fieldboost)
  	count = 0
  	table_class.find(:all).each { |tuple|
                                  document = Ferret::Document.new(doc_boost)
                                  fieldboost.each_pair { |column, boost|
                                                          #BISOGNA GESTIRE I NUMERI
                                                          field=Ferret::Field.new(tuple[column],boost)
                                                          document[column]=field
                                                        }
                                  index << document
                                  count += 1
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
  ActiveRecord::Base.establish_connection(:adapter => 'mysql',
                                          :host => '192.168.1.100',
                                          :username => 'root',
                                          :password => 'zxcpoi',
  					               :database => 'test');
  
  include ForIndexing
  config=load_config
  create_indexes(config)
  
  #SearchingElement
  #populate(1000)
  #create_index(SearchingElement,{:nome=>1,:cognome=>3,:eta=>6})
  #term query
  #query_parser=Ferret::QueryParser.new()
  #term_query=query_parser.parse("nome: Pietro OR cognome: Pietro*")
  #search(term_query,10)
end
