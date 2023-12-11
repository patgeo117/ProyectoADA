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
        configurarVentana();
        agregarBotonAbrirArchivo();
        mostrarVentana();
    }

    // Configurar la ventana
    private void configurarVentana() {
        setTitle("Seleccionador de Archivos");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Agregar el botón para abrir el selector de archivos
    private void agregarBotonAbrirArchivo() {
        JButton abrirBoton = new JButton("Abrir Archivo");
        abrirBoton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    seleccionarArchivo();
                } catch (DatosInvalidosException ex) {
                    manejarExcepcion(ex);
                }
            }
        });
        add(abrirBoton);
    }

    // Mostrar la ventana
    private void mostrarVentana() {
        setVisible(true);
    }

    // Método para abrir el selector de archivos
    private void seleccionarArchivo() throws DatosInvalidosException {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            leerArchivo(archivo);
        }
    }

    // Método para leer y procesar el archivo
    private void leerArchivo(File archivo) throws DatosInvalidosException {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            int n = Integer.parseInt(lector.readLine());
            int minimo = Integer.parseInt(lector.readLine());
            int maximo = Integer.parseInt(lector.readLine());

            System.out.println("Número de equipos: " + n);
            System.out.println("Tamaño mínimo: " + minimo);
            System.out.println("Tamaño máximo: " + maximo);

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

        } catch (NumberFormatException e) {
            manejarExcepcion(new DatosInvalidosException("Error: Los datos no son válidos."));
        } catch (Exception e) {
            manejarExcepcion(new DatosInvalidosException("Error desconocido al procesar el archivo."));
        }
    }

    // Manejar excepción
    private void manejarExcepcion(DatosInvalidosException ex) {
        System.err.println(ex.getMessage());
        ex.printStackTrace();
    }

    // Definición de la excepción personalizada
    class DatosInvalidosException extends Exception {
        public DatosInvalidosException(String mensaje) {
            super(mensaje);
        }
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SeleccionadorArchivos());
    }
}
