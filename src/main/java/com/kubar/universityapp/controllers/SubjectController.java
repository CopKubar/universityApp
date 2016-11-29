package com.kubar.universityapp.controllers;

import com.kubar.universityapp.model.Subject;
import com.kubar.universityapp.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SubjectController {

    @Autowired
    @Qualifier("subjectService")
    GenericService<Subject> subjectService;

    @RequestMapping(value = {"/subjects"}, method = RequestMethod.GET)
    public ModelAndView subjects(){
        ModelAndView mov = new ModelAndView();
        List<Subject>list = subjectService.getAll();
        mov.addObject("subjectList", list);
        mov.setViewName("subjects");
        return mov;
    }

    @RequestMapping(value = {"/subjectAdd"}, method = RequestMethod.GET)
    public ModelAndView subjectAdd(){
        ModelAndView mov = new ModelAndView();
        Subject subject = new Subject();
        mov.addObject("subject", subject);
        mov.setViewName("subjectAddAndUpdate");
        return mov;
    }

    @RequestMapping(value = {"/subjectAdd"}, method = RequestMethod.POST)
    public ModelAndView saveSubject(@Valid Subject subject, BindingResult result){
        if (result.hasErrors()){
            return new ModelAndView("subjectAddAndUpdate");
        }
        subjectService.save(subject);
        return new ModelAndView(new RedirectView("/university/subjects"));
    }


    @RequestMapping(value = {"/subject/update/{id}"}, method = RequestMethod.GET)
    public ModelAndView subjectUpdate(@PathVariable Long id){
        ModelAndView mov = new ModelAndView();
        Subject subject = subjectService.findById(id);
        mov.addObject("subject", subject);
        mov.addObject("edit", true);
        mov.setViewName("subjectAddAndUpdate");
        return mov;
    }

    @RequestMapping(value = {"/subject/update/{id}"}, method = RequestMethod.POST)
    public ModelAndView updateSubject(@PathVariable Long id,@Valid Subject subject, BindingResult result){
        if (result.hasErrors()){
            ModelAndView mov = new ModelAndView();
            mov.addObject("edit", true);
            mov.setViewName("subjectAddAndUpdate");
            return mov;
        }
        subjectService.update(subject);
        return new ModelAndView(new RedirectView("/university/subjects"));
    }

    @RequestMapping(value = {"/subject/delete/{id}"}, method = RequestMethod.GET)
    public ModelAndView subjectDelete(@PathVariable Long id){
        Subject subject = subjectService.findById(id);
        subject.setDeleted(true);
        subjectService.update(subject);
        return new ModelAndView(new RedirectView("/university/subjects"));
    }

}
