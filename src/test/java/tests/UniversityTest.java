package tests;

import assistant.CheckInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;
import spring.domain.Attend;
import spring.domain.Rating;
import spring.domain.Student;
import spring.domain.Subject;
import spring.service.GenericService;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(classes = {spring.configuration.AppConfig.class})
@Transactional
public class UniversityTest extends AbstractTransactionalTestNGSpringContextTests{

    @Autowired
    @Qualifier("studentService")
    GenericService<Student> studentService;

    @Autowired
    @Qualifier("subjectService")
    GenericService<Subject> subjectService;

    @Autowired
    @Qualifier("attendService")
    GenericService<Attend> attendService;

    @Autowired
    @Qualifier("ratingService")
    GenericService<Rating> ratingService;

    //StudentService tests

    @Test
    public void testStudentGetAll(){
        assertNotNull(studentService.getAll());
    }

    @Test
    public void testStudentCreate(){
        List<Student> listBefore = studentService.getAll();
        Student student = new Student("Студент", "Тестовый", 2016);
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
        Student student = new Student("Тест", "Тестовый", 2013);
        studentService.save(student);
        List<Student> list = studentService.getAll();
        Student student1 = studentService.findById(list.get(list.size()-1).getId()); //Ищем последнего добавленного студента по ID
        assertTrue(student.getFirstName().equals(student1.getFirstName()));
        assertTrue(student.getLastName().equals(student1.getLastName()));
        assertTrue(student.getEntranceYear()==student1.getEntranceYear());
    }

    //SubjectService tests

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

    //AttendService tests

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

    //RatingService tests

    @Test
    public void testRatingGetAll(){
        assertNotNull(ratingService.getAll());
    }

    @Test
    public void testRatingCreate(){
        List<Rating> listBefore = ratingService.getAll();
        //Получаем последнее в списке назначение
        Attend attend = attendService.findById(attendService.getAll().get(attendService.getAll().size()-1).getId());
        Rating rating = new Rating(attend, 10);
        ratingService.save(rating);
        List<Rating> listAfter = ratingService.getAll();
        Rating rating1 = listAfter.get(listAfter.size()-1);
        assertTrue(listAfter.size()-listBefore.size()==1);
        assertTrue(rating.getAttend().getId()==rating.getAttend().getId());
    }

    @Test
    public void testRatingUpdate(){
        List<Rating>listBefore = ratingService.getAll();
        Rating rating = listBefore.get(listBefore.size()-1);
        Attend attend1 = attendService.findById(attendService.getAll().get(attendService.getAll().size()-1).getId());
        Attend attend2 = attendService.findById(attendService.getAll().get(0).getId());

        //Проверка, что attend присваивается объект Subject отличный от того, который он содержит
        if (rating.getAttend().getId()==attend1.getId()){
            rating.setAttend(attend2);
        }else {
            rating.setAttend(attend1);
        }

        ratingService.update(rating);
        List<Rating>listAfter = ratingService.getAll();
        Rating rating1 = listAfter.get(listAfter.size()-1);
        assertTrue(rating.getAttend().getId()==rating1.getAttend().getId());
    }

    @Test
    public void testRatingDelete(){
        List<Rating>listBefore = ratingService.getAll();
        ratingService.delete(listBefore.get(listBefore.size()-1)); //Удаляем последний элемент из списка
        List<Rating>listAfter = ratingService.getAll();
        assertTrue(listBefore.size()-listAfter.size()==1);
    }

    @Test
    public void testRatingFindById(){
        Attend attend = attendService.findById(attendService.getAll().get(attendService.getAll().size()-1).getId());
        Rating rating = new Rating(attend, 7);
        ratingService.save(rating);
        List<Rating> list = ratingService.getAll();
        Rating rating1 = ratingService.findById(list.get(list.size()-1).getId()); //Ищем последний добавленный предмет по ID
        assertTrue(rating.getAttend().getId()==rating1.getAttend().getId());
    }


    //CheckInput tests
    @Test
    public void testCheckStudentName(){
        //Имя и фамилия может содержать буквы латинского алфавита, длинна строки 2-15 символа

        assertTrue(CheckInput.checkStudentName("Саша"));
        assertFalse(CheckInput.checkStudentName("Sasha")); //Английского алфавит
        assertFalse(CheckInput.checkStudentName("С")); //Длинна строки (2-15)
        assertFalse(CheckInput.checkStudentName("СашаСашаСашаСаша"));
        assertFalse(CheckInput.checkStudentName("С аша")); //Наличие пробела
        assertFalse(CheckInput.checkStudentName("Саша3")); //Наличие цифр
        assertFalse(CheckInput.checkStudentName("")); //Пустая строка
        assertFalse(CheckInput.checkStudentName("_@#!?")); //Символы
    }

    @Test
    public void testCheckStudentDate(){
        //Год поступления состоит только из цифр, в диапазоне 1970-2016 года

        assertTrue(CheckInput.checkStudentDate("2016"));
        assertFalse(CheckInput.checkStudentDate("AAA"));
        assertFalse(CheckInput.checkStudentDate("1969"));
        assertFalse(CheckInput.checkStudentDate("^?&%"));
        assertFalse(CheckInput.checkStudentDate(""));
    }

    @Test
    public void testCheckSubjectTitle(){


        assertTrue(CheckInput.checkSubjectTitle("Художественная литература"));
        assertTrue(CheckInput.checkSubjectTitle("Spring 4"));
        assertFalse(CheckInput.checkSubjectTitle("2ая Мировая Война")); //Первый символ цифра
        assertFalse(CheckInput.checkSubjectTitle(" География"));  //Первый символ пробел
        assertFalse(CheckInput.checkSubjectTitle("ау")); //Строка меньше допустимого диапазона
        assertFalse(CheckInput.checkSubjectTitle("Высшая математикаВысшая математикаВысшая математикаВысшая математикаВысшая математика")); //Строка длиннее допустимого диапазона
        assertFalse(CheckInput.checkSubjectTitle("#%@!^")); //Наличие символов
    }



}
