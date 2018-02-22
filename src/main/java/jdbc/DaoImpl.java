package jdbc;

import jdbc.dao.Dao;
import jdbc.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DaoImpl implements Dao {
    private Connection conn;
    private Statement st;

    private void initConnection() throws SQLException {
        final String HOST = "localhost";
        final int PORT = 3306;
        final String DB_NAME = "aj";
        final String USER_NAME = "root";
        final String PASSWORD = "admin";
        String dburl = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;

        conn = DriverManager.getConnection(dburl, USER_NAME, PASSWORD);
        DatabaseMetaData dbmd = conn.getMetaData();
        System.out.printf("Połączenie nawiązane poprzez: %s%n", dbmd.getDriverName());
        st = conn.createStatement();
    }

    private void createTable() throws SQLException {
        System.out.println("\n---> TWORZENIE TABELI...");
        final String SQL_CREATE = "CREATE TABLE kursy("
                + "id INT NOT NULL AUTO_INCREMENT,"  // Autoinkrementacja
                + "code VARCHAR(255),"
                + "name VARCHAR(255),"
                + "days INT,"
                + "date VARCHAR(255),"
                + "PRIMARY KEY (id))";
        st.executeUpdate(SQL_CREATE);
    }

    @Override
    public void open() throws SQLException {
        initConnection();
        createTable();
    }

    @Override
    public void saveCourse(Course... courses) throws SQLException {
        System.out.println("\n+++++ WSTAWIANIE DANYCH +++++");
        for (Course c : courses) {
            String insert = String.format("INSERT INTO kursy VALUES (null, '%s','%s', '%d', '%s')",
//                    c.getId(),
                    c.getCode(),
                    c.getName().toString(),
                    c.getDays(),
                    c.getDate());
            System.out.print(c + "\n ");
            st.executeUpdate(insert);
        }
    }

    @Override
    public List<Course> getCourse() throws SQLException {
        System.out.println("\n\n----- ODCZYT DANYCH Z TABELI -----");
        final String SQL_SELECT = "SELECT * FROM kursy";
        List<Course> courses = new ArrayList<>();

        try (ResultSet rs = st.executeQuery(SQL_SELECT)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();
            System.out.printf("Tabela 'kursy' zawiera %d kolumn o nazwach: ", columns);
            for (int i = 1; i <= columns; i++) {
                System.out.print(rsmd.getColumnName(i) + " ");
            }
            System.out.println();

            while (rs.next()) {
                Course c = new Course();

                c.setId(rs.getInt(1));
                c.setCode(rs.getString(2));
                c.setName(rs.getString(3));
                c.setDays(rs.getInt(4));
                c.setDate(rs.getDate(5).toLocalDate());

                courses.add(c);
            }
        }
        return courses;
    }

    private void dropTable() throws SQLException {
        System.out.println("\nxxxxx USUNIECIE TABELI xxxxx");
        final String SQL_DROP = "DROP TABLE kursy";
        st.executeUpdate(SQL_DROP);
    }

    private void closeConnection() {
        try {
            if (st != null)
                st.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        dropTable();
        closeConnection();
    }
}
