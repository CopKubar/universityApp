package com.kubar.universityapp.service;

import com.kubar.universityapp.model.Attend;
import com.kubar.universityapp.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(classes = {com.kubar.universityapp.configuration.AppConfig.class})
@WebAppConfiguration
public class RatingServiceTest extends AbstractTransactionalTestNGSpringContextTests{

    @Autowired
    @Qualifier("attendService")
    GenericService<Attend>attendService;

    @Autowired
    @Qualifier("ratingService")
    GenericService<Rating> ratingService;

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
}
