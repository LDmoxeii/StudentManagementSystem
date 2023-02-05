import Calss.Student;
import Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
@SuppressWarnings("all")
public class Function {

    public static Connection connection;
    public static PreparedStatement preparedStatement01;
    public static PreparedStatement preparedStatement02;
    public static ResultSet resultSet;
    public static Student student;
    public static String dateName;
    public static int dateAge;
    public static int field;
    public static int id;
    public static int idx;
    public static Scanner scanner = new Scanner(System.in);
    private static String sql;
    private static String sqlSelect;

    public static boolean deleteById() {

        System.out.println("删除定位:");
        id = scanner.nextInt();
        try {
            connection = JDBCUtils.getConnection();
            sql = "delete from student where id=?";
            preparedStatement01 = connection.prepareStatement(sql);
            preparedStatement01.setInt(1, id);
            idx = preparedStatement01.executeUpdate();

            if (idx > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement01);
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Student insert() {

        System.out.println("name,age:");

        try {
            connection = JDBCUtils.getConnection();
            dateName = scanner.next();
            dateAge = scanner.nextInt();
            sql = "insert into student(name,age)value (?,?)";
            preparedStatement01 = connection.prepareStatement(sql);
            preparedStatement01.setString(1, dateName);
            preparedStatement01.setInt(2, dateAge);
            preparedStatement01.executeUpdate();

            sqlSelect = "select * from student order by id DESC limit 1";
            preparedStatement02 = connection.prepareStatement(sqlSelect);
            resultSet = preparedStatement02.executeQuery();
            while (resultSet.next()) {
                student = new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
                return student;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement01, resultSet);
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Student select() {
        System.out.println("输入查询号码");
        id = scanner.nextInt();
        try {
            connection = JDBCUtils.getConnection();

            sql = "select * from student where id=?";

            preparedStatement01 = connection.prepareStatement(sql);

            preparedStatement01.setInt(1, id);

            resultSet = preparedStatement01.executeQuery();
            while (resultSet.next()) {
                student = new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
                return student;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement01, resultSet);
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Student updateById() {

        System.out.println("可修改字段名:\n1.name\n2.age\n3.name&age");
        System.out.println("输入修改字段，修改信息，定位id");
        field = scanner.nextInt();

        try {
            connection = JDBCUtils.getConnection();
            if (field == 1) {
                dateName = scanner.next();
                id = scanner.nextInt();
                sql = "update student set name =? where id=?;";
                preparedStatement01 = connection.prepareStatement(sql);
                preparedStatement01.setString(1, dateName);
                preparedStatement01.setInt(2, id);
                preparedStatement01.executeUpdate();
            }
            if (field == 2) {
                dateAge = scanner.nextInt();
                id = scanner.nextInt();
                sql = "update student set age =? where id=?;";
                preparedStatement01 = connection.prepareStatement(sql);
                preparedStatement01.setInt(1, dateAge);
                preparedStatement01.setInt(2, id);
                preparedStatement01.executeUpdate();
            }
            if (field == 3) {
                dateName = scanner.next();
                dateAge = scanner.nextInt();
                id = scanner.nextInt();
                sql = "update student set name =?,age=? where id=?;";
                preparedStatement01 = connection.prepareStatement(sql);
                preparedStatement01.setString(1, dateName);
                preparedStatement01.setInt(2, dateAge);
                preparedStatement01.setInt(3, id);
                preparedStatement01.executeUpdate();
            }

            sqlSelect = "select * from student where id =?";
            preparedStatement02 = connection.prepareStatement(sqlSelect);
            preparedStatement02.setInt(1, id);
            resultSet = preparedStatement02.executeQuery();
            while (resultSet.next()) {
                student = new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
                return student;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement01, resultSet);
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ArrayList<Student> selectAll() {
        ArrayList<Student> arrayList = new ArrayList<Student>();
        try {
            connection = JDBCUtils.getConnection();
            sql = "select * from student";
            preparedStatement01 = connection.prepareStatement(sql);
            resultSet = preparedStatement01.executeQuery();

            while (resultSet.next()) {
                arrayList.add(new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement01);
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

}
