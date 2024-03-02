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
import model.Lecture;

/**
 *
 * @author trung
 */
public class LectureDAO {

    public Lecture selectById(int id) {
        Lecture lecture = null;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "SELECT [id]\n"
                    + "      ,[email]\n"
                    + "      ,[pass]\n"
                    + "      ,[name]\n"
                    + "  FROM [dbo].[Lecture]\n"
                    + "  WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int idLecture = resultSet.getInt(1);
                String email = resultSet.getString(2);
                String pass = resultSet.getString(3);
                String name = resultSet.getString(4);
                
                lecture = new Lecture(email, pass, idLecture+"", name);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return lecture;
    }

    public ArrayList<Lecture> selectAll() {
        ArrayList<Lecture> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select * from [Lecture]";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String email = resultSet.getString("email");
                String pass = resultSet.getString("pass");
                String name = resultSet.getNString("name");
                Lecture user = new Lecture(email, pass, id, name);
                list.add(user);
            }
            JDBC.closeConnection(connection);
        } catch (SQLException e) {
        }
        return list;
    }

    public Lecture getLogin(String email, String passWord) {
        Lecture user = null;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select * from [Lecture]"
                    + "where [email]=? AND [pass]=?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passWord);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getNString("name");
                user = new Lecture(email, passWord, id, name);
            }
            JDBC.closeConnection(connection);
        } catch (SQLException e) {
        }
        return user;
    }

    public int insertUser(Lecture user) {
        int kq = 0;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "insert into [Lecture] (id,email,pass,name)"
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
            String sql = "delete from [Lecture] where id = ?";
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
            String sql = "update [Lecture]"
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
