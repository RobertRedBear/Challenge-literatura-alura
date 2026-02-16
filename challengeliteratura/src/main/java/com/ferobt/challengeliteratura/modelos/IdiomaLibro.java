package com.ferobt.challengeliteratura.modelos;

public enum IdiomaLibro {
        ESPANOL("es", "Español"),
        INGLES("en", "Inglés"),
        FRANCES("fr", "Francés"),
        PORTUGUES("pt", "Portugués");

        private String codigo;
        private String nombreCompleto;

        IdiomaLibro(String codigo, String nombreCompleto) {
            this.codigo = codigo;
            this.nombreCompleto = nombreCompleto;
        }

        // Método para convertir un String (de la API o consola) al Enum
        public static IdiomaLibro fromString(String text) {
            for (IdiomaLibro idioma : IdiomaLibro.values()) {
                if (idioma.codigo.equalsIgnoreCase(text)) {
                    return idioma;
                }
            }
            throw new IllegalArgumentException("Ningún idioma encontrado para: " + text);
        }
    }

