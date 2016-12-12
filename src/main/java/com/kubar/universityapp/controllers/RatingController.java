package com.kubar.universityapp.controllers;

import com.kubar.universityapp.editors.AttendEditor;
import com.kubar.universityapp.model.Attend;
import com.kubar.universityapp.model.Rating;
import com.kubar.universityapp.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RatingController {

    @Autowired
    @Qualifier("ratingService")
    GenericService<Rating>ratingService;

    @Autowired
    @Qualifier("attendService")
    GenericService<Attend> attendService;

    @Autowired
    @Qualifier("attendEditor")
    AttendEditor attendEditor;


    @RequestMapping(value = {"/rating/add/{id}"}, method = RequestMethod.GET)
    public ModelAndView ratingAdd(@PathVariable Long id){
        ModelAndView mov = new ModelAndView();
        Attend attend = attendService.findById(id);
        List<Integer> marks = new ArrayList<>();
        for (int i=1;i<11;i++){
            marks.add(i);
        }
        mov.addObject("attend", attend);
        mov.addObject("marks", marks);
        mov.addObject("rating", new Rating());
        mov.setViewName("ratingAdd");
        return mov;
    }

    @RequestMapping(value = {"/rating/add/ratingSave"}, method = RequestMethod.POST)
    public ModelAndView ratingSave(@RequestParam("attendId") String attendId, @ModelAttribute Rating rating){
        System.out.println(attendId);
        Attend attend = attendService.findById(Long.parseLong(attendId));
        rating.setAttend(attend);
        ratingService.save(rating);
        return new ModelAndView(new RedirectView("/university/student/profile/"+rating.getAttend().getStudent().getId()));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Attend.class, attendEditor);
    }

}
