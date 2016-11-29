package com.kubar.universityapp.editors;

import com.kubar.universityapp.model.Subject;
import com.kubar.universityapp.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class SubjectEditor extends PropertyEditorSupport {

    @Autowired
    @Qualifier("subjectService")
    private GenericService<Subject> subjectService;

    @Override
    public void setAsText(String text){
        try {
            Subject subject = subjectService.findById(Long.parseLong(text));
            this.setValue(subject);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}