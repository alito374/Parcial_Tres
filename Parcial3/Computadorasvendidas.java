import java.util.Scanner;

public class    Computadorasvendidas{

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de vendedores (n): ");
        int n_vendedores = scanner.nextInt();
        
        System.out.print("Ingrese la cantidad de zonas (m): ");
        int m_zonas = scanner.nextInt();

        int[][] ventas = new int[n_vendedores][m_zonas];

        System.out.println("\n--- Ingrese los datos de ventas ---");
        for (int i = 0; i < n_vendedores; i++) { 
            for (int j = 0; j < m_zonas; j++) { 
                System.out.print("Ventas Vendedor " + (i + 1) + " en Zona " + (j + 1) + ": ");
                ventas[i][j] = scanner.nextInt();
            }
        }

        int[] totalPorVendedor = new int[n_vendedores];
        int[] totalPorZona = new int[m_zonas];
        int granTotal = 0;

        for (int i = 0; i < n_vendedores; i++) {
            for (int j = 0; j < m_zonas; j++) {
                int ventaActual = ventas[i][j];
                
                totalPorVendedor[i] += ventaActual;
                totalPorZona[j] += ventaActual;
                granTotal += ventaActual;
            }
        }

        int maxVentasZona = -1;
        int indiceMaxZona = -1;
        for (int j = 0; j < m_zonas; j++) {
            if (totalPorZona[j] > maxVentasZona) {
                maxVentasZona = totalPorZona[j];
                indiceMaxZona = j;
            }
        }

        int minVentasVendedor = Integer.MAX_VALUE;
        int indiceMinVendedor = -1;
        for (int i = 0; i < n_vendedores; i++) {
            if (totalPorVendedor[i] < minVentasVendedor) {
                minVentasVendedor = totalPorVendedor[i];
                indiceMinVendedor = i;
            }
        }

        int maxVentasVendedor = -1;
        int indiceMaxVendedor = -1;
        for (int i = 0; i < n_vendedores; i++) {
            if (totalPorVendedor[i] > maxVentasVendedor) {
                maxVentasVendedor = totalPorVendedor[i];
                indiceMaxVendedor = i;
            }
        }

        System.out.println("\n--- Resultados del Análisis ---");
        
        System.out.println("La zona que más computadoras vendió fue la Zona " + (indiceMaxZona + 1) + 
                        " (con " + maxVentasZona + " computadoras).");

        System.out.println("El vendedor que menos computadoras vendió fue el Vendedor " + (indiceMinVendedor + 1) + ".");
        System.out.println("  -> Cantidad vendida (su venta): " + minVentasVendedor + " computadoras.");

        System.out.println("El vendedor que más computadoras vendió fue el Vendedor " + (indiceMaxVendedor + 1) + ".");
        System.out.println("  -> Cantidad vendida (su venta): " + maxVentasVendedor + " computadoras.");

        System.out.println("La cantidad total de computadoras vendidas (todos) fue: " + granTotal + ".");

        scanner.close();
    }
}
