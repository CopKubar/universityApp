package com.kubar.universityapp.service;

import com.kubar.universityapp.model.Student;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(classes = {com.kubar.universityapp.configuration.AppConfig.class})
@WebAppConfiguration
public class StudentServiceTest extends AbstractTransactionalTestNGSpringContextTests{

    @Autowired
    @Qualifier("studentService")
    GenericService<Student> studentService;

    //StudentService tests

    @Test
    public void testStudentGetAll(){
        assertNotNull(studentService.getAll());
    }

    @Test
    public void testStudentCreate(){
        List<Student> listBefore = studentService.getAll();
        Student student = new Student("Студент", "Тестовый", new LocalDate(10/2/1994));
        studentService.save(student);
        List<Student> listAfter = studentService.getAll();
        Student student1 = listAfter.get(listAfter.size()-1);
        assertTrue(listAfter.size()-listBefore.size()==1);
        assertTrue(student.getFirstName().equals(student1.getFirstName()));
        assertTrue(student.getLastName().equals(student1.getLastName()));
        assertTrue(student.getEntranceYear()==student1.getEntranceYear());
    }

    @Test
    public void testStudentUpdate(){
        List<Student>listBefore = studentService.getAll();
        Student student = listBefore.get(listBefore.size()-1);
        student.setFirstName(student.getFirstName()+"test");
        studentService.update(student);
        List<Student>listAfter = studentService.getAll();
        Student student1 = listAfter.get(listAfter.size()-1);
        assertTrue(student1.getFirstName().equals(student.getFirstName()));
    }

    @Test
    public void testStudentDelete(){
        List<Student>listBefore = studentService.getAll();
        studentService.delete(listBefore.get(listBefore.size()-1)); //Удаляем последний элемент из списка
        List<Student>listAfter = studentService.getAll();
        assertTrue(listBefore.size()-listAfter.size()==1);
    }

    @Test
    public void testStudentFindById(){
        Student student = new Student("Тест", "Тестовый", new LocalDate(10/2/1994));
        studentService.save(student);
        List<Student> list = studentService.getAll();
        Student student1 = studentService.findById(list.get(list.size()-1).getId()); //Ищем последнего добавленного студента по ID
        assertTrue(student.getFirstName().equals(student1.getFirstName()));
        assertTrue(student.getLastName().equals(student1.getLastName()));
        assertTrue(student.getEntranceYear()==student1.getEntranceYear());
    }

}
