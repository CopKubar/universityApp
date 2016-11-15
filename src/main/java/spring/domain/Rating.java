package spring.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rating")
public class Rating implements Serializable {

    private Long id;
    private Attend attend;
    private Integer mark;
    private Boolean deleted;

    public Rating(){}

    public Rating(Attend attend, Integer mark){
        this.attend=attend;
        this.mark=mark;
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

}
