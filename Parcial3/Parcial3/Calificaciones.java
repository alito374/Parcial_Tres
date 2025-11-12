import java.util.Scanner;

public class Calificaciones{

    public static void mostrarMatriz(double[][] mat, int n, int m) {
        System.out.println("--- Matriz de Calificaciones ---");
        for (int i = 0; i < n; i++) {
            System.out.print("Est. " + (i + 1) + ":\t");
            for (int j = 0; j < m; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] calificaciones = null;
        boolean matrizLlena = false;
        int n = 0;
        int m = 0;
        int opcion = 0;
        String errorMsg = "Debes rellenar la matriz primero (Opción 1).";

        do {
            System.out.println("\n--- MENÚ CALIFICACIONES (N x M) ---");
            System.out.println("1. Introducir N y M y rellenar la matriz");
            System.out.println("2. Promedio de calificaciones por estudiante");
            System.out.println("3. Mejor(es) promedio(s) y listado 9.0-10.0");
            System.out.println("4. Listado de estudiantes con promedio < 7.0");
            System.out.println("5. Examen con el promedio más alto");
            System.out.println("6. Examen con el promedio más bajo");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el número de estudiantes (N): ");
                    n = sc.nextInt();
                    System.out.print("Introduce el número de exámenes (M): ");
                    m = sc.nextInt();

                    if (n > 0 && m > 0) {
                        calificaciones = new double[n][m];
                        System.out.println("Introduce las " + (n * m) + " calificaciones:");
                        for (int i = 0; i < n; i++) {
                            System.out.println("Estudiante " + (i + 1) + ":");
                            for (int j = 0; j < m; j++) {
                                System.out.print("  Examen " + (j + 1) + ": ");
                                calificaciones[i][j] = sc.nextDouble();
                            }
                        }
                        matrizLlena = true;
                        System.out.println("Matriz rellenada correctamente.");
                        mostrarMatriz(calificaciones, n, m);
                    } else {
                        System.out.println("N y M deben ser mayores que 0.");
                    }
                    break;

                case 2:
                    if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(calificaciones, n, m);
                        System.out.println("--- Promedios por Estudiante ---");
                        for (int i = 0; i < n; i++) {
                            double sumaFila = 0;
                            for (int j = 0; j < m; j++) {
                                sumaFila += calificaciones[i][j];
                            }
                            double promedioEst = sumaFila / m;
                            System.out.println("Estudiante " + (i + 1) + ": " + promedioEst);
                        }
                    }
                    break;

                case 3:
                    if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(calificaciones, n, m);
                        double[] promedios = new double[n];
                        double maxPromedio = -1.0;
                        int contadorMejores = 0;

                        for (int i = 0; i < n; i++) {
                            double sumaFila = 0;
                            for (int j = 0; j < m; j++) {
                                sumaFila += calificaciones[i][j];
                            }
                            promedios[i] = sumaFila / m;
                            
                            if (promedios[i] > maxPromedio) {
                                maxPromedio = promedios[i];
                            }
                            
                            if (promedios[i] >= 9.0 && promedios[i] <= 10.0) {
                                contadorMejores++;
                            }
                        }
                        
                        System.out.println("--- Mejor(es) Promedio(s) ---");
                        System.out.println("El promedio más alto fue: " + maxPromedio);
                        System.out.println("Estudiantes que obtuvieron el mejor promedio:");
                        for(int i = 0; i < n; i++) {
                            if(promedios[i] == maxPromedio) {
                                 System.out.println("Estudiante " + (i + 1));
                            }
                        }

                        System.out.println("\n--- Estudiantes con Promedio entre 9.0 y 10.0 ---");
                        if (contadorMejores == 0) {
                            System.out.println("Ningún estudiante cumple este criterio.");
                        } else {
                            double[][] matrizMejores = new double[contadorMejores][m];
                            int filaMejores = 0;
                            System.out.println("Listado:");
                            for (int i = 0; i < n; i++) {
                                if (promedios[i] >= 9.0 && promedios[i] <= 10.0) {
                                    System.out.println("Estudiante " + (i + 1) + " (Promedio: " + promedios[i] + ")");
                                    for(int j=0; j < m; j++) {
                                        matrizMejores[filaMejores][j] = calificaciones[i][j];
                                    }
                                    filaMejores++;
                                }
                            }
                            System.out.println("Matriz generada con estos estudiantes:");
                            mostrarMatriz(matrizMejores, contadorMejores, m);
                        }
                    }
                    break;

                case 4:
                     if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(calificaciones, n, m);
                        double[] promedios = new double[n];
                        int contadorBajos = 0;

                        for (int i = 0; i < n; i++) {
                            double sumaFila = 0;
                            for (int j = 0; j < m; j++) {
                                sumaFila += calificaciones[i][j];
                            }
                            promedios[i] = sumaFila / m;
                            
                            if (promedios[i] < 7.0) {
                                contadorBajos++;
                            }
                        }

                        System.out.println("--- Estudiantes con Promedio < 7.0 ---");
                        if (contadorBajos == 0) {
                            System.out.println("Ningún estudiante cumple este criterio.");
                        } else {
                            double[][] matrizBajos = new double[contadorBajos][m];
                            int filaBajos = 0;
                            System.out.println("Listado:");
                            for (int i = 0; i < n; i++) {
                                if (promedios[i] < 7.0) {
                                    System.out.println("Estudiante " + (i + 1) + " (Promedio: " + promedios[i] + ")");
                                    for(int j=0; j < m; j++) {
                                        matrizBajos[filaBajos][j] = calificaciones[i][j];
                                    }
                                    filaBajos++;
                                }
                            }
                            System.out.println("Matriz generada con estos estudiantes:");
                            mostrarMatriz(matrizBajos, contadorBajos, m);
                        }
                    }
                    break;

