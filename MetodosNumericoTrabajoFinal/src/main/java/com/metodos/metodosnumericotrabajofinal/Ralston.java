/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.metodos.metodosnumericotrabajofinal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class Ralston {

    private List<Double> valoresX;
    private List<Double> valoresY;
    private List<Double> valoresY1;
    private int cont;

    public Ralston() {
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

    public double runRalston(Function<DifferentialEquation, Double> f, double x0, double y0, double h, double x) {
        double yn = y0;
        BigDecimal xn = BigDecimal.valueOf(x0);
        BigDecimal yn_1 = BigDecimal.valueOf(0); 
        BigDecimal xn_1 = new BigDecimal(0); 
        cont = 0;

        while (xn.doubleValue() < x) {
            
            
            System.out.println("\n______Interacion: " + cont);
            System.out.println("xn = " + xn);
            System.out.println("yn = " + yn);
            
            DifferentialEquation equation = new DifferentialEquation(xn.doubleValue(), yn);

            double k1 = f.apply(equation);
            System.out.println("k1 = " + k1);

            equation.x = xn.doubleValue() +  ((3.0 / 4.0) * h);
            //System.out.println("xn.doubleValue() +  ((3.0 / 4.0) * h = " + (xn.doubleValue() +  ((3.0 / 4.0) * h)));
            equation.y = yn + (3.0 / 4.0) * h * k1;
            //System.out.println("yn + (3.0 / 4.0) * h * k1 = " + yn + (3.0 / 4.0) * h * k1);
            double k2 = f.apply(equation);
            System.out.println("k2 = " + k2);

            yn_1 = new BigDecimal(yn + (1.0 / 3.0) * h * k1 + (2.0 / 3.0) * h * k2);
            xn_1 = xn.add(BigDecimal.valueOf(h));
            
            valoresY.add(yn); 
            valoresY1.add(yn_1.doubleValue());
            valoresX.add(xn_1.doubleValue());
            
            System.out.println("yn = " + yn);
            System.out.println("xn = " + xn);
            
            xn = xn_1; 
            yn = yn_1.doubleValue(); 
            
            
            
            
            
            cont++;
            
        }
        graficar();
        return yn;
    }

    public void graficar() {
        System.out.printf("%-10s %-10s %-10s %-10s %n", "Iteracion", "   X   ","   Y   ", "  Yi+1  ");
        for (int i = 0; i < cont; i++) {
            System.out.printf("%-10s %-10s %-10s %-10s %n", i, valoresX.get(i),Math.round(valoresY.get(i) * 1000) * 0.001 ,valoresY1.get(i));
        }
        
//        System.out.println("");
//        // Crear gráfica
//        XYChart chart = QuickChart.getChart("Solución de la Ecuación Diferencial", "t", "y", "y(t)", valoresX, valoresY);
//
//        // Mostrar la gráfica
//        new SwingWrapper<>(chart).displayChart();
    }

    public static void main(String[] args) {

        //Function<DifferentialEquation, Double> funcion = equation -> equation.x * Math.sqrt(equation.y);
        //Function<DifferentialEquation, Double> funcion = equation -> -2 * Math.pow(equation.x, 3) + 12 * Math.pow(equation.x, 2) - 20 * equation.x + 8.5; 
        //Function<DifferentialEquation, Double> funcion = equation -> Math.pow(equation.x, 2) + Math.pow(equation.y, 2); 
        Function<DifferentialEquation, Double> funcion = equation -> (1+(4*equation.x)) * Math.sqrt(equation.y);
        //Function<DifferentialEquation, Double> funcion = equation -> -2 * Math.pow(equation.x, 3) + 12 * Math.pow(equation.x, 2) - 20 * equation.x + 8.5;
        //Function<DifferentialEquation, Double> funcion = equation -> -4 * Math.exp(0.8 * equation.x) - 0.5 * equation.y;
        //es funcional el metodo segun este
        //Function<DifferentialEquation, Double> funcion = equation -> Math.pow(Math.E, Math.pow(equation.x, 2));
        
        // Definimos las condiciones iniciales
        double x0 = 0; // Valor inicial de x
        double y0 = 1; // Valor inicial de y

        // Definimos el tamaño del paso
        double h = 0.25; // Tamaño del paso

        // Definimos el valor de x para el cual queremos obtener el valor de y
        double x = 1; // Valor de x para el cual queremos obtener y

        // Aplicamos el método de Ralston
        Ralston rs = new Ralston();
        double y = rs.runRalston(funcion, x0, y0, h, x);

        // Imprimimos el resultado
        //System.out.println("El valor de y en x = " + x + " es: " + y);
    }
}