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
  	#MA QUI OGNI VOLTA CHE RICHIAMI HASHSOURCE SI RIGENERA L'HASH: CONTROLLARE
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
È questo il metodo deputato a ritornare uno per uno e ordinati
gli elementi da aggiungere nel campo relativo ai riferimanti delle chiavi esterne
=end
  def each_ref_cod
	source.each { |rel|
		ref_app = []
		rel.fkey.each_pair { |fkey_name,fkey_ref_rel_name|
			ref_app << hashsource[fkey_ref_rel_name.to_sym]
		}
		out_elem = "#{rel.identify}"
		ref_app.sort!.each { |ref| out_elem += "_#{ref.identify}" }
		yield out_elem if ref_app != []
	}
  end

end
