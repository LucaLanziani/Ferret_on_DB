require "rubygems"
require "permutation"
require "pp"

str = []
#str = ["gatto"]
#str = ["gatto","topo","canguro"]
perm = Permutation.for(str)
permutazioni = perm.map { |p| p.project}


permutazioni.each {|p|
	p.each { |elem|
		puts "#{elem}, ciao ciao" 
	}
	puts "-----------------------------"
}
puts
puts
pp permutazioni

