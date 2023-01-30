package models;

import java.util.Objects;

public class Platforms {
    private Boolean isWindows;
    private Boolean isMac;
    private Boolean isLinux;

    private Boolean isMusic;

    private Boolean isMovie;


    public Platforms() {
    }


    public void setIsWindows(Boolean isWindows) {
        this.isWindows = isWindows;
    }

    public void setIsMac(Boolean isMac) {
        this.isMac = isMac;
    }

    public void setIsLinux(Boolean isLinux) {
        this.isLinux = isLinux;
    }

    public void setIsMusic(Boolean isMusic) {
        this.isMusic = isMusic;
    }

    public void setIsMovie(Boolean isMovie) {
        this.isMovie = isMovie;
    }

    @Override
    public String toString() {
        return "models.Platforms{" +
                "windows=" + isWindows +
                ", mac=" + isMac +
                ", linux=" + isLinux +
                ", music=" + isMusic +
                ", movie=" + isMovie +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platforms platforms = (Platforms) o;
        return Objects.equals(isWindows, platforms.isWindows) && Objects.equals(isMac, platforms.isMac) && Objects.equals(isLinux, platforms.isLinux) && Objects.equals(isMusic, platforms.isMusic) && Objects.equals(isMovie, platforms.isMovie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isWindows, isMac, isLinux, isMusic, isMovie);
    }
}

