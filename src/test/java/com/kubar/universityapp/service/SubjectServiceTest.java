package com.kubar.universityapp.service;

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
public class SubjectServiceTest extends AbstractTransactionalTestNGSpringContextTests{

    @Autowired
    @Qualifier("subjectService")
    GenericService<Subject>subjectService;

    @Test
    public void testSubjectGetAll(){
        assertNotNull(subjectService.getAll());
    }

    @Test
    public void testSubjectCreate(){
        List<Subject> listBefore = subjectService.getAll();
        Subject subject = new Subject("Информатика");
        subjectService.save(subject);
        List<Subject> listAfter = subjectService.getAll();
        Subject subject1 = listAfter.get(listAfter.size()-1);
        assertTrue(listAfter.size()-listBefore.size()==1);
        assertTrue(subject.getTitle().equals(subject1.getTitle()));
    }

    @Test
    public void testSubjectUpdate(){
        List<Subject>listBefore = subjectService.getAll();
        Subject subject = listBefore.get(listBefore.size()-1);
        subject.setTitle(subject.getTitle()+"test");
        subjectService.update(subject);
        List<Subject>listAfter = subjectService.getAll();
        Subject subject1 = listAfter.get(listAfter.size()-1);
        assertTrue(subject1.getTitle().equals(subject.getTitle()));
    }

    @Test
    public void testSubjectDelete(){
        List<Subject>listBefore = subjectService.getAll();
        subjectService.delete(listBefore.get(listBefore.size()-1)); //Удаляем последний элемент из списка
        List<Subject>listAfter = subjectService.getAll();
        assertTrue(listBefore.size()-listAfter.size()==1);
    }

    @Test
    public void testSubjectFindById(){
        Subject subject = new Subject("Spring 4");
        subjectService.save(subject);
        List<Subject> list = subjectService.getAll();
        Subject subject1 = subjectService.findById(list.get(list.size()-1).getId()); //Ищем последний добавленный предмет по ID
        assertTrue(subject.getTitle().equals(subject1.getTitle()));
    }

}
