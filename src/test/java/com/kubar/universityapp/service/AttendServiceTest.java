package com.kubar.universityapp.service;


import com.kubar.universityapp.model.Attend;
import com.kubar.universityapp.model.Student;
import com.kubar.universityapp.model.Subject;
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
public class AttendServiceTest extends AbstractTransactionalTestNGSpringContextTests{

    @Autowired
    @Qualifier("attendService")
    GenericService<Attend>attendService;

    @Autowired
    @Qualifier("subjectService")
    GenericService<Subject>subjectService;

    @Autowired
    @Qualifier("studentService")
    GenericService<Student> studentService;

    @Test
    public void testAttendGetAll(){
        assertNotNull(attendService.getAll());
    }

    @Test
    public void testAttendCreate(){
        List<Attend> listBefore = attendService.getAll();
        //Получаем последние в списке предмет и студента
        Subject subject = subjectService.findById(subjectService.getAll().get(subjectService.getAll().size()-1).getId());
        Student student = studentService.findById(studentService.getAll().get(studentService.getAll().size()-1).getId());
        Attend attend = new Attend(student, subject);
        attendService.save(attend);
        List<Attend> listAfter = attendService.getAll();
        Attend attend1 = listAfter.get(listAfter.size()-1);
        assertTrue(listAfter.size()-listBefore.size()==1);
        assertTrue(attend.getSubject().getId()==attend1.getSubject().getId());
        assertTrue(attend.getStudent().getId()==attend1.getStudent().getId());
    }

    @Test
    public void testAttendUpdate(){
        List<Attend>listBefore = attendService.getAll();
        Attend attend = listBefore.get(listBefore.size()-1);
        //Получаем последние в списке 2 предмета
        Subject subject1 = subjectService.findById(subjectService.getAll().get(subjectService.getAll().size()-1).getId());
        Subject subject2 = subjectService.findById(subjectService.getAll().get(subjectService.getAll().size()-2).getId());

        //Проверка, что attend присваивается объект Subject отличный от того, который он содержит
        if (attend.getSubject().getId()==subject1.getId()){
            attend.setSubject(subject2);
        }else {
            attend.setSubject(subject1);
        }
        attendService.update(attend);
        List<Attend>listAfter = attendService.getAll();
        Attend attend1 = listAfter.get(listAfter.size()-1);
        assertTrue(attend.getSubject().getId()==attend1.getSubject().getId());
    }

    @Test
    public void testAttendDelete(){
        List<Attend>listBefore = attendService.getAll();
        attendService.delete(listBefore.get(listBefore.size()-1)); //Удаляем последний элемент из списка
        List<Attend>listAfter = attendService.getAll();
        assertTrue(listBefore.size()-listAfter.size()==1);
    }

    @Test
    public void testAttendFindById(){
        Subject subject = subjectService.findById(subjectService.getAll().get(subjectService.getAll().size()-1).getId());
        Student student = studentService.findById(studentService.getAll().get(studentService.getAll().size()-1).getId());
        Attend attend = new Attend(student, subject);
        attendService.save(attend);
        List<Attend> list = attendService.getAll();
        Attend attend1 = attendService.findById(list.get(list.size()-1).getId()); //Ищем последний добавленный предмет по ID
        assertTrue(attend.getSubject().getId()==attend1.getSubject().getId());
    }

}
