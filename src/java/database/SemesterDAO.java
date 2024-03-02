/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Semester;
/**
 *
 * @author trung
 */
public class SemesterDAO {
    public Semester selectById(int id) {
        Semester semester = null;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select *\n"
                    + "from [Semester]\n"
                    + "where [idSemester] = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                semester = new Semester();
                semester.setIdSemester(id);
                semester.setNameSemester(resultSet.getString(3));
                semester.setCodeSemester(resultSet.getString(2));
                break;
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return semester;
    }
    public ArrayList<Semester> selectAll() {
        ArrayList<Semester> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select *\n"
                    + "from [Semester]\n";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
           
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Semester semester = new Semester();
                semester.setIdSemester(resultSet.getInt(1));
                semester.setNameSemester(resultSet.getString(3));
                semester.setCodeSemester(resultSet.getString(2));
                list.add(semester);
                
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return list;
    }
}
