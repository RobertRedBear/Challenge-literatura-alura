package com.ferobt.challengeliteratura.principal;

import com.ferobt.challengeliteratura.modelos.Autor;
import com.ferobt.challengeliteratura.modelos.Datos;
import com.ferobt.challengeliteratura.modelos.IdiomaLibro;
import com.ferobt.challengeliteratura.modelos.Libro;
import com.ferobt.challengeliteratura.repository.LibroRepository;
import com.ferobt.challengeliteratura.service.ConsumoApi;
import com.ferobt.challengeliteratura.service.Convertirdatos;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner teclado = new Scanner(System.in);
    private String URL_BASE = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private Convertirdatos convertirDatos = new Convertirdatos();
    private LibroRepository repositorio;

    public Main(LibroRepository repository) {
        this.repositorio = repository;

    }

    public void mostrarMenu(){

        int opc = -1;
        while(opc != 0){
            System.out.println("Bienvenido al sistema de busqueda de libros");
            var menu = """
                    1.- Buscar libro
                    2.- Ver libros
                    3.- Consulta por idioma
                    4.- Consulta por autor
                    5.- Consulta por numero de descargas
                    0.- Salir
                    """;
            System.out.println(menu);
            System.out.println("Ingrese la opcion deseada: ");
            opc = teclado.nextInt();
            teclado.nextLine();
            switch (opc){
                case 1:
                    datosLibro();
                    break;
                case 2:
                    mostrarLibros();
                    break;
                case 3:
                    consultarPorIdioma();
                    break;
                case 4:
                    consultarPorAutor();
                    break;
                case 5:
                    consultarTop10Descargas();
                    break;
                case 0:
                    System.out.println("Gracias por usar la aplicación");
                    break;
            }
        }
    }

    public Datos obtenerLibros(){
        System.out.println("Ingrese el nombre del libro: ");
        var nombre = teclado.nextLine();
        var json = consumoApi.ConsumoApi(URL_BASE + "?search=" + nombre.replace(" ", "+"));
        System.out.println(json);
        var datosLibro = convertirDatos.convertirDatos(json, Datos.class);
        System.out.println(datosLibro);
        return datosLibro;
    }

    public void datosLibro() {
        System.out.println("------Datos del libro------");
        Datos datos = obtenerLibros();

        if (!datos.libros().isEmpty()) {
            var datosPrimerLibro = datos.libros().get(0);

            //Creamos el objeto libro
            Libro libro = new Libro(datosPrimerLibro);

            //ASIGNAR EL AUTOR (Esto llena la columna autor_id)
            if (!datosPrimerLibro.autor().isEmpty()) {
                // Creamos el objeto Autor con los datos de la API
                Autor autor = new Autor(datosPrimerLibro.autor().get(0));
                // Vinculamos el autor al libro
                libro.setAutor(autor);
            }

            //ASIGNAR EL IDIOMA (Esto llena la columna idioma)
            if (!datosPrimerLibro.idiomas().isEmpty()) {
                String codigoIdioma = datosPrimerLibro.idiomas().get(0);
                libro.setIdioma(IdiomaLibro.fromString(codigoIdioma));
            }

            // 4. Guardar (Gracias al CascadeType.ALL en la entidad, guardará ambos)
            repositorio.save(libro);
            System.out.println("¡Libro guardado con éxito!");
        }
    }

    public void mostrarLibros(){
        List<Libro> libros = repositorio.findAll();
        libros.stream().sorted(Comparator.comparing(Libro::getId))
                .forEach(System.out::println);
    }

    private void consultarPorIdioma() {
        System.out.println("""
            Ingrese el idioma para buscar los libros:
            es - Español
            en - Inglés
            fr - Francés
            pt - Portugués
            """);
        var codigoIdioma = teclado.nextLine();
        try {
            // Convertimos el String del usuario al Enum
            IdiomaLibro idioma = IdiomaLibro.fromString(codigoIdioma);
            List<Libro> librosPorIdioma = repositorio.findByIdioma(idioma);

            if (librosPorIdioma.isEmpty()) {
                System.out.println("No se encontraron libros en ese idioma.");
            } else {
                librosPorIdioma.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Idioma no válido.");
        }
    }

    private void consultarPorAutor() {
        System.out.println("Ingrese el nombre del autor que desea buscar: ");
        var nombreAutor = teclado.nextLine();
        List<Libro> librosPorAutor = repositorio.findByAutorNombreContainingIgnoreCase(nombreAutor);

        if (librosPorAutor.isEmpty()) {
            System.out.println("No se encontraron libros para ese autor.");
        } else {
            librosPorAutor.forEach(System.out::println);
        }
    }

    private void consultarTop10Descargas() {
        System.out.println("------ Top 10 Libros Más Descargados ------");
        List<Libro> topLibros = repositorio.findTop10ByOrderByNumeroDescargasDesc();
        topLibros.forEach(l ->
                System.out.println("Libro: " + l.getTitulo() + " | Descargas: " + l.getNumeroDescargas()));
    }
}
