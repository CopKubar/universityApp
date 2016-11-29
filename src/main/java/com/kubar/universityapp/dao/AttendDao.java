package com.kubar.universityapp.dao;

import com.kubar.universityapp.model.Attend;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("attendDao")
public class AttendDao extends AbstractDao {

    @Override
    Criteria createCriteria(Session session) {
        return session.createCriteria(Attend.class);
    }

    @Override
    List createQuery(Session session) {
        return session.createQuery("FROM Attend order by id").list();
    }
}
