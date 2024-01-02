import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student {
    public void CreateDatabse() {
        try {
            // Register the MySQL JDBC driver

            // 2.Create a connection
            String url = "jdbc:mysql://localhost:3306/";
            String username = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("connected to database");

            // 3.write statement
            Statement st = connection.createStatement();
            String query = "create database db2";

            // 4.database execute
            boolean bl = st.execute(query);
            System.out.println("database created successfully" + bl);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            // Register the MySQL JDBC driver

            // 2.Create a connection
            String url = "jdbc:mysql://localhost:3306/db2";
            String username = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("connected to database");

            // 3.write statement
            Statement st = connection.createStatement();
            String query = """
                    CREATE TABLE student (
                        sid INT PRIMARY KEY,
                        sname VARCHAR(255) NOT NULL,
                        sroll INT NOT NULL,
                        smarks DECIMAL(5, 2) NOT NULL
                    )
                    """;

            // 4.database execute
            boolean bl = st.execute(query);
            System.out.println("database created successfully" + bl);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createData() {
        try {
            // Register the MySQL JDBC driver

            // 2.Create a connection
            String url = "jdbc:mysql://localhost:3306/";
            String db = "db2";
            String username = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url + db, username, password);
            System.out.println("connected to database");

            // 3.write statement
            String query = """
                    INSERT INTO student (sid,sname,sroll,smarks) VALUE (?,?,?,?)
                    """;
            PreparedStatement prst = connection.prepareStatement(query);
            prst.setInt(1, 3);
            prst.setString(2, "bummy");
            prst.setInt(3, 32);
            prst.setBigDecimal(4, new BigDecimal("8.92"));

            // 4.database execute
            // prst.execut && prst.executeUpdate both working in same way?
            prst.executeUpdate();
            System.out.println("data inserted successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        try {
            String url = "jdbc:mysql://localhost:3306/db2";
            String username = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("connected to database");

            // 3.write statement
            Statement st = connection.createStatement();
            String query = """
                     select * from student
                    """;

            // 4.database execute
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println("id = " + rs.getInt(1));
                System.out.println("name = " + rs.getString(2));
                System.out.println("roll-no = " + rs.getInt(3));
                System.out.println("marks = " + rs.getBigDecimal(4));
            }
            // System.out.println("database created successfully");
            System.out.println("data read successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        try {
            // Register the MySQL JDBC driver

            // 2.Create a connection
            String url = "jdbc:mysql://localhost:3306/";
            String db = "db2";
            String username = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url + db, username, password);
            System.out.println("connected to database");

            // 3.write statement
            String query = """
                    UPDATE  student set sname=? WHERE sid=?;
                    """;
            PreparedStatement prst = connection.prepareStatement(query);
            prst.setString(1, "lummy");
            prst.setInt(2, 2);

            // 4.database execute
            // prst.execut && prst.executeUpdate both working in same way
            prst.execute();
            System.out.println("data udpated successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData() {
        try {
            // Register the MySQL JDBC driver

            // 2.Create a connection
            String url = "jdbc:mysql://localhost:3306/";
            String db = "db2";
            String username = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url + db, username, password);
            System.out.println("connected to database");

            // 3.write statement
            String query = """
                    DELETE FROM  student  WHERE sid=?;
                    """;
            PreparedStatement prst = connection.prepareStatement(query);
            prst.setInt(1, 2);

            // 4.database execute
            // prst.execut && prst.executeUpdate both working in same way
            prst.execute();
            System.out.println("data delete successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
