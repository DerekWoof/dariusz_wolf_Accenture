package jdbc.dao;

import jdbc.model.Course;
import java.sql.SQLException;
import java.util.List;

public interface Dao {

    void open() throws SQLException;

    void saveCourse(Course... courses) throws SQLException;

    List<Course> getCourse() throws SQLException;

    void close() throws SQLException;

}
