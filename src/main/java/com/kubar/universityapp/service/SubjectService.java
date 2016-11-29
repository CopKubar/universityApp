package com.kubar.universityapp.service;

import com.kubar.universityapp.dao.SubjectDao;
import com.kubar.universityapp.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("subjectService")
@Transactional
public class SubjectService implements GenericService<Subject>{

    @Autowired
    @Qualifier("subjectDao")
    SubjectDao subjectDao;

    @Override
    public void save(Subject subject) {
        subjectDao.save(subject);
    }

    @Override
    public void update(Subject subject) {
        subjectDao.update(subject);
    }

    @Override
    public void delete(Subject subject) {
        subjectDao.delete(subject);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Subject> getAll() {
        return subjectDao.getAll();
    }

    @Override
    public Subject findById(Long id) {
        return (Subject) subjectDao.getById(id);
    }
}
