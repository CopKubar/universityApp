package com.kubar.universityapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ATTEND")
public class Attend implements Serializable {

    private Long id;
    private Student student;
    private Subject subject;
    private Boolean deleted=false;
    private List<Rating> rating;

    public Attend(){}

    public Attend(Student student, Subject subject){
        this.student=student;
        this.subject=subject;
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
    @JoinColumn(name = "studentId")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "subjectId")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Column(name = "deleted", columnDefinition = "boolean default false")
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attend", fetch = FetchType.LAZY)
    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Attend id = "+id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attend attend = (Attend) o;

        if (id != null ? !id.equals(attend.id) : attend.id != null) return false;
        if (student != null ? !student.equals(attend.student) : attend.student != null) return false;
        if (subject != null ? !subject.equals(attend.subject) : attend.subject != null) return false;
        return deleted != null ? deleted.equals(attend.deleted) : attend.deleted == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        return result;
    }
}
