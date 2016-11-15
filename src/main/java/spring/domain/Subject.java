package spring.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subject")
public class Subject implements Serializable {

    private Long id;
    private String title;
    private Boolean deleted;

    public Subject(){}

    public Subject(String title){
        this.title=title;
        this.deleted=false;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "deleted", columnDefinition = "boolean default false")
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

}
