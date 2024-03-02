/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Category;
import model.Course;
import model.Point;


/**
 *
 * @author trung
 */
public class PointDAO {

    public Point selectById(int id) {
        Point point = null;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select *\n"
                    + "from Point\n"
                    + "where idPoint = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idPoint = resultSet.getInt(1);
                CourseDAO courseDAO = new CourseDAO();
                Course course = courseDAO.selectById(resultSet.getInt(2));
                String describe = resultSet.getString(3);
                float percent = resultSet.getFloat(4);
                int category = resultSet.getInt(5);
                
                CategoryDAO categoryDAO = new CategoryDAO();
                Category category1 = categoryDAO.selectById(category);
                point = new Point(idPoint, course, describe, percent,category1);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return point;
    }
    public ArrayList<Point> selectScore(int idCourse) {
        ArrayList<Point> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select *\n"
                    + "from Point\n"
                    + "where idCourse = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, idCourse);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idPoint = resultSet.getInt(1);
                CourseDAO courseDAO = new CourseDAO();
                Course course = courseDAO.selectById(resultSet.getInt(2));
                String der = resultSet.getString(3);
                float per = resultSet.getFloat(4);
                int category = resultSet.getInt(5);
                CategoryDAO categoryDAO = new CategoryDAO();
                Category category1 = categoryDAO.selectById(category);
                Point point = new Point(idPoint, course, der, per,category1); 
                list.add(point);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return list;
    }
}
