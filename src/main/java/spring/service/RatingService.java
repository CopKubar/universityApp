package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.RatingDao;
import spring.domain.Rating;

import java.util.List;

@Service("ratingService")
@Transactional
public class RatingService implements GenericService<Rating> {

    @Autowired
    @Qualifier("ratingDao")
    RatingDao ratingDao;

    @Override
    public void save(Rating rating) {
        ratingDao.save(rating);
    }

    @Override
    public void update(Rating rating) {
        ratingDao.update(rating);
    }

    @Override
    public void delete(Rating rating) {
        ratingDao.delete(rating);
    }

    @Override
    public List<Rating> getAll() {
        return ratingDao.getAll();
    }

    @Override
    public Rating findById(Long id) {
        return (Rating) ratingDao.getById(id);
    }
}
