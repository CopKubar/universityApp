package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.StudentDao;
import spring.domain.Student;

import java.util.List;

@Service("studentService")
@Transactional
public class StudentService implements GenericService<Student> {

    @Autowired
    @Qualifier("studentDao")
    StudentDao studentDao;

    @Override
    public void save(Student student) {
        studentDao.save(student);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void delete(Student student) {
        studentDao.delete(student);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getAll() {
       return studentDao.getAll();
    }

    @Override
    public Student findById(Long id) {
        return (Student) studentDao.getById(id);
    }
}
