import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class MenuCrudCompleto {

    
    private static final String NOMBRE_ARCHIVO = "registros.txt"; 
    

    private static List<String[]> listaRegistros = new ArrayList<>();

    private static int nextId = 1; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

    
        cargarDatos();
    
        int maxId = listaRegistros.stream()
                    .mapToInt(r -> Integer.parseInt(r[0]))
                    .max().orElse(0);
        nextId = maxId + 1;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcion) {
                    case 1: crear(scanner); break;
                    case 2: leerTodos(); break;
                    case 3: actualizar(scanner); break;
                    case 4: eliminar(scanner); break;
                    case 5: 
                    
                        guardarDatos();
                        System.out.println("Saliendo del programa. ¡Datos guardados!"); 
                        break;
                    default: System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } catch (Exception e) {
                System.err.println(" ERROR: Entrada inválida o error en la operación. Reiniciando menú.");
                scanner.nextLine();
            }
            System.out.println("------------------------------------");

        } while (opcion != 5);
        
        scanner.close();
    }

// -----------------------------------------------------------
// --- Lógica de Persistencia (Guardar y Cargar) ---
// -----------------------------------------------------------

    private static void guardarDatos() {
        try (FileWriter fw = new FileWriter(NOMBRE_ARCHIVO)) {
            for (String[] registro : listaRegistros) {
                
                String linea = String.join(",", registro);
                fw.write(linea + "\n");
            }
            System.out.println(" Datos guardados en " + NOMBRE_ARCHIVO);
        } catch (IOException e) {
            System.err.println(" Error al guardar los datos: " + e.getMessage());
        }
    }

    
    private static void cargarDatos() {
        File archivo = new File(NOMBRE_ARCHIVO);
        if (archivo.exists()) {
            try (Scanner fileScanner = new Scanner(archivo)) {
                while (fileScanner.hasNextLine()) {
                    String linea = fileScanner.nextLine();
                
                    String[] registro = linea.split(","); 
                    if (registro.length == 3) {
                        listaRegistros.add(registro);
                    }
                }
                System.out.println(" Datos cargados desde " + NOMBRE_ARCHIVO);
            } catch (IOException e) {
                System.err.println(" Error al cargar los datos: " + e.getMessage());
            }
        } else {
            System.out.println(" Archivo de datos no encontrado. Comenzando con lista vacía.");
        }
    }

// -----------------------------------------------------------
// --- Métodos Auxiliares del CRUD (Mismos que el anterior) ---
// -----------------------------------------------------------

    private static void mostrarMenu() {
        System.out.println("\n--- CRUD con Persistencia en Archivo ---");
        System.out.println("1. ➕ Crear Registro");
        System.out.println("2. 📖 Leer Todos los Registros");
        System.out.println("3. ✏️ Actualizar Registro por ID");
        System.out.println("4. 🗑️ Eliminar Registro por ID");
        System.out.println("5. 🚪 Salir (y Guardar)");
        System.out.print("Selecciona una opción: ");
    }

    // --- C: CREATE (Crear) ---
    private static void crear(Scanner scanner) {
        System.out.print("Introduce el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce la edad: ");
        String edad = scanner.nextLine();

        String id = String.valueOf(nextId++);
        String[] nuevoRegistro = new String[]{id, nombre, edad};
        listaRegistros.add(nuevoRegistro);
        
        System.out.println("✅ Registro creado. ID: " + id);
    }

    // --- R: READ (Leer Todos) ---
    private static void leerTodos() {
        if (listaRegistros.isEmpty()) {
            System.out.println("⚠️ La lista de registros está vacía.");
            return;
        }
        System.out.println("--- Lista de Registros (ID | Nombre | Edad) ---");
        for (String[] registro : listaRegistros) {
            System.out.printf("  > %s | %s | %s\n", registro[0], registro[1], registro[2]);
        }
    }
    
    // --- U: UPDATE (Actualizar) ---
    private static void actualizar(Scanner scanner) {
        System.out.print("Introduce el ID del registro a actualizar: ");
        String idBuscar = scanner.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < listaRegistros.size(); i++) {
            String[] registro = listaRegistros.get(i);
            
            if (registro[0].equals(idBuscar)) {
                System.out.printf("Registro actual: ID %s | Nombre: %s | Edad: %s\n", registro[0], registro[1], registro[2]);
                
                System.out.print("Nuevo nombre (dejar vacío para no cambiar): ");
                String nuevoNombre = scanner.nextLine();
                if (!nuevoNombre.trim().isEmpty()) {
                    registro[1] = nuevoNombre;
                }

                System.out.print("Nueva edad (dejar vacío para no cambiar): ");
                String nuevaEdad = scanner.nextLine();
                if (!nuevaEdad.trim().isEmpty()) {
                    registro[2] = nuevaEdad;
                }
                
                System.out.println(" Registro actualizado.");
                encontrado = true;
                break; 
            }
        }

        if (!encontrado) {
            System.out.println(" No se encontró un registro con el ID: " + idBuscar);
        }
    }

    // --- D: DELETE (Eliminar) ---
    private static void eliminar(Scanner scanner) {
        System.out.print("Introduce el ID del registro a eliminar: ");
        String idBuscar = scanner.nextLine();
        
        boolean eliminado = listaRegistros.removeIf(registro -> registro[0].equals(idBuscar));

        if (eliminado) {
            System.out.println(" Registro con ID " + idBuscar + " ha sido eliminado.");
        } else {
            System.out.println(" No se encontró un registro con el ID: " + idBuscar);
        }
    }
}