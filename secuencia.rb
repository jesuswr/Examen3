module Secuencia
    def agregar(x)
        raise "No implementado"
    end

    def remover()
        raise "No implementado"
    end

    def vacio()
        raise "No implementado"
    end
end

class Pila
    include Secuencia

    def initialize()
        @arr = []
    end

    def agregar(x)
        @arr << x
        nil
    end

    def remover()
        @arr.pop()
    end

    def vacio()
        @arr.empty?
    end
end

class Cola
    include Secuencia

    def initialize()
        @arr = []
    end

    def agregar(x)
        @arr << x
        nil
    end

    def remover()
        @arr.shift()
    end

    def vacio()
        @arr.empty?
    end
end


class Grafo
    attr_accessor :G

    def initialize()
        @G = [[]]
    end

    # Para agregar un nodo agregamos una dimension mas a la lista principal
    def agregar_nodo()
        @G << []
    end

    def agregar_edge(a, b)
        @G[a] << b
        nil
    end
end

class Busqueda
    def initialize
        raise "Dont do it xD"
    end
    
    def buscar(d, h)
        # crear arreglos para saber si un nodo fue visitado y su distancia al
        # primero
        visited = []
        dist = []

        # limpiar la pila o cola en caso de que haya sido usada antes
        while(!@ord.vacio())
            @ord.remover()
        end
        # llenar arreglos de visitados y disntacia
        for i in 0..(@grafo.G.size-1)
            dist << 0
            visited << false
        end

        @ord.agregar(d)
        visited[d] = true

        while (!@ord.vacio)
            v = @ord.remover()
            # si encontramos el valor, devolvemos su distancia
            if (v == h)
                return dist[v]
            end
            # si no vemos a los sucesores de v en el grafo y si no han sido
            # visitados actualizamos su distancia y los ponemos en el orden
            for i in (0..@grafo.G[v].size()-1)
                if (!visited[ @grafo.G[v][i] ])
                    @ord.agregar( @grafo.G[v][i] )
                    visited[ @grafo.G[v][i] ] = true
                    dist[ @grafo.G[v][i] ] = dist[v] + 1
                end
            end
        end
        # si no lo encontramos, devolver -1
        -1
    end
end

class DFS < Busqueda
    def initialize(g)
        @grafo = g
        @ord = Pila.new()
    end
end

class BFS < Busqueda
    def initialize(g)
        @grafo = g
        @ord = Cola.new()
    end
end
