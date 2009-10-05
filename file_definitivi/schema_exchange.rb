require "rubygems"
require "permutation"
require "relation"
require "pp"

=begin
  uno schemaexchange è identificato da una:
  * source: un hash con chiave il nome della relazione che punta a un oggetto relation
  * target: codice xml che descrive il target
=end
class SchemaExchange

  attr_accessor :target,:xml_file_name
  attr_reader :totfkey,:totkey,:totatt
  def initialize(file_name,target=nil,source=[])
    @xml_file_name=file_name
    @source=source
    @hashsource={}
    @target=target
    @totfkey=@totkey=@totatt=0
    @ordered=false
  end
  
  def hashsource
  	source.each { |rel| @hashsource[rel.name.to_sym]=rel }
  	@hashsource
  end
  
  def source
  	if @ordered
  		then @source
  		else @ordered=true
  			@source.sort!
  			
  	end
  end
  
  def add_to_source(*relation)
    relation.each { |rel| @source << rel 
                    @totfkey+=rel.nfkey
                    @totkey+=rel.nkey
                    @totatt+=rel.natt
                  }
    @ordered=false
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

=begin
È questo il metodo deputato a ritornare uno per uno (completi di permutazioni)
gli elementi da aggiungere nel campo relativo ai riferimanti delle chiavi esterne
=end
  def each_ref_cod
	get_hash_of_fkey_id.each { |rel_name, list_of_id| 
		perm = Permutation.for(list_of_id)
		perm_of_rel = perm.map { |p| p.project}
		perm_of_rel.each {|p|
			out_elem = "#{@source[rel_name].identify}"
			p.each { |elem|
				#puts "elem: <#{elem}>"
				out_elem += "_#{elem}"
			}
			yield out_elem
		}		
	}
  end

=begin
È questo il metodo deputato a ritornare le costanti associate alle relazioni,
specificando anche a quale tipo di atomo è associata la costante
=end
  def each_constant_cod
	@source.each { |rel_name, rel|
		rel_id = rel.identify
		rel.constant.each { |con_type,list_of_con| 
			list_of_con.each { |con|
				yield "#{rel_id}_#{con_type.to_s.upcase}_#{con}"
			}
		}
	}
  end

  private
	def get_hash_of_fkey_id
		app = {}
		@source.each { |rel_name, rel| 
		rel.fkey.each { |fkey, ref| 
			app[rel_name.to_sym] = [] if app[rel_name.to_sym] == nil
			app[rel_name.to_sym] << @source[ref.to_sym].identify
		}  	
	}
		app
	end
end
