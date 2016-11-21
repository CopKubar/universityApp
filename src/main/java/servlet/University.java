package servlet;

import assistant.CheckInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import spring.domain.Attend;
import spring.domain.Rating;
import spring.domain.Student;
import spring.domain.Subject;
import spring.service.GenericService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class University extends HttpServlet {

    @Autowired
    @Qualifier("studentService")
    GenericService<Student> studentService;

    @Autowired
    @Qualifier("subjectService")
    GenericService<Subject> subjectService;

    @Autowired
    @Qualifier("attendService")
    GenericService<Attend> attendService;

    @Autowired
    @Qualifier("ratingService")
    GenericService<Rating> ratingService;

    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        response.setContentType("text/html; charset=UTF-8");
        doAllRequests(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        doAllRequests(request, response);
    }

    private void doAllRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Проверка, есть ли в запросе параметр url
        String url = request.getParameter("url");

        //Если параметр url отсутствует, то ему присваивается начальная страница
        if (url == null){
            url = "students";
        }

                //Переменная с именем страницы, на которую надо сделать перессылку
                String jsp = null;

                //Действия со студентом

                //Перессылка на страницу со списком студентов
                if (url.equals("students")) {
                    jsp = printStudentsMainPage(request);
                }

                //Перессылка на страницу добавления студента
                else if (url.equals("studentAdd")){
                    jsp = printStudentAddPage();
                }

                //Обработка параметров из формы добавления студента и переадресация на страницу со списком студентов в случае валидности данных
                else if (url.equals("studentAddForm")){
                    jsp = processingStudentAddForm(request);
                }

                //Перессылка на страницу обновления данных студента
                else if (url.equals("studentUpdate")){
                    jsp = printStudentUpdatePage(request);
                }

                else if(url.equals("errorStudentUpdate")){
                    jsp = printErrorStudentUpdate();
                }

                //Обработка параметров из формы обновления студента и переадресация на профиль обновленного студента в случае валидности данных
                else if (url.equals("studentUpdateForm")){
                    jsp = processingStudentUpdateForm(request);
                }

                //Перессылка на страницу подтверждения удаления студента
                else if (url.equals("studentDelete")){
                    jsp = printStudentDeletePage(request);
                }

                //Обработка параметров из формы удаления студента и переадресация на страницу со списком студентов в случае подтверждения
                else if (url.equals("studentDeleteForm")){
                    jsp = processingStudentDeleteForm(request);
                }

                //Перессылка на профиль студента
                else if (url.equals("profile")){
                    jsp = printProfileMainPage(request);
                }

                //Действия с учебным предметом

                //перессылка на страницу со списком учебных предметов
                else if (url.equals("subjects")){
                    jsp = printSubjectsMainPage(request);
                }

                //перессылка на страницу добавления предмета
                else if (url.equals("subjectAdd")){
                    jsp = printSubjectAddPage();
                }

                //Обработка данных добавления предмета и переадресация на список предметов в случае валидности данных
                else if (url.equals("subjectAddForm")){
                    jsp = processingSubjectAddForm(request);
                }

                //Перессылка на страницу редактирования предмета
                else if (url.equals("subjectUpdate")){
                    jsp = printSubjectUpdatePage(request);
                }

                //Обработка данных обновления предмета и переадресация на список предметов в случае валидности данных
                else if (url.equals("subjectUpdateForm")){
                    jsp = processingSubjectUpdateForm(request);
                }

                //Перессылка на страницу удаления предмета
                else if(url.equals("subjectDelete")){
                    jsp = printSubjectDeletePage(request);
                }

                //Обработка данных удаления предмета и переадресация на список предметов
                else if(url.equals("subjectDeleteForm")){
                    jsp = processingSubjectDeleteForm(request);
                }

                //Действия с назначениями

                //Перессылка на форму назначения предмета студенту
                else if (url.equals("attendAdd")){
                    jsp = printAttendAddPage(request);
                }

                //Обработка параметров из формы назначения предмета и переадресация на профиль студента
                else if (url.equals("attendAddForm")){
                    jsp = processingAttendAddForm(request);
                }

                //Перессылка на страницу удаления назначения
                else if (url.equals("attendDelete")){
                    jsp = printAttendDeletePage(request);
                }

                //Обработка данных удаления назначения и переадресация на профильм студента
                else if (url.equals("attendDeleteForm")){
                    jsp = processingAttendDeleteForm(request);
                }

                //Действия с оценками

                //Перессылка на страницу добавления оценки
                else if (url.equals("ratingAdd")){
                    jsp = printRatingAddPage(request);
                }

                //Обработка данных добавления оценки и переадресация на профиль студента
                else if (url.equals("ratingAddForm")){
                    jsp = processingRatingAddForm(request);
                }

                forward(jsp, request, response);
   }

   private void forward(String to, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.getServletContext().getRequestDispatcher(to).forward(request, response);
   }

   //Student методы

   //Страница со списком студентов
   private String printStudentsMainPage(HttpServletRequest request) {
       request.setAttribute("list", studentService.getAll());
       return "/jsp/students.jsp";
   }

   //Страница с формой добавления студента
    private String printStudentAddPage(){
        return "/jsp/studentAdd.jsp";
    }

    //Обработка данных добавления студента
    private String processingStudentAddForm(HttpServletRequest request) {
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String entranceYear=request.getParameter("entranceYear");
        if (CheckInput.checkStudentName(firstName) &&CheckInput.checkStudentName(lastName)&&CheckInput.checkStudentDate(entranceYear)){
            studentService.save(new Student(firstName, lastName, Integer.parseInt(entranceYear)));
            return printStudentsMainPage(request);
        }else {
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("entranceYear", entranceYear);

            return "/jsp/errorStudentAdd.jsp";
        }
    }

    //Страница с формой обновления студента
   private String printStudentUpdatePage(HttpServletRequest request){
       Long studentId=Long.parseLong(request.getParameter("studentId"));
       Student student = studentService.findById(studentId);
       request.setAttribute("student", student);
       return "/jsp/studentUpdate.jsp";
   }

   private String printErrorStudentUpdate(){
       return "/jsp/errorStudentUpdate.jsp";
   }

   //Обработка данных обновления студента
    private String processingStudentUpdateForm(HttpServletRequest request) {
        Long studentId= Long.parseLong(request.getParameter("studentId"));
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String entranceYear=request.getParameter("entranceYear");
        if (CheckInput.checkStudentName(firstName) && CheckInput.checkStudentName(lastName) && CheckInput.checkStudentDate(entranceYear)){
            Student student = new Student(firstName, lastName, Integer.parseInt(entranceYear));
            student.setId(studentId);
            studentService.update(student);
            return printProfileMainPage(request);
        }
        else {
            request.setAttribute("studentId", studentId);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("entranceYear", entranceYear);
            return "/jsp/errorStudentUpdate.jsp";
        }
    }

    //Страница с формой удаления студента
   private String printStudentDeletePage(HttpServletRequest request){
       Long studentId=Long.parseLong(request.getParameter("studentId"));
       request.setAttribute("studentId", studentId);
       return "/jsp/studentDelete.jsp";
   }

    //Обработка данных удаления студента
   private String processingStudentDeleteForm(HttpServletRequest request) {
       Long studentId=Long.parseLong(request.getParameter("studentId"));
       String unswer=request.getParameter("unswer");
       if (unswer.equals("yes")){
           Student student = studentService.findById(studentId);
           student.setDeleted(true);
           studentService.update(student);
           return printStudentsMainPage(request);
       }
       else {
           return printProfileMainPage(request);
       }
   }

   //Страница с профилем студента
    private String printProfileMainPage(HttpServletRequest request){
        Long studentId=Long.parseLong(request.getParameter("studentId"));
        Student student=studentService.findById(studentId);
        request.setAttribute("student", student);
        return "/jsp/profile.jsp";
    }

    //Страница со списком предметов
    private String printSubjectsMainPage(HttpServletRequest request){
        request.setAttribute("subjectList", subjectService.getAll());
        return "/jsp/subjects.jsp";
    }

    //Страница с формой добавления предмета
    private String printSubjectAddPage(){
        return "/jsp/subjectAdd.jsp";
    }

    //Обработка данных добавления предмета
    private String processingSubjectAddForm(HttpServletRequest request) throws IOException, ServletException {
        String title=request.getParameter("title");
            if (CheckInput.checkSubjectTitle(title)) {
                subjectService.save(new Subject(title));
                request.setAttribute("subjectList", subjectService.getAll());
                return "/jsp/subjects.jsp";
            }else {
                request.setAttribute("title", title);
                return "/jsp/errorSubjectAdd.jsp";
            }
    }

    //Страница обновления предмета
    private String printSubjectUpdatePage(HttpServletRequest request){
        Long subjectId=Long.parseLong(request.getParameter("subjectId"));
        Subject subject=subjectService.findById(subjectId);
        request.setAttribute("subject", subject);
        return "/jsp/subjectUpdate.jsp";
    }

    //Обработка данных обновления предмета
    private String processingSubjectUpdateForm(HttpServletRequest request) throws IOException, ServletException {
        Long subjectId=Long.parseLong(request.getParameter("subjectId"));
        String title=request.getParameter("title");
        Subject subject=new Subject(title);
        subject.setId(subjectId);
        if (CheckInput.checkSubjectTitle(title)){
            subjectService.update(subject);
            return printSubjectsMainPage(request);
        }
        else {
            request.setAttribute("title", title);
            request.setAttribute("subjectId", subjectId);
            return "/jsp/errorSubjectUpdate.jsp";
        }
    }

    //Страница удаления предмета
    private String printSubjectDeletePage(HttpServletRequest request){
        Long subjectId=Long.parseLong(request.getParameter("subjectId"));
        request.setAttribute("subjectId", subjectId);
        return "/jsp/subjectDelete.jsp";
    }

    //Обработка данных удаления предмета
    private String processingSubjectDeleteForm(HttpServletRequest request) throws IOException, ServletException {
        Long subjectId=Long.parseLong(request.getParameter("subjectId"));
        String unswer=request.getParameter("unswer");
        if (unswer.equals("yes")){
            Subject subject=subjectService.findById(subjectId);
            subject.setDeleted(true);
            subjectService.update(subject);
            List<Attend>list = attendService.getAll();
            for(Attend a: list){
                if (a.getSubject().getId()==subjectId){
                    a.setDeleted(true);
                    attendService.update(a);
                }
            }
            return printSubjectsMainPage(request);
        }
        else {
            return printSubjectsMainPage(request);
        }
    }

    //Attend методы

    //Страница добавления назначения предмета студенту
    private String printAttendAddPage(HttpServletRequest request){
        Long studentId=Long.parseLong(request.getParameter("studentId"));
        Student student=studentService.findById(studentId);
        List<Attend> attends=student.getAttends();
        List<Subject>subjects=new ArrayList<>();
        for (Subject s: subjectService.getAll()){
            int count=0;
            for(Attend a: attends){
                if (s.getId()==a.getSubject().getId()){
                    if(a.getDeleted()) {
                        count++;
                    }
                }
            }
            if (count==0){
                subjects.add(s);
            }
        }
        request.setAttribute("subjectList", subjects);
        request.setAttribute("studentId", studentId);
        return "/jsp/attendAdd.jsp";
    }

    //Обработка данных добавления назначения
    private String processingAttendAddForm(HttpServletRequest request)  {
        Long studentId=Long.parseLong(request.getParameter("studentId"));
        Long subjectId=Long.parseLong(request.getParameter("subjectId"));
        Student student=studentService.findById(studentId);
        Subject subject=subjectService.findById(subjectId);
        attendService.save(new Attend(student, subject));
        return printProfileMainPage(request);
    }

    //Страница удаления назначения
    private String printAttendDeletePage(HttpServletRequest request){
        Long attendId=Long.parseLong(request.getParameter("attendId"));
        request.setAttribute("attendId", attendId);
        return "/jsp/attendDelete.jsp";
    }

    //Обработка данных удаления назначения
    private String processingAttendDeleteForm(HttpServletRequest request) {
        Long attendId=Long.parseLong(request.getParameter("attendId"));
        String unswer=request.getParameter("unswer");
        Attend attend=attendService.findById(attendId);
        if (unswer.equals("yes")){
            attend.setDeleted(true);
            attendService.update(attend);
            Student student=studentService.findById(attend.getStudent().getId());
            request.setAttribute("student", student);
            return "/jsp/profile.jsp";
        }
        else {
            Student student=studentService.findById(attend.getStudent().getId());
            request.setAttribute("student", student);
            return "/jsp/profile.jsp";
        }
    }

    //Страница добавления оценки
    private String printRatingAddPage(HttpServletRequest request){
        Long attendId=Long.parseLong(request.getParameter("attendId"));
        request.setAttribute("attendId", attendId);
        return "/jsp/ratingAdd.jsp";
    }

    //Обработка данных добавления оценки
    private String processingRatingAddForm(HttpServletRequest request) {
        Long attendId=Long.parseLong(request.getParameter("attendId"));
        String mark=request.getParameter("mark");
        Attend attend=attendService.findById(attendId);
        ratingService.save(new Rating(attend, Integer.parseInt(mark)));
        Student student=studentService.findById(attend.getStudent().getId());
        request.setAttribute("student", student);
        return "/jsp/profile.jsp";
    }
}
