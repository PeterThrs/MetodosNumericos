package com.metodos.metodosnumericotrabajofinal;

import java.util.Scanner;
import java.util.function.BiFunction;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Euler {

    public static double eulerIntegration(BiFunction<Double, Double, Double> f, double a, double b, double h, double initialY) {
        int n = (int) ((b - a) / h); // Número de pasos de integración
        double x = a;
        double y = initialY; // Valor inicial de la integral

        System.out.println("Iteración\t|x\t|y\t|y Verdadero\t|Error relativo (%)");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            double exactSolution = f.apply(x, y);
            double error = Math.abs((exactSolution - y) / exactSolution) * 100;

            System.out.printf("%d\t\t|%.4f\t|%.4f\t|%.4f\t\t|%.4f\n", i + 1, x, y, exactSolution, error);

            y += h * f.apply(x, y);
            x += h;
        }

        return y;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el límite inferior de integración: ");
        double a = scanner.nextDouble();

        System.out.print("Ingrese el límite superior de integración: ");
        double b = scanner.nextDouble();

        System.out.print("Ingrese el tamaño de paso (h): ");
        double h = scanner.nextDouble();

        System.out.print("Ingrese el valor inicial de y en x=" + a + ": ");
        double initialY = scanner.nextDouble();

        scanner.nextLine(); // Consumir el salto de línea después de ingresar el valor inicial

        System.out.print("Ingrese la función f(x, y): ");
        String functionString = scanner.nextLine();

        // Definir la función f(x, y) ingresada por el usuario utilizando exp4j
        BiFunction<Double, Double, Double> f = (x, y) -> {
            double result;
            try {
                Expression expression = new ExpressionBuilder(functionString)
                        .variables("x", "y")
                        .build()
                        .setVariable("x", x)
                        .setVariable("y", y);
                result = expression.evaluate();
            } catch (Exception e) {
                System.out.println("Error: La función ingresada no es válida.");
                result = 0.0;
            }
            return result;
        };

        // Calcular la solución aproximada utilizando el método de Euler
        double approximateSolution = eulerIntegration(f, a, b, h, initialY);
        System.out.println("La solución aproximada es: " + approximateSolution);

        scanner.close();
    }
}