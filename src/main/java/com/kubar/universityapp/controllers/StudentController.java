package com.kubar.universityapp.controllers;

import com.kubar.universityapp.model.Student;
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
public class StudentController {

	@Autowired
	@Qualifier("studentService")
	GenericService<Student> studentService;



	@RequestMapping(value = {"/","/students"}, method = RequestMethod.GET)
	public ModelAndView students(){
		ModelAndView mov = new ModelAndView();
		List<Student> list = studentService.getAll();
		mov.addObject("studentList", list);
		mov.setViewName("students");
		return mov;
	}

	@RequestMapping(value = {"/studentAdd"}, method = RequestMethod.GET)
	public ModelAndView studentAddGet(){
		ModelAndView mov = new ModelAndView();
		Student student = new Student();
		mov.addObject("student", student);
		mov.setViewName("studentAddAndUpdate");
		return mov;
	}

	@RequestMapping(value = {"/studentAdd"}, method = RequestMethod.POST)
	public ModelAndView studentAddPost(@Valid Student student, BindingResult result){
		if (result.hasErrors()){
			return new ModelAndView("studentAddAndUpdate");
		}
		studentService.save(student);
		return new ModelAndView(new RedirectView("/university/students"));
	}

	@RequestMapping(value = {"/profile/student/{id}"}, method = RequestMethod.GET)
	public ModelAndView profile(@PathVariable Long id){
		ModelAndView mov = new ModelAndView();
		Student student = studentService.findById(id);
		mov.addObject("student", student);
		mov.setViewName("profile");
		return mov;
	}

	@RequestMapping(value = {"/student/update/{id}"}, method = RequestMethod.GET)
	public ModelAndView studentUpdateGet(@PathVariable Long id){
		ModelAndView mov = new ModelAndView();
		Student student = studentService.findById(id);
		mov.addObject("student", student);
		mov.addObject("edit", true);
		mov.setViewName("studentAddAndUpdate");
		return mov;
	}

	@RequestMapping(value = {"/student/update/{id}"}, method = RequestMethod.POST)
	public ModelAndView studentUpdatePost(@Valid Student student, BindingResult result){
		if (result.hasErrors()){
			ModelAndView mov = new ModelAndView();
			mov.addObject("edit", true);
			mov.setViewName("studentAddAndUpdate");
			return mov;
		}
		studentService.update(student);
		return new ModelAndView(new RedirectView("/university/profile/student/"+student.getId()));
	}

	@RequestMapping(value = {"/student/delete/{id}"}, method = RequestMethod.GET)
	public ModelAndView studentDelete(@PathVariable Long id){
		Student student = studentService.findById(id);
		student.setDeleted(true);
		studentService.update(student);
		return new ModelAndView(new RedirectView("/university/students"));
	}

}
