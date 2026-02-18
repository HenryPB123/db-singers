package com.henrypb.dbSingers.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "canciones")
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String titulo;
    private String fechaLanzamiento;

    @ManyToOne
    @JoinColumn(name = "cantante_id")
    private Cantante cantante;

    public Cancion(){}

    public Cancion(Long id, String titulo, String fechaLanzamiento, Cantante cantante) {
        this.id = id;
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.cantante = cantante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String  getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Cantante getCantante() {
        return cantante;
    }

    public void setCantante(Cantante cantante) {
        this.cantante = cantante;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "id = " + id + ", " +
                "titulo = '" + titulo + ",  " +
                "fechaLanzamiento = '" + fechaLanzamiento + ", " +
                "cantante = " + cantante.getNombre() +
                '}';
    }
}
