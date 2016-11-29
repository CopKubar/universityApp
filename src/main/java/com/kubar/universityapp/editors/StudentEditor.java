package com.kubar.universityapp.editors;

import com.kubar.universityapp.model.Student;
import com.kubar.universityapp.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class StudentEditor  extends PropertyEditorSupport{

    @Autowired
    @Qualifier("studentService")
    private GenericService<Student>studentService;

    @Override
    public void setAsText(String text){
        try {
            Student student = studentService.findById(Long.parseLong(text));
            this.setValue(student);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
