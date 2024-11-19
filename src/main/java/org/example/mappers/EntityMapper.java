package org.example.mappers;

public interface EntityMapper<T> {
    String toCSV(T obj); // Konvertiert ein Objekt in CSV-Format
    T fromCSV(String csv); // Erstellt ein Objekt aus CSV-Format
}

