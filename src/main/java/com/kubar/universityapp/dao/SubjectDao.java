package com.kubar.universityapp.dao;

import com.kubar.universityapp.model.Subject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("subjectDao")
public class SubjectDao extends AbstractDao {

    @Override
    Criteria createCriteria(Session session) {
        return session.createCriteria(Subject.class);
    }

    @Override
    List createQuery(Session session) {
        return session.createQuery("FROM Subject order by id").list();
    }

}
