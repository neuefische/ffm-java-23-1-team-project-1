package de.neuefische.backend;
import lombok.Data;
import lombok.NonNull;

@Data
public class Movie {

    // ATTRIBUTES
    private @NonNull String _id;
    private @NonNull String title;
    private @NonNull int year;
    private String extract;
    private String thumbnail;
    private boolean isFavorite;

    // CONSTRUCTORS:
    public Movie () {
        this._id = "test id";
        this.title = "test title";
        this.year = 0;
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
