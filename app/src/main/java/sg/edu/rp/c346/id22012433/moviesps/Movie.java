package sg.edu.rp.c346.id22012433.moviesps;

import java.io.Serializable;

public class Movie implements Serializable {
    private int id;
    private String mtitle;
    private String genre;
    private int year;
    private String rating;

    public Movie(int id, String mtitle, String genre, int year, String rating) {
        this.id = id;
        this.mtitle = mtitle;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getMtitle() {
        return mtitle;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }


    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String toString(){
        return id + "\n" + mtitle + "\n" + genre + "\n" + year + "\n" + rating;
    }
}