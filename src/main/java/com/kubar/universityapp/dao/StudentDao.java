package com.kubar.universityapp.dao;

import com.kubar.universityapp.model.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentDao")
public class StudentDao extends AbstractDao {

    @Override
    Criteria createCriteria(Session session) {
        return session.createCriteria(Student.class);
    }

    @Override
    List createQuery(Session session) {
        return session.createQuery("FROM Student order by id").list();
    }
}
