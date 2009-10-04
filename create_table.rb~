require "rubygems"
require "active_record"
require "pp"

ActiveRecord::Base.establish_connection(:adapter => 'mysql',
                                        :host => '192.168.1.100',
                                        :username => 'root',
                                        :password => 'zxcpoi',
					               :database => 'test');

module CreateTable
  
  class SearchingElement < ActiveRecord::Base
   end

  def init()
      if not SearchingElement.table_exists?() 
            ActiveRecord::Schema.define do
              create_table :searching_elements do |table|
                  table.string :nome
                  table.string :cognome
                  table.integer :eta
                  table.float :stipendio
                  table.timestamps
              end
            end
      else
         	  puts "Tabella esistente"   
      end
  end

  def random_string(length=10)
          chars = 'abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ'
          password = ''
          length.times { password << chars[rand(chars.size)] }
          password
      end

  def random_item(many=10)
      many.times {
    				item = SearchingElement.create(:nome => random_string(rand(10)+5),
    							                         :cognome => random_string(rand(15)+5),
    							                         :eta => rand(70)+20,
    							                         :stipendio => (rand * 10000)
    							                         )
    				item.save!
    			}
  end

=begin
    populate the db with [(many-many%4),many] tuples using 4 threads
=end
  def populate(many)
  	thread=[]
  	4.times { 
  	  thread << Thread.new { random_item(many/4)}
    }
    thread.each { |e| e.join; puts "#{e} Terminated"  }	
  end

end

if __FILE__ == $0
  include CreateTable
  init
  if ARGV == 1 and ARGV[0].is_a? Integer
    populate(ARGV[0].to_i)
  else
    puts "for insert random tuple:
              usage #{File.basename(__FILE__)} <num_of_tuples>"
  end
end
