package Seleccionador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// Definición de la clase principal
public class SeleccionadorArchivos extends JFrame {

    // Constructor de la clase
    public SeleccionadorArchivos() {
        // Configurar la ventana
        setTitle("Seleccionador de Archivos");  // Establecer el título de la ventana
        setSize(400, 200);  // Establecer el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Configurar la operación de cierre al hacer clic en la "X"

        // Crear un botón para abrir el selector de archivos
        JButton abrirBoton = new JButton("Abrir Archivo");
        abrirBoton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionarArchivo();  // Llamar al método para seleccionar el archivo cuando se hace clic en el botón
            }
        });

        // Agregar el botón a la ventana
        add(abrirBoton);

        // Mostrar la ventana
        setVisible(true);
    }

    // Método para abrir el selector de archivos
    private void seleccionarArchivo() {
        // Crear un selector de archivos
        JFileChooser fileChooser = new JFileChooser();

        // Mostrar el diálogo de selección de archivos
        int resultado = fileChooser.showOpenDialog(this);

        // Procesar el resultado del diálogo
        if (resultado == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            File archivo = fileChooser.getSelectedFile();

            // Procesar el archivo
            leerArchivo(archivo);  // Llamar al método para leer y procesar el archivo
        }
    }

    // Método para leer y procesar el archivo
    private void leerArchivo(File archivo) {
        try {
            // Crear un lector de archivos
            BufferedReader lector = new BufferedReader(new FileReader(archivo));

            // Leer el número de equipos (n)
            int n = Integer.parseInt(lector.readLine());
            System.out.println("Número de equipos: " + n);

            // Leer el tamaño mínimo de gira o permanencia (Mínimo)
            int minimo = Integer.parseInt(lector.readLine());
            System.out.println("Tamaño mínimo: " + minimo);

            // Leer el tamaño máximo de gira o permanencia (Máximo)
            int maximo = Integer.parseInt(lector.readLine());
            System.out.println("Tamaño máximo: " + maximo);

            // Leer la matriz de distancias
            int[][] distancias = new int[n][n];
            System.out.println("Matriz de distancias:");
            for (int i = 0; i < n; i++) {
                String[] valores = lector.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    distancias[i][j] = Integer.parseInt(valores[j]);
                    System.out.print(distancias[i][j] + " ");
                }
                System.out.println();
            }

            // Cerrar el lector
            lector.close();
        } catch (Exception e) {
            e.printStackTrace();  // Imprimir la traza de la pila en caso de excepción
        }
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SeleccionadorArchivos();  // Crear una instancia de la clase principal
            }
        });
    }
}
