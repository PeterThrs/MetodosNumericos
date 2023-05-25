/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.metodos.metodosnumericotrabajofinal;

import java.util.function.Function;

public class RungeKuttaSolver {
    static class DifferentialEquation {
        double x;
        double y;
        
        public DifferentialEquation(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double rungeKutta4(Function<DifferentialEquation, Double> f, double x0, double y0, double h, double x) {
        double yn = y0;
        double xn = x0;

        while (xn < x) {
            DifferentialEquation equation = new DifferentialEquation(xn, yn);
            
            double k1 = h * f.apply(equation);
            
            equation.x = xn + h/2;
            equation.y = yn + k1/2;
            double k2 = h * f.apply(equation);
            
            equation.y = yn + k2/2;
            double k3 = h * f.apply(equation);
            
            equation.x = xn + h;
            equation.y = yn + k3;
            double k4 = h * f.apply(equation);

            yn += (k1 + 2*k2 + 2*k3 + k4) / 6;
            xn += h;
        }

        return yn;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        // Definimos la función f(x, y) que representa la ecuación diferencial dy/dx = f(x, y)
        Function<DifferentialEquation, Double> f = equation -> equation.x * equation.y; // Por ejemplo, dy/dx = x * y

        // Definimos las condiciones iniciales
        double x0 = 0; // Valor inicial de x
        double y0 = 1; // Valor inicial de y

        // Definimos el tamaño del paso
        double h = 0.1; // Tamaño del paso

        // Definimos el valor de x para el cual queremos obtener el valor de y
        double x = 1; // Valor de x para el cual queremos obtener y

        // Aplicamos el método de Runge-Kutta de cuarto orden
        double y = rungeKutta4(f, x0, y0, h, x);

        // Imprimimos el resultado
        System.out.println("El valor de y en x = " + x + " es: " + y);
    }
}
