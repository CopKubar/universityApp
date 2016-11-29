package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.AttendDao;
import spring.domain.Attend;

import java.util.List;

@Service("attendService")
@Transactional
public class AttendService implements GenericService<Attend> {

    @Autowired
    @Qualifier("attendDao")
    AttendDao attendDao;

    @Override
    public void save(Attend attend) {
        attendDao.save(attend);
    }

    @Override
    public void update(Attend attend) {
        attendDao.update(attend);
    }

    @Override
    public void delete(Attend attend) {
        attendDao.delete(attend);
    }

    @Override
    public List<Attend> getAll() {
        return attendDao.getAll();
    }

    @Override
    public Attend findById(Long id) {
        return (Attend) attendDao.getById(id);
    }
}
