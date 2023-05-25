/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.metodos.metodosnumericotrabajofinal;

import java.util.function.Function;
import org.knowm.xchart.*;

public class RungeKuttaSolver2 {
    static class DifferentialEquation {
        double t;
        double y;
        
        public DifferentialEquation(double t, double y) {
            this.t = t;
            this.y = y;
        }
    }

    public static double rungeKutta4(Function<DifferentialEquation, Double> f, double t0, double y0, double h, double t) {
        int numSteps = (int) ((t - t0) / h);
        
        double[] tValues = new double[numSteps + 1];
        double[] yValues = new double[numSteps + 1];
        
        tValues[0] = t0;
        yValues[0] = y0;

        for (int i = 1; i <= numSteps; i++) {
            DifferentialEquation equation = new DifferentialEquation(tValues[i-1], yValues[i-1]);
            
            double k1 = h * f.apply(equation);
            
            equation.t = tValues[i-1] + h/2;
            equation.y = yValues[i-1] + k1/2;
            double k2 = h * f.apply(equation);
            
            equation.y = yValues[i-1] + k2/2;
            double k3 = h * f.apply(equation);
            
            equation.t = tValues[i-1] + h;
            equation.y = yValues[i-1] + k3;
            double k4 = h * f.apply(equation);

            yValues[i] = yValues[i-1] + (k1 + 2*k2 + 2*k3 + k4) / 6;
            tValues[i] = tValues[i-1] + h;
        }

        // Crear gráfica
        XYChart chart = QuickChart.getChart("Solución de la Ecuación Diferencial", "t", "y", "y(t)", tValues, yValues);

        // Mostrar la gráfica
        new SwingWrapper<>(chart).displayChart();

        return yValues[numSteps];
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        // Definimos la función f(t, y) que representa la ecuación diferencial dy/dt = f(t, y)
        Function<DifferentialEquation, Double> f = equation -> equation.y * Math.pow(equation.t, 3) - 1.1 * equation.y;

        // Definimos las condiciones iniciales
        double t0 = 0; // Valor inicial de t
        double y0 = 1; // Valor inicial de y

        // Definimos el tamaño del paso
        double h = 0.5; // Tamaño del paso

        // Definimos el valor de t para el cual queremos obtener el valor de y
        double t = 2; // Valor de t para el cual queremos obtener y

        // Aplicamos el método de Runge-Kutta de cuarto orden
        double y = rungeKutta4(f, t0, y0, h, t);

        // Imprimimos el resultado
        System.out.println("El valor de y en t = " + t + " es: " + y);
    }
}
