package com.kubar.universityapp.dao;

import com.kubar.universityapp.model.Rating;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ratingDao")
public class RatingDao extends AbstractDao {

    @Override
    Criteria createCriteria(Session session) {
        return session.createCriteria(Rating.class);
    }

    @Override
    List createQuery(Session session) {
        return session.createQuery("FROM Rating order by id").list();
    }
}
