import java.util.Scanner;

public class choferes {

    private static final String[] DIAS_SEMANA = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    private static final int INDICE_LUNES = 0; 
    private static final int NUM_DIAS_SEMANA = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numChoferes;

        System.out.println("=================================================");
        System.out.print("¿Cuántos choferes desea ingresar?: ");
        while (true) {
            if (scanner.hasNextInt()) {
                numChoferes = scanner.nextInt();
                if (numChoferes > 0) {
                    break;
                } else {
                    System.out.print("Por favor, ingrese un número mayor a cero: ");
                }
            } else {
                System.out.print("Entrada inválida. Ingrese un número entero: ");
                scanner.next(); 
            }
        }
        scanner.nextLine(); 

        
        String[] nombres = new String[numChoferes];
        double[] sueldosPorHora = new double[numChoferes];
        // Matriz: [Fila = Chofer][Columna = Día (7 días)]
        double[][] horasTrabajadas = new double[numChoferes][NUM_DIAS_SEMANA];

        System.out.println("=================================================");
        System.out.println("--- INGRESO DE DATOS DE LOS " + numChoferes + " CHOFERES ---");
        
        for (int i = 0; i < numChoferes; i++) {
            System.out.println("\n*** Datos del Chofer #" + (i + 1) + " ***");
            
            // Nombre
            System.out.print("Nombre: ");
            nombres[i] = scanner.nextLine();
            
            // Sueldo por Hora
            System.out.print("Sueldo por hora ($): ");
            sueldosPorHora[i] = scanner.nextDouble();
            scanner.nextLine();
            
            // Horas trabajadas por día
            System.out.println("Ingrese las horas trabajadas para cada día (ponga 0 en el día de descanso):");
            for (int j = 0; j < NUM_DIAS_SEMANA; j++) {
                System.out.printf("  %s: ", DIAS_SEMANA[j]);
                horasTrabajadas[i][j] = scanner.nextDouble();
                scanner.nextLine();
            }
        }

        // --- CÁLCULOS ---

        double totalAPagarEmpresa = 0;
        String nombreMasHorasLunes = "N/A";
        double maxHorasLunes = -1;
        
        // (a) Horas Semanales, (b) Sueldo Semanal, (c) Total a Pagar, (d) Máx Horas Lunes
        double[] totalHorasSemanal = new double[numChoferes];
        double[] sueldoSemanal = new double[numChoferes];
        String[] diaDescanso = new String[numChoferes];

        for (int i = 0; i < numChoferes; i++) {
            double horasTotales = 0;
            String descanso = "Ninguno";
            
            for (int j = 0; j < NUM_DIAS_SEMANA; j++) {
                double horas = horasTrabajadas[i][j];
                horasTotales += horas;
                
                if (horas == 0) {
                    descanso = DIAS_SEMANA[j];
                }
                
                // (d) Lógica para encontrar el que trabaja más horas el Lunes
                if (j == INDICE_LUNES && horas > maxHorasLunes) {
                    maxHorasLunes = horas;
                    nombreMasHorasLunes = nombres[i];
                }
            }

            // (a) y (b)
            totalHorasSemanal[i] = horasTotales;
            sueldoSemanal[i] = horasTotales * sueldosPorHora[i];
            
            // (c)
            totalAPagarEmpresa += sueldoSemanal[i];
            
            // Día de descanso (cualquiera a la semana)
            diaDescanso[i] = descanso;
        }

        // --- REPORTE (e) ---

        System.out.println("\n==================================================================================");
        System.out.println("                        REPORTE SEMANAL DE NÓMINA - COMPAÑÍA DE TRANSPORTE        ");
        System.out.println("==================================================================================");
        System.out.printf("%-10s | %-12s | %-10s | %-14s | %-15s\n", 
                        "Nombre", "Horas/Semana", "Sueldo/Hr", "Sueldo Semanal", "Día Descanso");
        System.out.println("----------------------------------------------------------------------------------");

        for (int i = 0; i < numChoferes; i++) {
            System.out.printf("%-10s | %-12.2f | $%-10.2f | $%-14.2f | %-15s\n", 
                            nombres[i], 
                            totalHorasSemanal[i], 
                            sueldosPorHora[i], 
                            sueldoSemanal[i],
                            diaDescanso[i]);
        }

        System.out.println("==================================================================================");
        
        // (c) Imprimir total
        System.out.printf("\nTOTAL A PAGAR POR LA EMPRESA (Costo de nómina semanal): $%.2f\n", totalAPagarEmpresa);
        
        // (d) Imprimir el más trabajador en lunes
        System.out.println("\n----------------------------------------------------------------------------------");
        if (maxHorasLunes > 0) {
            System.out.printf("Trabajador con más horas el día LUNES: %s (%.1f horas)\n", nombreMasHorasLunes, maxHorasLunes);
        } else {
            System.out.println("No hay registro de horas trabajadas el día LUNES.");
        }
        System.out.println("----------------------------------------------------------------------------------");
        
        scanner.close();
    }
}
