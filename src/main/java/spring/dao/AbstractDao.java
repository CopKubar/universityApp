package spring.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractDao<T> implements GenericDao<T>{

    @Autowired
    SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T object) {
        getSession().persist(object);
    }

    @Override
    public void update(T object) {
        getSession().merge(object);
    }

    @Override
    public void delete(T object) {
        getSession().delete(object);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        List<T>list=createQuery(getSession());
        return list;
}

    @Override
    @SuppressWarnings("unchecked")
    public T getById(Long id) {
        Criteria criteria = createCriteria(getSession());
        criteria.add(Restrictions.eq("id",id));
        return (T) criteria.uniqueResult();
    }

    abstract Criteria createCriteria(Session session);

    abstract List<T> createQuery(Session session);

}
