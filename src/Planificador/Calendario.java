package Planificador;

import java.util.Scanner;

public class Calendario {
    public static boolean isValid(int[][] board, int row, int col, int num, int N) {
            // Verificar la fila
        for (int x = 0; x < N; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

            // Verificar la columna
        for (int x = 0; x < N; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        return true;
    }

    public static boolean solveCalendario(int[][] board, int N, int row, int col) {
        if (row == N - 1 && col == N) {
            return true;
        }
        if (col == N) {
            row++;
            col = 0;
        }
        for (int num = 1; num <= N; num++) {
            if (isValid(board, row, col, num, N)) {
                board[row][col] = num;
                if (solveCalendario(board, N, row, col + 1)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ingresar el número N
       // System.out.print("Ingrese el número N: ");
        int N = 20; //scanner.nextInt();

        // Crear un calendario NxN vacío
        int[][] Cal_1 = new int[N][N];

        long startTime = System.currentTimeMillis();


        if (solveCalendario(Cal_1, N, 0, 0)) {
            // Obtener el tiempo de finalización
            long endTime = System.currentTimeMillis();

            // Calcular la duración en milisegundos
            long duration = endTime - startTime;

            // Imprimir el tiempo de ejecución
            System.out.println("Tiempo de ejecución: " + duration + " milisegundos");

            // Imprimir filas positivas
            for (int i = 1; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(Cal_1[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("------------------------------------------------------------");
            // Imprimir filas negativas
            for (int i = 1; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(-Cal_1[i][j] + " ");
                }
                System.out.println();
            }

        } else {
            System.out.println("No hay solución.");
        }
    }
}
