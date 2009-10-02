require "relation"

class SchemaExchange
  attr_accessor :source,:target
  def initialize(source={},target=nil)
    @source=source
    @target=target
  end
  
  def add_source(*relation)
    relation.each { |rel| @source[rel.name]=rel }
    
  end
  
  def add_target(*relation)
    relation.each { |rel| @target[rel.name]=rel }
  end
  
  def each_fkey
    @source.each_pair { |key, rel|
      rel.fkey.each_pair { |key, val|
              val.each { |fkey| yield "#{rel.identify}-#{source[fkey].identify}" }
          }  
    }
  end

end