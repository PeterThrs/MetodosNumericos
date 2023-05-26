/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.metodos.metodosnumericotrabajofinal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class RungeKutta {

    private List<Double> valoresX;
    private List<Double> valoresY;
    private List<Double> valoresY1;
    private int cont;

    public RungeKutta() {
        valoresX = new ArrayList<>();
        valoresY = new ArrayList<>();
        valoresY1 = new ArrayList<>();
    }

    static class DifferentialEquation {

        double x;
        double y;

        public DifferentialEquation(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public double runKutta4(Function<DifferentialEquation, Double> f, double x0, double y0, double h, double x) {
        double yn = y0;
        BigDecimal yn_1 = BigDecimal.valueOf(0); 
        BigDecimal xn = BigDecimal.valueOf(x0);
        cont = 0;

        while (xn.doubleValue() < x) {
            
            
            //System.out.println("xn = " + xn);
            DifferentialEquation equation = new DifferentialEquation(xn.doubleValue(), yn);

            double k1 = f.apply(equation);
            System.out.println("k1 = " + k1);

            equation.x = xn.doubleValue() + h / 2;
            equation.y = yn + (k1 * h / 2);
            double k2 = f.apply(equation);
            System.out.println("k2 = " + k2);

            equation.y = yn + (k2 * h / 2);
            double k3 = f.apply(equation);
            System.out.println("k3 = " + k3);

            equation.x = xn.doubleValue() + h;
            equation.y = yn + (k3 * h);
            double k4 = f.apply(equation);
            System.out.println("k4 = " + k4);

            yn_1 = new BigDecimal(yn + ((k1 + 2 * k2 + 2 * k3 + k4) * (h)) / 6);
            xn = xn.add(BigDecimal.valueOf(h));
            
            valoresY.add(yn); 
            valoresY1.add(yn_1.doubleValue()); 
            valoresX.add(xn.doubleValue());
            
            
            
            yn = yn_1.doubleValue();
            cont++;
            System.out.println("xn = " + xn);
        }
        graficar();
        return yn;
    }

    public void graficar() {
        System.out.printf("%-10s %-10s %-10s %-10s %n", "Iteracion", "   X   ","   Y   ", "  Yi+1  ");
        for (int i = 0; i < cont; i++) {
            System.out.printf("%-10s %-10s %-10s %-10s %n", i, valoresX.get(i),Math.round(valoresY.get(i) * 1000) * 0.001 ,Math.round(valoresY1.get(i) * 1000) * 0.001);
        }
        
//        System.out.println("");
//        // Crear gráfica
//        XYChart chart = QuickChart.getChart("Solución de la Ecuación Diferencial", "t", "y", "y(t)", valoresX, valoresY);
//
//        // Mostrar la gráfica
//        new SwingWrapper<>(chart).displayChart();
    }

    public static void main(String[] args) {

        
          Function<DifferentialEquation, Double> funcion = equation -> equation.x * Math.sqrt(equation.y);
        //Function<RungeKutta.DifferentialEquation, Double> funcion = equation -> -2 * Math.pow(equation.x, 3) + 12 * Math.pow(equation.x, 2) - 20 * equation.x + 8.5; 
        //Function<DifferentialEquation, Double> funcion = equation -> Math.pow(equation.x, 2) + Math.pow(equation.y, 2); 
        //Function<DifferentialEquation, Double> funcion = equation -> equation.y * Math.pow(equation.x, 3) - 1.1 * equation.y;
        //Function<DifferentialEquation, Double> funcion = equation -> -2 * Math.pow(equation.x, 3) + 12 * Math.pow(equation.x, 2) - 20 * equation.x + 8.5;
        //Function<DifferentialEquation, Double> funcion = equation -> -4 * Math.exp(0.8 * equation.x) - 0.5 * equation.y;
        //es funcional el metodo segun este
        //Function<DifferentialEquation, Double> funcion = equation -> Math.pow(Math.E, Math.pow(equation.x, 2));
        
        // Definimos las condiciones iniciales
        double x0 = 1; // Valor inicial de x
        double y0 = 4; // Valor inicial de y

        // Definimos el tamaño del paso
        double h = 0.2; // Tamaño del paso

        // Definimos el valor de x para el cual queremos obtener el valor de y
        double x = 1.6; // Valor de x para el cual queremos obtener y

        // Aplicamos el método de Runge-Kutta de cuarto orden
        RungeKutta rs = new RungeKutta();
        double y = rs.runKutta4(funcion, x0, y0, h, x);

        // Imprimimos el resultado
        //System.out.println("El valor de y en x = " + x + " es: " + y);
    }
}
