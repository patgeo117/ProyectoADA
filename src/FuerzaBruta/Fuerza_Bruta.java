package FuerzaBruta;

import java.util.Random;

public class Fuerza_Bruta {
    /// Método para crear una matriz según el patrón especificado

    /**
    public static int[][] crearMatrizPatron(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("El tamaño de la matriz debe ser mayor que 0");
        }

        int[][] matriz = new int[n*2*(n-1)][n];

        for (int i = 0; i < n*2*(n-1); i++) {
            for (int j = 0; j < n; j++) {
                int numero = (i % 2 == 0) ? j + 1 : n - j;
                while (yaEstaEnLaColumna(matriz, j, numero)) {
                    // Si el número ya está en la columna, intentar con otro
                    numero = (numero % n) + 1;
                }
                matriz[i][j] = numero;
            }
        }

        return matriz;
    }
     **/

    public static int[][] crearMatrizPatron(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("El tamaño de la matriz debe ser mayor que 0");
        }

        int[][] matriz = new int[(n - 1)][n];

        // Generar la secuencia de números consecutivos
        int[] secuencia = new int[n];
        for (int i = 0; i < n; i++) {
            secuencia[i] = i + 1;
        }

        // Mezclar la secuencia para evitar repeticiones
        Random rand = new Random();
        for (int i = matriz.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 2);
            // Intercambiar elementos
            int temp = secuencia[i];
            secuencia[i % n] = secuencia[j % n];
            secuencia[j % n] = temp;
        }

        // Llenar la matriz con la secuencia generada
        for (int i = 0; i < (n-1); i++) {
            for (int j = 0; j < n; j++) {
                int numero = (i % 2 == 0) ? j + 1 : n - j;
                while (yaEstaEnLaColumna(matriz, j, numero)) {
                    // Si el número ya está en la columna, intentar con otro
                    numero = (numero % n) + 1;
                }
                matriz[i][j] = numero;
            }
        }

        return matriz;
    }



    // Método para verificar si un número ya está en una columna
    private static boolean yaEstaEnLaColumna(int[][] matriz, int columna, int numero) {
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][columna] == numero) {
                return true;
            }
        }
        return false;
    }

    // Método para imprimir una matriz
    public static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Método main para utilizar la clase
    public static void main(String[] args) {

        Fuerza_Bruta f = new Fuerza_Bruta();

        // Crear una matriz según el patrón con n=4
        int[][] miMatriz = f.crearMatrizPatron(4);

        // Imprimir la matriz
        System.out.println("MATRIZ:");
        f.imprimirMatriz(miMatriz);
    }
}
