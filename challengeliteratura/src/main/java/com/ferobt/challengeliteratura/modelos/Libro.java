package com.ferobt.challengeliteratura.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;


    @ManyToOne(cascade = CascadeType.ALL) // Agrega CascadeType.ALL
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private Double numeroDescargas;
    @Enumerated(EnumType.STRING) // Guarda el nombre del enum (ej: "ESPANOL") en la DB
    private IdiomaLibro idioma;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        //this.autor = datosLibro.autor();
        //this.idiomas = datosLibro.idiomas();
        this.numeroDescargas = datosLibro.numeroDescargas();
    }

    public Libro(){}

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public IdiomaLibro getIdioma() {
        return idioma;
    }

    public void setIdioma(IdiomaLibro idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", numeroDescargas=" + numeroDescargas +
                ", idioma=" + idioma;
    }
}
