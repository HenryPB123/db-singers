package com.henrypb.dbSingers.principal;

import com.henrypb.dbSingers.model.Cancion;
import com.henrypb.dbSingers.model.Cantante;
import com.henrypb.dbSingers.model.Menu;
import com.henrypb.dbSingers.repository.CantanteRepository;
import com.henrypb.dbSingers.service.ConsultarGemini;
import com.henrypb.dbSingers.service.ConvierteDatos;
import com.henrypb.dbSingers.util.ValidarNumero;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConvierteDatos conversor = new ConvierteDatos();
    private Cantante infoCantante;
    private CantanteRepository repository;
    private ValidarNumero validador = new ValidarNumero();
    List<Cantante> cantantes;

    public Principal(CantanteRepository cantanteRepository){
        this.repository = cantanteRepository;
    }


    public void mostrarMenu(){
        var opcion = -1;

        while(opcion != 0){

            opcion = validador.validarInt(Menu.muestraMenu(),scanner);

            switch (opcion){
                case 1:
                  encontrarDatosCantante();
                    break;
                case 2:
                    mostrarCantantes();
                    break;
                case 3:
                    mostrarCanciones();
                    break;
                case 4:
                    buscarCantante();
                    break;
                case 5:
                    buscarCancionesPorCantante();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                    System.out.println("\nEsta opción no es valida, elije otra!");
            }
        }
    }

    private void encontrarDatosCantante(){
        System.out.println("Ingresa el nombre del cantante");
        String cantante = scanner.nextLine();
        var json = ConsultarGemini.obtenerInfoCantante(cantante);
        infoCantante = conversor.obtenerDatos(json, Cantante.class);
        System.out.println(json);
        guardarInfoCantanteEnDB(infoCantante);

    }

    private void guardarInfoCantanteEnDB(Cantante infoCantante){
        int respuesta = validador.validarInt(Menu.muestraMenu2(), scanner);
        if (respuesta == 1 ){
            repository.save(infoCantante);
            System.out.println("\nCantante guardado en la base de datos.\n");
        }else {
            mostrarMenu();
        }
    }

    private void mostrarCantantes(){
        cantantes = repository.findAll();
        if (cantantes.size() > 0) {
            System.out.println("++++++++++++++ LISTA DE CANTANTES +++++++++++++");
            cantantes.forEach(cantante -> {
                System.out.println("Cantante ==> {id: " + cantante.getId() + ", nombre: " + cantante.getNombre());
            });
        }else {
            System.out.println("Aún no hay cantantes en la base de datos!");
        }
    }

    private  void mostrarCanciones(){
        List<Cancion> canciones = repository.todasLasCanciones();
        if (canciones.size() > 0){
            System.out.println("++++++++++++++ LISTA DE CANCIONES +++++++++++++");
            canciones.forEach(cancion -> System.out.println("Cancion: " + cancion.getTitulo() + ", Cantante: " + cancion.getCantante().getNombre()));
        }else {
            System.out.println("Aún no hay canciones en la base de datos!");
        }

    }

    private void buscarCantante(){
        System.out.println("Ingresa el nombre del cantante que deseas buscar");
        String nombreCantante= scanner.nextLine();
        Optional<Cantante> cantante = repository.findByNombreContainsIgnoreCase(nombreCantante);

        if (cantante.isPresent()){
            System.out.println(cantante.get());
        }else {
            System.out.println("Cantante no encontrado.");
        }
    }

    private void buscarCancionesPorCantante(){
        cantantes = repository.findAll();
        if (cantantes != null){
        System.out.println("++++++++++++++ LISTA DE CANTANTES DISPONIBLES +++++++++++++");
            cantantes.forEach(cantante -> System.out.println("Nombre: " + cantante.getNombre()));

        System.out.println("\nIngresa el nombre del cantante de quien quieres ver las canciones");
        String nombreCantante= scanner.nextLine();
        List<Cancion> canciones = repository.cancionesPorNombreCantante(nombreCantante);
        if (!canciones.isEmpty()){
            System.out.printf("***************** CANCIONES DEL ARTISTA %s **************\n", nombreCantante.toUpperCase());
            canciones.forEach(cancion ->{
                System.out.println("Título: " + cancion.getTitulo() + ", fecha de lanzamiento: " + cancion.getFechaLanzamiento());
            });
        }else {
            System.out.println("No se encontraron canciones de este artista!");
        }  }else {
            System.out.println("Aún no hay cantantes en la base para realizar esta búsqueda!");
        }


    }



}
