package com.kubar.universityapp.editors;

import com.kubar.universityapp.model.Attend;
import com.kubar.universityapp.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class AttendEditor extends PropertyEditorSupport{

    @Autowired
    @Qualifier("attendService")
    private GenericService<Attend> attendService;

    @Override
    public void setAsText(String text){
        try {
            Attend attend = attendService.findById(Long.parseLong(text));
            this.setValue(attend);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}