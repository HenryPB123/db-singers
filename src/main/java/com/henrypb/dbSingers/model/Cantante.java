package com.henrypb.dbSingers.model;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cantantes")
public class Cantante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private String nacimiento;
    private String nacionalidad;
    private String genero;
    @Column(columnDefinition = "TEXT")
     private String biografia;
//    @Transient
    @OneToMany(mappedBy = "cantante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cancion> canciones;

    public Cantante(){}

    public Cantante(Long id, String nombre, String nacimiento, String nacionalidad, String genero, String biografia, List<Cancion> canciones) {
        this.id = id;
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.nacionalidad = nacionalidad;
        this.genero = genero;
        this.biografia = biografia;
        this.canciones = canciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    //Como es un array setearlos uno a uno y luego asignarlos a this.canciones
    public void setCanciones(List<Cancion> canciones) {
       canciones.forEach(cancion -> cancion.setCantante(this));
       this.canciones = canciones;
    }

    @Override
    public String toString() {
        return "Cantante ==> {\n" +
                "nombre = '" + nombre + '\n' +
                "nacimiento = '" + nacimiento + '\n' +
                "nacionalidad = '" + nacionalidad + '\n' +
                "genero = '" + genero + '\n' +
                "biografia = '" + biografia + '\n' +
                '}';
    }
}


//"canciones = " + canciones.stream()
//                .map(Cancion::getTitulo)
//                .toList() + '\n' +