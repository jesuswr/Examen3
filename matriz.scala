import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{ Failure, Success }

object matriz {
    def main(args: Array[String]) {
        // Filas y Columnas
        val R = 6;
        val C = 6;

        // Matrices
        val A = Array.ofDim[Int](R, C);
        val B = Array.ofDim[Int](R, C);
        val ANS = Array.ofDim[Int](R, C);

        // Llenar las matrices con numeros random
        val r = new scala.util.Random
        for (i <- 0 to R - 1) {
            for (j <- 0 to C - 1) {
                A(i)(j) = r.nextInt(100);
                B(i)(j) = r.nextInt(100);
            }
        }
        
        println("Matriz 1:")
        for (i <- 0 to R - 1) {
            for (j <- 0 to C - 1) {
                print(A(i)(j));
                print("\t");
            }
            println();
        }
        println("Matriz 2:")
        for (i <- 0 to R - 1) {
            for (j <- 0 to C - 1) {
                print(B(i)(j));
                print("\t");
            }
            println();
        }

        // Contador de cuantas filas han sido sumadas
        var cnt = 0;

        // Funcion que suma filas usando future, es decir en el background
        // mientras el programa sigue
        def sumarFila(row: Int, rowSize: Int) = Future {
            for (j <- 0 to rowSize - 1) {
                ANS(row)(j) = A(row)(j) + B(row)(j);
            }
            // Colocamos eso asi para evitar que 2 llamadas a sumarFila intenten
            // modificar el valor al mismo tiempo
            synchronized {
                cnt = cnt + 1;
            }
        }

        // LLamar a sumarFila para cada fila
        for (i <- 0 to R - 1) {
            sumarFila(i, C);
        }

        // mientras la cantidad de filas sumadas no sea igual a la
        // cantidad de fila, esperamos un tiempo
        while (cnt < R) {
            Thread.sleep(10);
        }

        println("Matriz 1 + 2:")
        for (i <- 0 to R - 1) {
            for (j <- 0 to C - 1) {
                print(ANS(i)(j));
                print("\t");
            }
            println();
        }
    }
}