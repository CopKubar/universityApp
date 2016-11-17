package assistant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInput {

    //Имя и фамилия может содержать буквы латинского алфавита, длинна строки 2-15 символа
    private static Pattern pForStudentNames=Pattern.compile("^[А-Яа-я]{2,15}$");

    //Год поступления состоит только из цифр, в диапазоне 1970-2016 года
    private static Pattern pForStudentDate=Pattern.compile("^(19[7-9]\\d|20[0-1][0-9])$");

    /*Название учебного предмета может содержать буквы латинского и английского алфавита,цифры, знак пробела,
        первым символом не может быть пробел и цифра, длинна строки 3-80 символов
         */
    private static Pattern pForSubjectTitle=Pattern.compile("^[A-Za-zа-яА-Я][А-Яа-яA-Za-z 0-9]{3,80}$");

    public static boolean checkStudentName(String name){
        Matcher m=pForStudentNames.matcher(name);
        return m.matches();
    }

    public static boolean checkStudentDate(String date){
        Matcher m=pForStudentDate.matcher(date);
        return m.matches();
    }

    public static boolean checkSubjectTitle(String title){
        Matcher m=pForSubjectTitle.matcher(title);
        return m.matches();
    }
}