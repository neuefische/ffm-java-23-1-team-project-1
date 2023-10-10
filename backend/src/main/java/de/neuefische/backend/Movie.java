package de.neuefische.backend;

public record Movie(
        String _id,
        String title,
        int year,
        String extract,
        String thumbnail
) { }
