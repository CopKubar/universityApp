package com.kubar.universityapp.controllers;


import com.kubar.universityapp.editors.StudentEditor;
import com.kubar.universityapp.editors.SubjectEditor;
import com.kubar.universityapp.model.Attend;
import com.kubar.universityapp.model.Student;
import com.kubar.universityapp.model.Subject;
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
public class AttendController {

    @Autowired
    @Qualifier("attendService")
    GenericService<Attend> attendService;

    @Autowired
    @Qualifier("studentService")
    GenericService<Student> studentService;

    @Autowired
    @Qualifier("subjectService")
    GenericService<Subject> subjectService;

    @Autowired
    @Qualifier("studentEditor")
    StudentEditor studentEditor;

    @Autowired
    @Qualifier("subjectEditor")
    SubjectEditor subjectEditor;

    @RequestMapping(value = {"/attend/add/{id}"}, method = RequestMethod.GET)
    public ModelAndView attendAdd(@PathVariable Long id){
        ModelAndView mov = new ModelAndView();
        Student student = studentService.findById(id);
        List<Attend> studentAttend = student.getAttends();
        List<Subject> subjects = subjectService.getAll();
        List<Subject> studentSubjects = new ArrayList<>();
        for (Attend a: studentAttend){
            studentSubjects.add(a.getSubject());
        }
        subjects.removeAll(studentSubjects);
        mov.addObject("subjectList", subjects);
        mov.addObject("studentId", student.getId());
        mov.addObject("attend", new Attend());
        mov.setViewName("attendAdd");
        return mov;
    }

    @RequestMapping(value = {"/attend/add/attendSave"}, method = RequestMethod.POST)
    public ModelAndView attendSave(@ModelAttribute Attend attend, @RequestParam("studentId") String studentId, @RequestParam("subject") String subjectId){
        Student student = studentService.findById(Long.parseLong(studentId));
        Subject subject = subjectService.findById(Long.parseLong(subjectId));
        attend.setSubject(subject);
        attend.setStudent(student);
        attendService.save(attend);
        return new ModelAndView(new RedirectView("/university/student/profile/"+studentId));
    }

    @RequestMapping(value = {"/attend/delete/{id1}"}, method = RequestMethod.GET)
    public ModelAndView attendDelete(@PathVariable Long id1){
        Attend attend = attendService.findById(id1);
        attend.setDeleted(true);
        attendService.update(attend);
        return new ModelAndView(new RedirectView("/university/student/profile/"+attend.getStudent().getId()));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Student.class, studentEditor);
        binder.registerCustomEditor(Subject.class, subjectEditor);
    }
}
