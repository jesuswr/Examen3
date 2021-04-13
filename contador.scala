import java.io.File

class hiloContador extends Thread {
    // Directorio a recorrer
    var file: File = null

    override def run() {
        // Contador de archivos
        var count = 0
        // Lista de hilos creados
        var threads: List[hiloContador] = List()

        // Revisamos que file exista y sea un directorio
        if (file.exists() && file.isDirectory()) {
            // Lista de archivos en el directorio
            val l = file.listFiles.toList
            for (i <- 0 to l.size - 1) {
                // Si es un directorio creamos un nuevo hilo y lo agregamos
                // a la lista de hilos
                if (l(i).isDirectory()) {
                    var th = new hiloContador()
                    th.file = l(i)
                    th.start()
                    threads = th :: threads 
                }
                // Si es un archivo anadimos 1 al contador
                if (l(i).isFile()) {
                    count = count + 1
                }
            }
        }
        // Anadimos los que contamos
        contador.addToCount(count)
        // Esperamos a los hilos hijos
        threads.foreach(t => t.join())
    }
}

object contador {
    var cnt = 0
    def addToCount(x: Int) {
        // Colocamos esto con synchronized para evitar que dos hilos
        // lo usen al mismo tiempo
        synchronized {
            cnt = cnt + x
        }
    }

    def main(args: Array[String]) {
        var th = new hiloContador()
        th.file = new File(args(0))
        th.start()
        // Esperamos al hilo y luego imprimimos el contador
        th.join()
        println(cnt)
    }
}