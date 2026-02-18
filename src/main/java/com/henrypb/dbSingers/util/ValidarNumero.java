package com.henrypb.dbSingers.util;

import java.util.Scanner;

public class ValidarNumero {

    public int validarInt(String mensaje, Scanner scanner){
        int numero;
        while(true){
            System.out.println(mensaje);
            String entrada = scanner.nextLine().trim();

            if(entrada.isEmpty()){
                System.out.println("El campo está vacío, por favor ingresa el número de una de las opciones del menú!!\n");
                continue;
            }

            try{
                numero = Integer.parseInt(entrada);
                break;
            }catch (NumberFormatException e){
                System.out.println("El dato que debes ingresar es un número de opción del menú!!\n");
            }
        }
        return numero;
    }
}
