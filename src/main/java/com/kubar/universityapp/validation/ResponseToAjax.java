package com.kubar.universityapp.validation;

import com.kubar.universityapp.model.Student;
import com.kubar.universityapp.model.Subject;

public class ResponseToAjax {

    private boolean hasError = false;
    private String status = null;
    private Subject subject = null;
    private Student student = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
