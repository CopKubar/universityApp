package com.kubar.universityapp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "RATING")
public class Rating implements Serializable {

    private Long id;
    private Attend attend;
    private Integer mark;
    private Boolean deleted=false;

    public Rating(){}

    public Rating(Attend attend, Integer mark){
        this.attend=attend;
        this.mark=mark;
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

    @ManyToOne
    @JoinColumn(name = "attendId")
    public Attend getAttend() {
        return attend;
    }

    public void setAttend(Attend attend) {
        this.attend = attend;
    }


    @Column(name = "mark", nullable = false)
    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
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
        return "Rating{" +
                "id=" + id +
                ", mark=" + mark +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        if (id != null ? !id.equals(rating.id) : rating.id != null) return false;
        if (attend != null ? !attend.equals(rating.attend) : rating.attend != null) return false;
        if (mark != null ? !mark.equals(rating.mark) : rating.mark != null) return false;
        return deleted != null ? deleted.equals(rating.deleted) : rating.deleted == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (attend != null ? attend.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        return result;
    }
}
