package jdbc.model;

import java.sql.Date;

public class Course {

    private int id; // jednoznaczny identyfikator szkolenia
    private String code; // kod szkolenia
    private String name; // tytuł szkolenia
    private int days; // ilość dni
    private Date date; // data rozpoczęcia


    public Course(int id, String code, String name, int days, Date date) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.days = days;
        this.date = date;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", days=" + days +
                ", date=" + date +
                '}';
    }
}
