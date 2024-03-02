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
import model.Group;

/**
 *
 * @author trung
 */
public class GroupDAO {

    public Group selectIdGroup(int id) {
        Group group = null;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "SELECT *"
                    + "  FROM [dbo].[Group]\n"
                    + "  WHERE idGroup = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idGroup = resultSet.getInt(1);
                String codeGroup = resultSet.getString(2);
                int idCourse = resultSet.getInt(3);
                int idSemester = resultSet.getInt(4);
                CourseDAO courseDAO = new CourseDAO();
                SemesterDAO semesterDAO = new SemesterDAO();
                group = new Group(idGroup, codeGroup, courseDAO.selectById(idCourse),semesterDAO.selectById(idSemester));

            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return group;
    }

    public ArrayList<Group> selectAllByIdStudentAndIdSemester(String idStudent,int idSemester) {
        ArrayList<Group> groups = new ArrayList<Group>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select g.idGroup,g.codeGroup,g.idCourse,g.[idSemester]\n"
                    + "from [Group] g join inGroup i on g.idGroup=i.idGroup\n"
                    + "where i.idStudent = ? AND idSemester=?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, idStudent);
            preparedStatement.setInt(2, idSemester);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idGroup = resultSet.getInt(1);
                String codeGroup = resultSet.getString(2);
                int idCourse = resultSet.getInt(3);
                
                CourseDAO courseDAO = new CourseDAO();
                SemesterDAO semesterDAO = new SemesterDAO();
                Group group = new Group(idGroup, codeGroup, courseDAO.selectById(idCourse),semesterDAO.selectById(idSemester));
                groups.add(group);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return groups;
    }
}
