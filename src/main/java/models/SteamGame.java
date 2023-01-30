package models;

import java.util.Objects;

public class SteamGame {
    private String name;
    private String releaseDate;
    private String reviewSummaryResult;
    private String price;
    private Platforms platforms;

    public SteamGame(String name, String releaseDate, String reviewSummaryResult, String price) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.reviewSummaryResult = reviewSummaryResult;
        this.price = price;
    }

    public SteamGame(String name, String releaseDate, String price) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.price = price;

    }

    public SteamGame() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReviewSummaryResult() {
        return reviewSummaryResult;
    }

    public void setReviewSummaryResult(String reviewSummaryResult) {
        this.reviewSummaryResult = reviewSummaryResult;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Platforms getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Platforms platforms) {
        this.platforms = platforms;
    }

    @Override
    public String toString() {
        return "models.SteamGame{" +
                "name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", reviewSummaryResult='" + reviewSummaryResult + '\'' +
                ", price='" + price + '\'' +
                ", platforms=" + platforms +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SteamGame steamGame = (SteamGame) o;
        return Objects.equals(name, steamGame.name) && Objects.equals(releaseDate, steamGame.releaseDate) && Objects.equals(reviewSummaryResult, steamGame.reviewSummaryResult) && Objects.equals(price, steamGame.price) && Objects.equals(platforms, steamGame.platforms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseDate, reviewSummaryResult, price, platforms);
    }
}
