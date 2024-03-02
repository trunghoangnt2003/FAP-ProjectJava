/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Student;

/**
 *
 * @author trung
 */
public class inGroupDAO {

    public ArrayList<Student> selectStudentsByIdGroup(int idGroup) {
        ArrayList<Student> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select idStudent\n"
                    + "from inGroup\n"
                    + "where idGroup = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, idGroup);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String idStudent = resultSet.getString(1);
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.selectStudent(idStudent);
                list.add(student);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return list;

    }
}
