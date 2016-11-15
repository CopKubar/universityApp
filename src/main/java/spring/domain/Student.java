package spring.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="student")
public class Student implements Serializable{

    private Long id;
    private String firstName;
    private String lastName;
    private Integer entranceYear;
    private Boolean deleted;
    private List<Attend> attends=new ArrayList<>(0);



    public Student(){}

    public Student(String firstName, String lastName, Integer entranceYear){
        this.firstName=firstName;
        this.lastName=lastName;
        this.entranceYear=entranceYear;
        this.deleted=false;
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

    @Column(name = "firstName", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "entranceYear", nullable = false)
    public Integer getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(Integer entranceYear) {
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


}
