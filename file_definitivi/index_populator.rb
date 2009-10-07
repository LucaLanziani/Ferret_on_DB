require "rubygems"
require "ferret"
require "xml_to_schemaexchange"
require "pp"
include Ferret::Index
include Ferret::Search
=begin
UNO SCHEMA EXCHANGE RAPPRESENTA UN DOCUMENTO
CAMPI DELL'INDICE
1) :n_rel		=> numero relazioni
2) :n_tot_key	=> numero delle chiavi
3) :n_tot_fkey	=> numero delle chiavi esterne
4) :cod_rels 	=> #key#fkey#inarcs<codifica  unaria del nemuro degli attributi mediante il carattere 'a'>
5) :cod_fkey 	=>  rel_<lista ordinata degli identificatori separata da '_' delle relazioni a cui fanno riferimento le chiavi esterne di rel>
6) :target 	=> xml del target
7) :file_name	=> file_name
=end


class IndexPopulator

=begin
	MODIFICARE PASSANDO LA DIRECTORY IN CUI SALVARE L'INDICE E SOPRATTUTTO FARCELO SALVARE DENTRO!!
	PER IL MOMENTO FUNZIONA SOLO CON UNA DIR PIENA DI XML RAPPRESENTANTI SCHEMAEXCHANGE (DIREI CHE È PERFETTAMENTE ADERENTE ALLE SPECIFICHE)
=end
	def initialize(xml_schema_dir_absolute_path, index_dir)
			@xml_schema_dir_absolute_path = xml_schema_dir_absolute_path
			@xtose = XmlToSchemaexchange.new
			@xtose.parse_dir(xml_schema_dir_absolute_path)
			@index_name = index_dir
			@index = Index.new(:path => index_dir, :create => true)
			populate_index
	end
	
private
	def populate_index
		@xtose.each_schemaexchange { |se| 
			document = Ferret::Document.new()
      		document[:n_rel]=Ferret::Field.new se.source.length
      		document[:n_tot_key]=Ferret::Field.new se.totkey
			document[:n_tot_fkey]=Ferret::Field.new se.totfkey
			apprelscod = ""
			se.source.each { |rel| apprelscod += "#{rel.identify} "}
			apprefcod = ""
			se.each_ref_cod { |cod| apprefcod += "#{cod} "}
			document[:cod_rels]=Ferret::Field.new apprelscod[0, apprelscod.length-1]
			#le codifiche delle chiavi esterne vanno separate da spazio?? per ora si
			document[:cod_fkey]=Ferret::Field.new apprefcod[0, apprelscod.length-1]
      document[:target]=Ferret::Field.new se.target
			document[:xml_file_name]=Ferret::Field.new se.xml_file_name

			@index << document
		}
		@index.optimize()
		@index.close()
	end	
  
end

if __FILE__ == $0
  	ip = IndexPopulator.new("/home/ivanagloriosi/Desktop/CODE/testPapotti", "primo_indice_completo")

end
