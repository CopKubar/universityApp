package com.kubar.universityapp.editors;

import com.kubar.universityapp.model.Rating;
import com.kubar.universityapp.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class RatingEditor extends PropertyEditorSupport{

    @Autowired
    @Qualifier("ratingService")
    private GenericService<Rating> ratingService;

    @Override
    public void setAsText(String text){
        try {
            Rating rating = ratingService.findById(Long.parseLong(text));
            this.setValue(rating);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}