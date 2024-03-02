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
            String sql = "SELECT [idStatus]\n"
                    + "      ,[idStudent]\n"
                    + "      ,Status.[idLession]\n"
                    + "      ,Status.[status]\n"
                    + "      ,Status.[date]\n"
                    + "  FROM [Status]\n"
                    + "  join [dbo].[Lession] on Status.idLession=Lession.idLession\n"
                    + "  Where idStudent = ? and Lession.date=?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, idUser);
            preparedStatement.setString(2, dateLession);
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

    public int updateStatusByIdStudentAndIdLession(String id,java.sql.Timestamp date ,int idLession,int status) {
        int result = 0;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "update Status\n"
                    + "set status = ? , date = ?\n"
                    + "where idLession=? and idStudent = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, status);
            preparedStatement.setTimestamp(2, date);
            preparedStatement.setInt(3, idLession);
            preparedStatement.setString(4, id);
            result = preparedStatement.executeUpdate();
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return result;
    }
    public int updateStatusByIdStudentAndIdLessionReTake(String id,java.sql.Timestamp date ,int idLession,int status) {
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
    public ArrayList<Status> selectByIdLession(int id) {
        ArrayList<Status> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "SELECT [idStatus]\n"
                    + "      ,[idStudent]\n"
                    + "      ,Status.[idLession]\n"
                    + "      ,[status]\n"
                    + "      ,Status.[date]\n"
                    + "  FROM [Status]\n"
                    + "  Where Status.[idLession]=?";
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
}
