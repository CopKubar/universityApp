package com.kubar.universityapp.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="STUDENT")
public class Student implements Serializable{

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate entranceYear;
    private Boolean deleted=false;
    private List<Attend> attends=new ArrayList<Attend>(0);

    public Student(){}

    public Student(String firstName, String lastName, LocalDate entranceYear){
        this.firstName=firstName;
        this.lastName=lastName;
        this.entranceYear=entranceYear;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Size(min=2, max=15, message = "Имя должно быть от 2 до 15 символов")
    @Column(name = "firstName", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min=2, max=15, message = "Фамилия должна быть от 2 до 15 символов")
    @Column(name = "lastName", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull(message = "Дата не может быть пустой")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Column(name = "entranceYear", nullable = false)
    public LocalDate getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(LocalDate entranceYear) {
        this.entranceYear = entranceYear;
    }

    @Column(name = "deleted", columnDefinition = "boolean default false")
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.LAZY)
    public List<Attend> getAttends() {
        return attends;
    }

    public void setAttends(List<Attend> attends) {
        this.attends = attends;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", entranceYear=" + entranceYear +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != null ? !id.equals(student.id) : student.id != null) return false;
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
        if (lastName != null ? !lastName.equals(student.lastName) : student.lastName != null) return false;
        if (entranceYear != null ? !entranceYear.equals(student.entranceYear) : student.entranceYear != null)
            return false;
        return deleted != null ? deleted.equals(student.deleted) : student.deleted == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (entranceYear != null ? entranceYear.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        return result;
    }
}
