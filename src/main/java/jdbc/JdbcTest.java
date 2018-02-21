package jdbc;

import jdbc.model.Course;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class JdbcTest {

    static {
        try {
            System.out.println("---> REJESTROWANIE STEROWNIKA...");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Niezarejestrowany sterownik");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Course c1 = new Course(1, "44-026", "Altkom - C#", 14, new Date(2018 - 2 - 8));
        Course c2 = new Course(2, "44-030", "Accenture - .NET", 28, new Date(2018 - 3 - 9));
        Course c3 = new Course(3, "44-026", "Altkom - PHP", 36, new Date(2018 - 4 - 10));
        Course c4 = new Course(4, "44-030", "Accenture - C++", 60, new Date(2018 - 5 - 11));
        Course c5 = new Course(5, "44-030", "Accenture - JAVA", 90, new Date(2018 - 6 - 12));


        DaoImpl dao = new DaoImpl();
        try {
            dao.open();
            dao.saveCourse(c1,c2,c3,c4,c5);
            List<Course> courses = dao.getCourse();
            System.out.println("\n " + courses);
            dao.close();
        } catch (SQLException e) {
            System.out.print("Błąd bazy danych: " + e.getMessage());
        }
    }
}
