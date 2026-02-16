package com.ferobt.challengeliteratura.repository;

import com.ferobt.challengeliteratura.modelos.IdiomaLibro;
import com.ferobt.challengeliteratura.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // 3. Consulta por idioma
    List<Libro> findByIdioma(IdiomaLibro idioma);

    // 4. Consulta por nombre de autor (Buscando en la entidad relacionada)
    List<Libro> findByAutorNombreContainingIgnoreCase(String nombreAutor);

    // 5. Consulta por número de descargas (Top 10 libros más descargados)
    List<Libro> findTop10ByOrderByNumeroDescargasDesc();
}
