package com.henrypb.dbSingers.model;

public class Menu {
    public static String muestraMenu(){
      return  """
                \n***  Bienvenido a tu base de datos de cantantes y canciones  ***\n
        
                Qué desear hacer? Selecciona una opción:
                
                1) Buscar información de cantante con IA
                2) Ver cantantes de la base de datos
                3) Ver canciones de la base de datos
                4) Buscar cantante en la base de datos
                5) Buscar canciones por cantante
                
                0) Salir
                """;
    }

    public static String muestraMenu2(){
      return  """
                Desear guardar la información de este cantante en la base de datos?
                
                1) SI
                2) VOLVER AL MENÚ \n
                """;
    }
}
