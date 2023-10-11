package de.neuefische.backend;
import lombok.Data;

@Data
public class Movie {

    // ATTRIBUTES
    private String _id;
    private String title;
    private int year;
    private String extract;
    private String thumbnail;
    private boolean isFavorite;

    // CONSTRUCTORS:
    public Movie() {
        // Default Constructor
    }
    public Movie(String _id, String title, int year, String extract, String thumbnail) {
        // Custom Constructor
        this._id = _id;
        this.title = title;
        this.year = year;
        this.extract = extract;
        this.thumbnail = thumbnail;
        this.isFavorite = false;
    }
    public Movie(String _id, String title, int year, String extract, String thumbnail, boolean isFavorite) {
        // Custom Constructor
        this._id = _id;
        this.title = title;
        this.year = year;
        this.extract = extract;
        this.thumbnail = thumbnail;
        this.isFavorite = isFavorite;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

}
