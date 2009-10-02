class Relation
=begin
  una relazione viene modellata attraverso:
  * nome
  * numero degli attributi
  * numero delle chiavi
  * numero delle chiavi esterne
  * un hash che come chiave ha il nome della chiave esterna e come valore una lista dei nomi delle relazioni a cui quella chiave si riferisce
      es: {"fk1"=>["r1","r2"],"fk2"=>["r3","r4"]}
  * una hash delle costanti reggruppate secondo il tipo (attributo, chiave, chiave esterna)
      es: {:a=>["nome","cognome"],:k=>["id"],:f=>[]}
=end
  
  @@CMAX=2 #numero di cifre utilizzate per rappresentare il numero di attributi|chiavi|chiaviesterne|archi
  
  attr_accessor :natt, :nkey, :nfkey, :ninarcs,:name
  attr_accessor :fkey,:constant
  def initialize(name,natt=0,nkey=0,nfkey=0,ninarcs=0,fkey={})
    @name,@natt,@nkey,@nfkey=name,natt,nkey,nfkey
    @fkey=fkey
    @constant={:a=>[],:k=>[],:f=>[]}
  end
  
  def each_constants
    @constant.each_pair { |key, val| 
      val.each { |e| yield "#{identify}#{key.to_s.upcase}<#{e}>"}
    }
  end
  
  
  def identify
    identify=""
    identify.concat(cod_number(@nkey))
    identify.concat(cod_number(@nfkey))
    identify.concat(cod_number(@ninarcs))
    identify.concat("a"*(@natt+1))
    puts @natt.class
    identify
  end

  def add_fkey(key,value)
    @fkey[key.to_sym]=[] if @fkey[key.to_sym] == nil
    @fkey[key.to_sym] << value
  end
  
  def add_constant(key,value)
    @constant[key]=[] if @constant[key] == nil
    @constant[key]<<value
  end
  
  def nfkey
    raise RuntimeError,"inconsistence" if @nfkey!=@fkey.size 
    @nfkey
  end
  
  private
    def cod_number(number)
      ("0"*@@CMAX).concat(number.to_s)[-@@CMAX..-1].to_s
    end
end
