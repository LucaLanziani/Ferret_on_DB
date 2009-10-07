require "rubygems"
require "permutation"
require "relation"
require "pp"
require "xml_exchange_parser"

=begin
  uno schemaexchange è identificato da una:
  * source: un arry di oggetti relation
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
  
  def each_rel
    source.each { |rel| yield rel}
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
                    @hashsource[rel.name.to_sym]=rel
                    @totfkey+=rel.nfkey
                    @totkey+=rel.nkey
                    @totatt+=rel.natt
                  }
    @ordered=false
  end
  
  def add_target(*relation)
    relation.each { |rel| @target[rel.name]=rel }
  end
  
  def each_ref
    @source.each { |rel|
      rel.fkey.each_pair { |key, val|
              val.each { |fkey| yield "#{rel.identify}-#{hashsource[fkey.to_sym].identify}" }
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
  def each_ref_cod(ending="",separator="_")
	source.each { |rel|
		ref_app = []
		rel.fkey.each_pair { |fkey_name,fkey_ref_rel_name|
			ref_app << hashsource[fkey_ref_rel_name.to_sym]
		}
		out_elem = "#{rel.identify}#{ending}"
		ref_app.sort!.each { |ref| out_elem += "#{separator}#{ref.identify}#{ending}" }
		yield out_elem if ref_app != []
	}
  end

end


if __FILE__==$0
  file_name="../testPapotti/company1.xml"
  file = File.new file_name
  doc = XmlExchangeParser.new :schema,file
  if doc.is_exchange?
  then  
    se=SchemaExchange.new(file_name,doc.get_target)
    doc.each_relation { |relation|  se.add_to_source(relation) }
    puts "HASHSOURCE"
    pp se.hashsource
    puts "\nExternal key"
    se.each_ref_cod {|x| puts "\t#{x}"}
  end
end