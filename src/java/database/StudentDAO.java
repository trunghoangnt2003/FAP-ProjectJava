/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Student;

/**
 *
 * @author trung
 */
public class StudentDAO {
    public Student getLoginGoogle(String email) {
        Student user = null;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select * from [Student]"
                    + "where [email]=?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String passWord = resultSet.getString("pass");
                String name = resultSet.getNString("name");
                user = new Student(email, passWord, id, name);
            }
            JDBC.closeConnection(connection);
        } catch (SQLException e) {
        }
        return user;
    }
    public ArrayList<Student> selectAll() {
        ArrayList<Student> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select * from [Student]";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String email = resultSet.getString("email");
                String pass = resultSet.getString("pass");
                String name = resultSet.getNString("name");
                Student user = new Student(email, pass, id, name);
                list.add(user);
            }
            JDBC.closeConnection(connection);
        } catch (SQLException e) {
        }
        return list;
    }

    public Student getLogin(String email, String passWord) {
        Student user = null;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select * from [Student]"
                    + "where [email]=? AND [pass]=?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passWord);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getNString("name");
                user = new Student(email, passWord, id, name);
            }
            JDBC.closeConnection(connection);
        } catch (SQLException e) {
        }
        return user;
    }
    public Student selectStudent(String id) {
        Student user = null;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select * from [Student]"
                    + "where id = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String pass = resultSet.getString("pass");
                String name = resultSet.getNString("name");
                user = new Student(email, pass, id, name);
            }
            JDBC.closeConnection(connection);
        } catch (SQLException e) {
        }
        return user;
    }

    public int insertUser(Student user) {
        int kq = 0;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "insert into [Student] (id,email,pass,name)"
                    + "                        values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassWord());
            preparedStatement.setNString(4, user.getName());
            kq = preparedStatement.executeUpdate();

            JDBC.closeConnection(connection);
        } catch (SQLException e) {
        }
        return kq;
    }

    public int deleteUser(String id) {
        int result = 0;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "delete from [Student] where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            result = preparedStatement.executeUpdate();
            JDBC.closeConnection(connection);
        } catch (SQLException e) {
        }
        return result;
    }

    public int updatePassWord(String id, String newPass) {
        int result = 0;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "update [Student]"
                    + "set pass=?"
                    + "where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newPass);
            preparedStatement.setString(2, id);
            result = preparedStatement.executeUpdate();
            JDBC.closeConnection(connection);
        } catch (SQLException e) {
        }
        return result;
    }

}
