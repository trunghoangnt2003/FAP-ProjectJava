/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import jakarta.websocket.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Group;
import model.Lession;
import model.TimeSlot;

/**
 *
 * @author trung
 */
public class LessionDAO {

    public ArrayList<Lession> selectLessionByUser(String idUser) {
        ArrayList<Lession> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select Lession.*\n"
                    + "from [Lession] \n"
                    + "join [Group] on Lession.idGroup = [Group].idGroup\n"
                    + "join inGroup on inGroup.idGroup = Lession.idGroup\n"
                    + "where inGroup.idStudent = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, idUser);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idLession = rs.getInt("idLession");
                int idGroup = rs.getInt("idGroup");
                int idLecture = rs.getInt("idLecture");
                int idRoom = rs.getInt("idRoom");
                int idSlot = rs.getInt("idSlot");
                java.sql.Date date = rs.getDate("date");
                GroupDAO groupDAO = new GroupDAO();
                LectureDAO lectureDAO = new LectureDAO();
                RoomDAO roomDAO = new RoomDAO();
                TimeSlotDAO timeSlotDAO = new TimeSlotDAO();
                Lession lession = new Lession(idLession, groupDAO.selectIdGroup(idGroup), lectureDAO.selectById(idLecture), roomDAO.selectById(idRoom), timeSlotDAO.selectById(idSlot), date);
                list.add(lession);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return list;
    }
    public ArrayList<Lession> selectLessionByUserAndGroup(String idUser,int idGroup) {
        ArrayList<Lession> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select Lession.*\n"
                    + "from [Lession] \n"
                    + "join [Group] on Lession.idGroup = [Group].idGroup\n"
                    + "join inGroup on inGroup.idGroup = Lession.idGroup\n"
                    + "where inGroup.idStudent = ? and [Group].idGroup=?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, idUser);
            preparedStatement.setInt(2, idGroup);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idLession = rs.getInt("idLession");
                int idLecture = rs.getInt("idLecture");
                int idRoom = rs.getInt("idRoom");
                int idSlot = rs.getInt("idSlot");
                java.sql.Date date = rs.getDate("date");
                GroupDAO groupDAO = new GroupDAO();
                LectureDAO lectureDAO = new LectureDAO();
                RoomDAO roomDAO = new RoomDAO();
                TimeSlotDAO timeSlotDAO = new TimeSlotDAO();
                Lession lession = new Lession(idLession, groupDAO.selectIdGroup(idGroup), lectureDAO.selectById(idLecture), roomDAO.selectById(idRoom), timeSlotDAO.selectById(idSlot), date);
                list.add(lession);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return list;
    }

    public Lession selectLessionById(int id) {
        Lession lession = null;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select Lession.*\n"
                    + "from [Lession] \n"
                    + "where idLession = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idLession = rs.getInt("idLession");
                int idGroup = rs.getInt("idGroup");
                int idLecture = rs.getInt("idLecture");
                int idRoom = rs.getInt("idRoom");
                int idSlot = rs.getInt("idSlot");
                java.sql.Date date = rs.getDate("date");
                int status = rs.getInt("status");
                GroupDAO groupDAO = new GroupDAO();
                LectureDAO lectureDAO = new LectureDAO();
                RoomDAO roomDAO = new RoomDAO();
                TimeSlotDAO timeSlotDAO = new TimeSlotDAO();
                lession = new Lession(idLession, groupDAO.selectIdGroup(idGroup), lectureDAO.selectById(idLecture), roomDAO.selectById(idRoom), timeSlotDAO.selectById(idSlot), date);
                lession.setStatus(status);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return lession;
    }

    public ArrayList<Lession> selectLessionByIdLectureAndDay(String idUser, String date1) {
        ArrayList<Lession> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "SELECT [idLession]\n"
                    + "      ,Lession.idGroup\n"
                    + "      ,[idLecture]\n"
                    + "      ,[idRoom]\n"
                    + "      ,[idSlot]\n"
                    + "      ,[date]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Lession]\n"
                    + "  where [date] = ? and idLecture=?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, date1);
            preparedStatement.setString(2, idUser);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idLession = rs.getInt("idLession");
                int idGroup = rs.getInt("idGroup");
                int idLecture = rs.getInt("idLecture");
                int idRoom = rs.getInt("idRoom");
                int idSlot = rs.getInt("idSlot");
                java.sql.Date date = rs.getDate("date");
                GroupDAO groupDAO = new GroupDAO();
                LectureDAO lectureDAO = new LectureDAO();
                RoomDAO roomDAO = new RoomDAO();
                TimeSlotDAO timeSlotDAO = new TimeSlotDAO();
                Lession lession = new Lession(idLession, groupDAO.selectIdGroup(idGroup), lectureDAO.selectById(idLecture), roomDAO.selectById(idRoom), timeSlotDAO.selectById(idSlot), date);

                int status = rs.getInt("status");
                lession.setStatus(status);
                list.add(lession);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return list;
    }

    public int updateLession(int idLession, int status) {
        int result = 0;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "update Lession\n"
                    + "set status = ?\n"
                    + "where idLession=?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, idLession);
            result = preparedStatement.executeUpdate();
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return result;
    }

    public static void main(String[] args) {
        LessionDAO lessionDAO = new LessionDAO();
        ArrayList<Lession> list = lessionDAO.selectLessionByUser("HE160918");
        for (Lession lession : list) {
            System.out.println(lession.getIdLession());
        }
    }
}
