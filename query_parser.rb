desc=[[3,3,3],[2,2,2],[4,4,4]]
lecter=["a","b","c"]
query="("
desc.each_index { |desc_i|
        query=query+"field: "
        desc[desc_i].each_index { |i| query=query+(lecter[i]*desc[desc_i][i])  }
        query=query+" AND " if desc_i<(desc.length-1)
      } 
      
puts query
query=query+") OR ("
desc.each_index { |desc_i|
        query=query+"field: "
        desc[desc_i].each_index { |i| query=query+(lecter[i]*desc[desc_i][i])  }
        query=query+"* "
        query=query+" AND " if desc_i<(desc.length-1)
      } 
query[query.length-1]=")"

puts query