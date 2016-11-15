package servlets.universityServlet;

import accessoryClasses.CheckInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import spring.domain.Attend;
import spring.domain.Rating;
import spring.domain.Student;
import spring.domain.Subject;
import spring.service.GenericService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    private WebApplicationContext springContext;

    @Override
    public void init(final ServletConfig config) throws ServletException{
        super.init(config);
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        final AutowireCapableBeanFactory beanFactory=springContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
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

    private void doAllRequests(HttpServletRequest request, HttpServletResponse response){

        //Проверка, есть ли в запросе параметр url
        String url = request.getParameter("url");

        //Если параметр url отсутствует, то ему присваивается начальная страница
        if (url == null){
            url = "students";
        }

        try {
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
                    processingStudentAddForm(request, response);
                }

                //Перессылка на страницу обновления данных студента
                else if (url.equals("studentUpdate")){
                    jsp = printStudentUpdatePage(request);
                }

                //Обработка параметров из формы обновления студента и переадресация на профиль обновленного студента в случае валидности данных
                else if (url.equals("studentUpdateForm")){
                    processingStudentUpdateForm(request, response);
                }

                //Перессылка на страницу подтверждения удаления студента
                else if (url.equals("studentDelete")){
                    jsp = printStudentDeletePage(request);
                }

                //Обработка параметров из формы удаления студента и переадресация на страницу со списком студентов в случае подтверждения
                else if (url.equals("studentDeleteForm")){
                    processingStudentDeleteForm(request, response);
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
                    processingSubjectAddForm(request, response);
                }

                //Перессылка на страницу редактирования предмета
                else if (url.equals("subjectUpdate")){
                    jsp = printSubjectUpdatePage(request);
                }

                //Обработка данных обновления предмета и переадресация на список предметов в случае валидности данных
                else if (url.equals("subjectUpdateForm")){
                    processingSubjectUpdateForm(request, response);
                }

                //Перессылка на страницу удаления предмета
                else if(url.equals("subjectDelete")){
                    jsp = printSubjectDeletePage(request);
                }

                //Обработка данных удаления предмета и переадресация на список предметов
                else if(url.equals("subjectDeleteForm")){
                    processingSubjectDeleteForm(request, response);
                }

                //Действия с назначениями

                //Перессылка на форму назначения предмета студенту
                else if (url.equals("attendAdd")){
                    jsp = printAttendAddPage(request);
                }

                //Обработка параметров из формы назначения предмета и переадресация на профиль студента
                else if (url.equals("attendAddForm")){
                    processingAttendAddForm(request, response);
                }

                //Перессылка на страницу удаления назначения
                else if (url.equals("attendDelete")){
                    jsp = printAttendDeletePage(request);
                }

                //Обработка данных удаления назначения и переадресация на профильм студента
                else if (url.equals("attendDeleteForm")){
                    processingAttendDeleteForm(request, response);
                }

                //Действия с оценками

                //Перессылка на страницу добавления оценки
                else if (url.equals("ratingAdd")){
                    jsp = printRatingAddPage(request);
                }

                //Обработка данных добавления оценки и переадресация на профиль студента
                else if (url.equals("ratingAddForm")){
                    processingRatingAddForm(request,response);
                }

                if (jsp!=null) {
                    forward(jsp, request, response);
                }

        } catch (Exception e){
            e.printStackTrace();
        }

   }

   public void forward(String to, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(to);
       dispatcher.forward(request, response);
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
    private void processingStudentAddForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String entranceYear=request.getParameter("entranceYear");
        if (CheckInput.checkStudentName(firstName) &&CheckInput.checkStudentName(lastName)&&CheckInput.checkStudentDate(entranceYear)){
            studentService.save(new Student(firstName, lastName, Integer.parseInt(entranceYear)));
            response.sendRedirect("http://localhost:8080/app/university?url=students");
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("firstName", firstName);
            session.setAttribute("lastName", lastName);
            session.setAttribute("entranceYear", entranceYear);
            response.sendRedirect("http://localhost:8080/app/university?url=studentAdd");
        }
    }

    //Страница с формой обновления студента
   private String printStudentUpdatePage(HttpServletRequest request){
       Long studentId=Long.parseLong(request.getParameter("studentId"));
       Student student = studentService.findById(studentId);
       request.setAttribute("student", student);
       return "/jsp/studentUpdate.jsp";
   }

   //Обработка данных обновления студента
    private void processingStudentUpdateForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long studentId= Long.parseLong(request.getParameter("studentId"));
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String entranceYear=request.getParameter("entranceYear");
        if (CheckInput.checkStudentName(firstName) && CheckInput.checkStudentName(lastName) && CheckInput.checkStudentDate(entranceYear)){
            Student student = new Student(firstName, lastName, Integer.parseInt(entranceYear));
            student.setId(studentId);
            studentService.update(student);
            response.sendRedirect("http://localhost:8080/app/university?url=profile&studentId="+studentId);
        }
        else {
            HttpSession session=request.getSession();
            session.setAttribute("studentId", studentId);
            session.setAttribute("firstName", firstName);
            session.setAttribute("lastName", lastName);
            session.setAttribute("entranceYear", entranceYear);
            response.sendRedirect("http://localhost:8080/app/university?url=studentUpdate&studentId="+studentId);
        }
    }

    //Страница с формой удаления студента
   private String printStudentDeletePage(HttpServletRequest request){
       Long studentId=Long.parseLong(request.getParameter("studentId"));
       request.setAttribute("studentId", studentId);
       return "/jsp/studentDelete.jsp";
   }

    //Обработка данных удаления студента
   private void processingStudentDeleteForm(HttpServletRequest request , HttpServletResponse response) throws IOException{
       Long studentId=Long.parseLong(request.getParameter("studentId"));
       String unswer=request.getParameter("unswer");
       if (unswer.equals("yes")){
           Student student = studentService.findById(studentId);
           student.setDeleted(true);
           studentService.update(student);
           response.sendRedirect("http://localhost:8080/app/university?url=students");
       }
       else {
           response.sendRedirect("http://localhost:8080/app/university?url=profile&studentId="+studentId);
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
    private void processingSubjectAddForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title=request.getParameter("title");
        if (CheckInput.checkSubjectTitle(title)){
            subjectService.save(new Subject(title));
            response.sendRedirect("http://localhost:8080/app/university?url=subjects");
        }
        else {
            HttpSession session=request.getSession();
            session.setAttribute("title", title);
            response.sendRedirect("http://localhost:8080/app/university?url=subjectAdd");
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
    private void processingSubjectUpdateForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long subjectId=Long.parseLong(request.getParameter("subjectId"));
        String title=request.getParameter("title");
        Subject subject=new Subject(title);
        subject.setId(subjectId);
        if (CheckInput.checkSubjectTitle(title)){
            subjectService.update(subject);
            response.sendRedirect("http://localhost:8080/app/university?url=subjects");
        }
        else {
            HttpSession session=request.getSession();
            session.setAttribute("title", title);
            response.sendRedirect("http://localhost:8080/app/university?url=subjectUpdate&subjectId="+subjectId);
        }
    }

    //Страница удаления предмета
    private String printSubjectDeletePage(HttpServletRequest request){
        Long subjectId=Long.parseLong(request.getParameter("subjectId"));
        request.setAttribute("subjectId", subjectId);
        return "/jsp/subjectDelete.jsp";
    }

    //Обработка данных удаления предмета
    private void processingSubjectDeleteForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long subjectId=Long.parseLong(request.getParameter("subjectId"));
        String unswer=request.getParameter("unswer");
        if (unswer.equals("yes")){
            Subject subject=subjectService.findById(subjectId);
            subject.setDeleted(true);
            subjectService.update(subject);
            response.sendRedirect("http://localhost:8080/app/university?url=subjects");
        }
        else {
            response.sendRedirect("http://localhost:8080/app/university?url=subjects");
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
                    count++;
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
    private void processingAttendAddForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long studentId=Long.parseLong(request.getParameter("studentId"));
        Long subjectId=Long.parseLong(request.getParameter("subjectId"));

        Student student=studentService.findById(studentId);
        Subject subject=subjectService.findById(subjectId);

        attendService.save(new Attend(student, subject));

        response.sendRedirect("http://localhost:8080/university?url=profile&studentId="+studentId);
    }

    //Страница удаления назначения
    private String printAttendDeletePage(HttpServletRequest request){
        Long attendId=Long.parseLong(request.getParameter("attendId"));
        request.setAttribute("attendId", attendId);
        return "/jsp/attendDelete.jsp";
    }

    //Обработка данных удаления назначения
    private void processingAttendDeleteForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long attendId=Long.parseLong(request.getParameter("attendId"));
        String unswer=request.getParameter("unswer");
        Attend attend=attendService.findById(attendId);
        if (unswer.equals("yes")){
            attend.setDeleted(true);
            attendService.update(attend);
            response.sendRedirect("http://localhost:8080/app/university?url=profile&studentId="+attend.getStudent().getId());
        }
        else {
            response.sendRedirect("http://localhost:8080/app/university?url=profile&studentId="+attend.getStudent().getId());
        }
    }

    //Страница добавления оценки
    private String printRatingAddPage(HttpServletRequest request){
        Long attendId=Long.parseLong(request.getParameter("attendId"));

        List<Integer>marks=new ArrayList<>();
        for (int n=1; n<11; n++){
            marks.add(n);
        }

        request.setAttribute("attendId", attendId);
        request.setAttribute("marks", marks);

        return "/jsp/ratingAdd.jsp";
    }

    //Обработка данных добавления оценки
    private void processingRatingAddForm(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Long attendId=Long.parseLong(request.getParameter("attendId"));
        String mark=request.getParameter("mark");
        Attend attend=attendService.findById(attendId);
        ratingService.save(new Rating(attend, Integer.parseInt(mark)));
        response.sendRedirect("http://localhost:8080/university?url=profile&studentId="+attend.getStudent().getId());
    }

}
