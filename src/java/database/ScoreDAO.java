/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Group;
import model.Point;
import model.Score;
import model.Student;

/**
 *
 * @author trung
 */
public class ScoreDAO {

    public ArrayList<Score> selectScore(int idGroup, String idStudent) {
        ArrayList<Score> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select *\n"
                    + "from Score\n"
                    + "where idGroup = ? and idStudent = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, idGroup);
            preparedStatement.setString(2, idStudent);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idPoint = resultSet.getInt(1);
                StudentDAO studentDAO = new StudentDAO();
                GroupDAO groupDAO = new GroupDAO();
                Student student = studentDAO.selectStudent(idStudent);
                Group group = groupDAO.selectIdGroup(idGroup);
                PointDAO pointDAO = new PointDAO();
                Point point = pointDAO.selectById(resultSet.getInt("idPoint"));
                float value = resultSet.getFloat("value");
                Score score = new Score(idGroup, student, group, point, value);
                list.add(score);
                
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return list;
    }
}
