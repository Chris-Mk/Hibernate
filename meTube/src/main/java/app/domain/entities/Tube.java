package app.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "tubes")
public class Tube extends BaseEntity {

    private String title;
    private String description;
    private String youtubeLink;
    private String uploader;

    public Tube() {
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "youtube_link")
    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    @Column
    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
