require "relation"

=begin
  uno schemaexchange è identificato da una:
  * source: un hash con chiave il nome della relazione che punta a un oggetto relation
  * target: codice xml che descrive il target
=end
class SchemaExchange
  attr_accessor :source,:target,:xml_file_name
  attr_reader :totfkey,:totkey,:totatt
  def initialize(file_name,target=nil,source={})
    @xml_file_name=file_name
    @source=source
    @target=target
    @totfkey=@totkey=@totatt=0
  end
  
  def add_to_source(*relation)
    relation.each { |rel| @source[rel.name]=rel 
                    @totfkey+=rel.nfkey
                    @totkey+=rel.nkey
                    @totatt+=rel.natt
                  }
    
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
  
  def toString
    "SchemaExchange #{@xml_file_name}\nTotale Relazioni: #{source.size}\nTotale Chiavi: #{@totkey}\nTotale Chiavi Esterne: #{@totfkey}\nTotale Attributi: #{@totatt}\n\n"
  end
end