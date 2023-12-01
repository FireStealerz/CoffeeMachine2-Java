package com.univa.cafetera;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) { Scanner scanner = new Scanner(System.in);
        final int CAPACIDAD_MAXIMA = 1000;
        System.out.println("Como desea inicialisar la cafetera 1.-Sin cafe\n2.-Con cafe al maximo\n3.-Customisado\n");
        int eleccion = scanner.nextInt();
        switch (eleccion) {
            case 1 -> {
                Cafetera cafetera = new Cafetera(); // Iinicailiza vacia
                while(true) {
                    cafetera.machineAction(scanner.next());
                }
            }
            case 2 -> {
                Cafetera cafetera = new Cafetera(CAPACIDAD_MAXIMA); //llena
                while(true) {
                    cafetera.machineAction(scanner.next());
                }
            }
            case 3 -> {
                System.out.println("Ingrese cafe a llenar: ");
                int seleccion = scanner.nextInt();
                Cafetera cafetera = new Cafetera(CAPACIDAD_MAXIMA, seleccion); //personalisada
                while(true) {
                    cafetera.machineAction(scanner.next());
                }
            }
        }

    }

}
