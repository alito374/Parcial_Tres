import java.util.Scanner;

public class MenuMatriz {

    public static void mostrarMatriz(int[][] m) {
        System.out.println("--- Matriz Original ---");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] matriz = new int[4][4];
        boolean matrizLlena = false;
        int opcion = 0;

        do {
            System.out.println("\n--- MENÚ MATRIZ 4x4 ---");
            System.out.println("1. Rellenar la matriz (Sin repetidos)");
            System.out.println("2. Suma de filas y columnas");
            System.out.println("3. Suma de una fila específica");
            System.out.println("4. Suma de una columna específica");
            System.out.println("5. Mayor y menor valor (y su posición)");
            System.out.println("6. Contar pares");
            System.out.println("7. Contar impares");
            System.out.println("8. Generar matriz con cuadrados");
            System.out.println("9. Sumar diagonal principal");
            System.out.println("10. Sumar diagonal inversa");
            System.out.println("11. Media de todos los valores");
            System.out.println("12. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            String errorMsg = "Debes rellenar la matriz primero (Opción 1).";

            switch (opcion) {
                case 1:
                    System.out.println("Rellenando la matriz (16 valores)...");
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            int valor;
                            boolean repetido;
                            do {
                                System.out.print("Introduce valor para [" + i + "][" + j + "]: ");
                                valor = sc.nextInt();
                                repetido = false;

                                for (int k = 0; k <= i; k++) {
                                    int limiteJ = (k == i) ? j : 4;
                                    for (int l = 0; l < limiteJ; l++) {
                                        if (matriz[k][l] == valor) {
                                            repetido = true;
                                            break;
                                        }
                                    }
                                    if (repetido) break;
                                }

                                if (repetido) {
                                    System.out.println("Valor repetido. Introduce otro.");
                                }
                            } while (repetido);
                            matriz[i][j] = valor;
                        }
                    }
                    matrizLlena = true;
                    System.out.println("Matriz rellenada correctamente.");
                    mostrarMatriz(matriz);
                    break;

                case 2:
                    if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(matriz);
                        for (int i = 0; i < 4; i++) {
                            int sumaFila = 0;
                            for (int j = 0; j < 4; j++) {
                                sumaFila += matriz[i][j];
                            }
                            System.out.println("Suma Fila " + (i + 1) + ": " + sumaFila);
                        }
                        for (int j = 0; j < 4; j++) {
                            int sumaCol = 0;
                            for (int i = 0; i < 4; i++) {
                                sumaCol += matriz[i][j];
                            }
                            System.out.println("Suma Columna " + (j + 1) + ": " + sumaCol);
                        }
                    }
                    break;

                case 3:
                    if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(matriz);
                        System.out.print("Introduce el número de fila (1-4): ");
                        int fila = sc.nextInt();
                        if (fila >= 1 && fila <= 4) {
                            int filaIndex = fila - 1;
                            int sumaFila = 0;
                            for (int j = 0; j < 4; j++) {
                                sumaFila += matriz[filaIndex][j];
                            }
                            System.out.println("Suma Fila " + fila + ": " + sumaFila);
                        } else {
                            System.out.println("Fila incorrecta. Debe ser entre 1 y 4.");
                        }
                    }
                    break;

                case 4:
                    if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(matriz);
                        System.out.print("Introduce el número de columna (1-4): ");
                        int col = sc.nextInt();
                        if (col >= 1 && col <= 4) {
                            int colIndex = col - 1;
                            int sumaCol = 0;
                            for (int i = 0; i < 4; i++) {
                                sumaCol += matriz[i][colIndex];
                            }
                            System.out.println("Suma Columna " + col + ": " + sumaCol);
                        } else {
                            System.out.println("Columna incorrecta. Debe ser entre 1 y 4.");
                        }
                    }
                    break;

                case 5:
                    if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(matriz);
                        int min = matriz[0][0];
                        int max = matriz[0][0];
                        int minF = 0, minC = 0, maxF = 0, maxC = 0;

                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (matriz[i][j] < min) {
                                    min = matriz[i][j];
                                    minF = i;
                                    minC = j;
                                }
                                if (matriz[i][j] > max) {
                                    max = matriz[i][j];
                                    maxF = i;
                                    maxC = j;
                                }
                            }
                        }
                        System.out.println("Mayor valor: " + max + " en [" + maxF + "][" + maxC + "]");
                        System.out.println("Menor valor: " + min + " en [" + minF + "][" + minC + "]");
                    }
                    break;

                case 6:
                    if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(matriz);
                        int pares = 0;
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (matriz[i][j] % 2 == 0) {
                                    pares++;
                                }
                            }
                        }
                        System.out.println("Total de números pares: " + pares);
                    }
                    break;

                case 7:
                    if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(matriz);
                        int impares = 0;
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (matriz[i][j] % 2 != 0) {
                                    impares++;
                                }
                            }
                        }
                        System.out.println("Total de números impares: " + impares);
                    }
                    break;

                case 8:
                    if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(matriz);
                        int[][] matrizCuadrada = new int[4][4];
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                matrizCuadrada[i][j] = matriz[i][j] * matriz[i][j];
                            }
                        }
                        System.out.println("Matriz con valores al cuadrado:");
                        mostrarMatriz(matrizCuadrada);
                    }
                    break;

                case 9:
                    if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(matriz);
                        int sumaDiagP = 0;
                        for (int i = 0; i < 4; i++) {
                            sumaDiagP += matriz[i][i];
                        }
                        System.out.println("Suma diagonal principal: " + sumaDiagP);
                    }
                    break;

                case 10:
                    if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(matriz);
                        int sumaDiagI = 0;
                        for (int i = 0; i < 4; i++) {
                            sumaDiagI += matriz[i][3 - i];
                        }
                        System.out.println("Suma diagonal inversa: " + sumaDiagI);
                    }
                    break;

                case 11:
                    if (!matrizLlena) {
                        System.out.println(errorMsg);
                    } else {
                        mostrarMatriz(matriz);
                        double sumaTotal = 0;
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                sumaTotal += matriz[i][j];
                            }
                        }
                        double media = sumaTotal / 16.0;
                        System.out.println("La media de todos los valores es: " + media);
                    }
                    break;

                case 12:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (opcion != 12);

        sc.close();
    }
}