                case 5:
                    if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(calificaciones, n, m);
                        double maxPromExamen = -1.0;
                        double[] promediosExamen = new double[m];
                        
                        System.out.println("--- Promedios por Examen ---");
                        for (int j = 0; j < m; j++) {
                            double sumaCol = 0;
                            for (int i = 0; i < n; i++) {
                                sumaCol += calificaciones[i][j];
                            }
                            promediosExamen[j] = sumaCol / n;
                            System.out.println("Promedio Examen " + (j+1) + ": " + promediosExamen[j]);
                            
                            if (promediosExamen[j] > maxPromExamen) {
                                maxPromExamen = promediosExamen[j];
                            }
                        }
                        System.out.println("\nEl promedio más alto en un examen fue: " + maxPromExamen);
                        System.out.println("Examen(es) con el promedio más alto:");
                        for (int j = 0; j < m; j++) {
                            if(promediosExamen[j] == maxPromExamen) {
                                System.out.println("Examen " + (j + 1));
                            }
                        }
                    }
                    break;

                case 6:
                     if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(calificaciones, n, m);
                        double minPromExamen = 101.0; 
                        double[] promediosExamen = new double[m];
                        
                        for (int j = 0; j < m; j++) {
                            double sumaCol = 0;
                            for (int i = 0; i < n; i++) {
                                sumaCol += calificaciones[i][j];
                            }
                            promediosExamen[j] = sumaCol / n;
                            
                            if (promediosExamen[j] < minPromExamen) {
                                minPromExamen = promediosExamen[j];
                            }
                        }
                        System.out.println("El promedio más bajo en un examen fue: " + minPromExamen);
                        System.out.println("Examen(es) con el promedio más bajo:");
                        for (int j = 0; j < m; j++) {
                            if(promediosExamen[j] == minPromExamen) {
                                System.out.println("Examen " + (j + 1));
                            }
                        }
                    }
                    break;

                case 7:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (opcion != 7);

        sc.close();
    }
}
//corregir las decimales cuidarlo//
