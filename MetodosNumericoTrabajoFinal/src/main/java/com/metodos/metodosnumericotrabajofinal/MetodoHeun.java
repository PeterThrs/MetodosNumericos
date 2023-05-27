package com.metodos.metodosnumericotrabajofinal;

import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MetodoHeun {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el valor inicial de t: ");
        double t0 = scanner.nextDouble();

        System.out.print("Ingrese el valor final de t: ");
        double tFinal = scanner.nextDouble();
        
        System.out.print("Ingrese el valor inicial de y cuando x=0: ");
        double y0 = scanner.nextDouble();

        System.out.print("Ingrese el tamaño del paso (h): ");
        double h = scanner.nextDouble();

        System.out.print("Ingrese la función f(t, y): ");
        String function = scanner.next();

        int numSteps = (int) Math.ceil((tFinal - t0) / h); // Número de pasos

        Expression expression = new ExpressionBuilder(function)
                .variables("x", "y")
                .build();

        double t = t0;
        double y = y0;

        System.out.println("Tabla de resultados:");
        System.out.println(StringUtils.repeat("-", 30));
        System.out.println(StringUtils.rightPad("t", 10) + StringUtils.rightPad("y", 10));
        System.out.println(StringUtils.repeat("-", 30));

        for (int i = 0; i < numSteps; i++) {
            expression.setVariable("x", t);
            expression.setVariable("y", y);

            double k1 = h * expression.evaluate();
            expression.setVariable("x", t + h);
            expression.setVariable("y", y + k1);

            double k2 = h * expression.evaluate();

            y += (k1 + k2) / 2;
            t += h;

            System.out.println(StringUtils.rightPad(String.format("%.4f", t), 10)
                    + StringUtils.rightPad(String.format("%.4f", y), 10));
        }

        System.out.println(StringUtils.repeat("-", 30));
    }
}