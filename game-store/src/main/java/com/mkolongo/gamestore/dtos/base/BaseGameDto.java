package com.mkolongo.gamestore.dtos.base;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class BaseGameDto {
    private String title;
    private BigDecimal price;
    private double size;
    private String trailer;
    private String imageThumbnail;
    private String description;
    private LocalDate releaseDate;

    protected BaseGameDto() {
    }

    protected BaseGameDto(String title, BigDecimal price, double size, String trailer, String imageThumbnail, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.imageThumbnail = imageThumbnail;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    @Pattern(regexp = "[A-Z].*", message = "Title has to begin with an uppercase letter")
    @Length(min = 3, max = 100, message = "Title must have length between 3 and 100 symbols")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Positive(message = "Price must be a positive number")
    @Digits(integer = 19, fraction = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Positive(message = "Size must be a positive number")
    @Digits(integer = 19, fraction = 1)
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Length(min = 11, max = 11)
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = "^http[s]?://.*|.*", message = "Invalid url")
    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    @Size(min = 20)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
