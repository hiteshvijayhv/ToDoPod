package com.hitesh.todopod;

public class items {
    private String title, title2, title3;

    public items(String title, String title2, String title3) {
        this.title = title;
        this.title2 = title2;
        this.title3 = title3;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return title3;
    }

    public void setYear(String year) {
        this.title3 = year;
    }

    public String getGenre() {
        return title2;
    }

    public void setGenre(String genre) {
        this.title2 = title2;
    }
}