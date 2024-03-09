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
import model.Lession;
import model.Status;
import model.Student;

/**
 *
 * @author trung
 */
public class StatusDAO {

    public ArrayList<Status> selectByIdStudentAndDay(String idUser, String dateLession) {
        ArrayList<Status> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select Status.idStatus, stu.id,Lession.idLession,Status.status,Status.date\n"
                    + "from Student stu join inGroup ig on stu.id = ig.idStudent\n"
                    + "				join Lession on Lession.idGroup = ig.idGroup\n"
                    + "				left join Status on stu.id=Status.idStudent and Status.idLession =Lession.idLession\n"
                    + "where Lession.date = ? and stu.id = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(2, idUser);
            preparedStatement.setString(1, dateLession);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idStatus = rs.getInt(1);
                int idLession = rs.getInt(3);
                int status = rs.getInt(4);
                java.sql.Timestamp date = rs.getTimestamp(5);

                LessionDAO lessionDAO = new LessionDAO();
                Lession lession = lessionDAO.selectLessionById(idLession);
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.selectStudent(idUser);

                Status s = new Status(idStatus, student, lession, status, date);
                list.add(s);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return list;
    }

    public int updateStatusByIdStudentAndIdLessionReTake(String id, java.sql.Timestamp date, int idLession, int status) {
        int result = 0;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "update Status\n"
                    + "set status = ? , date = ?\n"
                    + "where idLession=? and idStudent = ? and status <> ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, status);
            preparedStatement.setTimestamp(2, date);
            preparedStatement.setInt(3, idLession);
            preparedStatement.setString(4, id);
            preparedStatement.setInt(5, status);
            result = preparedStatement.executeUpdate();
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return result;
    }

    public int insertStatusByIdStudentAndIdLession(int idStatus, String idStudent, java.sql.Timestamp date, int idLession, int status) {
        int result = 0;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "INSERT INTO [dbo].[Status]\n"
                    + "           ([idStatus]\n"
                    + "           ,[idStudent]\n"
                    + "           ,[idLession]\n"
                    + "           ,[status]\n"
                    + "           ,[date])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, idStatus);
            preparedStatement.setString(2, idStudent);
            preparedStatement.setInt(3, idLession);
            preparedStatement.setInt(4, status);
            preparedStatement.setTimestamp(5, date);
            result = preparedStatement.executeUpdate();
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return result;
    }

    public int selectCountStatus() {
        int result = 0;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select count(*) as count\n"
                    + "from Status";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("count");
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return result;
    }

    public int selectCountStatusAbsent(String idStudent) {
        int result = 0;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select count(*) as count\n"
                    + "from [dbo].[Status]\n"
                    + "where idStudent=? and status = -1;";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, idStudent);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("count");
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return result;
    }

    public ArrayList<Status> selectByIdLession(int id) {
        ArrayList<Status> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select Status.idStatus, stu.id,Lession.idLession,Status.status,Status.date\n"
                    + "from Student stu join inGroup ig on stu.id = ig.idStudent\n"
                    + "				join Lession on Lession.idGroup = ig.idGroup\n"
                    + "				left join Status on stu.id=Status.idStudent and Status.idLession =Lession.idLession\n"
                    + "where Lession.idLession = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idStatus = rs.getInt(1);
                String idStudent = rs.getString(2);
                int idLession = rs.getInt(3);

                int status = rs.getInt(4);

                java.sql.Timestamp date = rs.getTimestamp(5);

                LessionDAO lessionDAO = new LessionDAO();
                Lession lession = lessionDAO.selectLessionById(idLession);
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.selectStudent(idStudent);
                Status s = new Status(idStatus, student, lession, status, date);
                list.add(s);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return list;
    }
    public ArrayList<Status> selectByIdGroupAndIdStudent(int id,String idStudent) {
        ArrayList<Status> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select Status.idStatus, stu.id,Lession.idLession,Status.status,Status.date\n" +
"                    from Student stu join inGroup ig on stu.id = ig.idStudent\n" +
"                				join Lession on Lession.idGroup = ig.idGroup\n" +
"                   				left join Status on stu.id=Status.idStudent and Status.idLession =Lession.idLession\n" +
"                   where Lession.idGroup = ? and stu.id=?\n" +
"                   order by Lession.[date]";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, idStudent);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idStatus = rs.getInt(1);
                int idLession = rs.getInt(3);

                int status = rs.getInt(4);

                java.sql.Timestamp date = rs.getTimestamp(5);

                LessionDAO lessionDAO = new LessionDAO();
                Lession lession = lessionDAO.selectLessionById(idLession);
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.selectStudent(idStudent);
                Status s = new Status(idStatus, student, lession, status, date);
                list.add(s);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return list;
    }
}
