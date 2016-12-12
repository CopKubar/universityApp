package com.kubar.universityapp.controllers;

import com.kubar.universityapp.model.Subject;
import com.kubar.universityapp.service.GenericService;
import com.kubar.universityapp.validation.ResponseToAjax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
        mov.addObject("subject", new Subject());
        mov.setViewName("subjects");
        return mov;
    }


    @RequestMapping(value = "/subjectAdd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseToAjax saveSubject(@Valid Subject subject, BindingResult result){
        ResponseToAjax response = new ResponseToAjax();
        if (result.hasErrors()){
            response.setHasError(true);
            String errorString="";
            for (ObjectError error: result.getAllErrors()){
                errorString+=error.getDefaultMessage()+"\n";
            }
            response.setStatus(errorString);
            response.setSubject(subject);
            return response;
        }else {
            response.setHasError(false);
            response.setStatus("Предмет "+subject.getTitle()+" успешно добавлен!");
            subjectService.save(subject);
            response.setSubject(subject);
            return response;
        }
    }


    @RequestMapping(value = {"/subject/update/{id}"}, method = RequestMethod.GET)
    public ModelAndView subjectUpdate(@PathVariable Long id){
        ModelAndView mov = new ModelAndView();
        Subject subject = subjectService.findById(id);
        mov.addObject("subject", subject);
        mov.setViewName("subjectUpdate");
        return mov;
    }

    @RequestMapping(value = {"/subject/update/id"}, method = RequestMethod.POST)
    public ModelAndView updateSubject(@Valid Subject subject, BindingResult result){
        if (result.hasErrors()){
            System.out.println();
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
