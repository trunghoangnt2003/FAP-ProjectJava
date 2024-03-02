/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import model.TimeSlot;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author trung
 */
public class TimeSlotDAO {

    public TimeSlot selectById(int id) {
        TimeSlot timeSlot = null;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select [idSlot],[timeline]\n"
                    + "from TimeSlot\n"
                    + "Where [idSlot] = ?;";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int idSlot = resultSet.getInt("idSlot");
                String timeline = resultSet.getString("timeline");
                timeSlot = new TimeSlot(idSlot, timeline);
                
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return timeSlot;
    }
    public ArrayList<TimeSlot> selectAll() {
        ArrayList<TimeSlot> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select [idSlot],[timeline]\n"
                    + "from TimeSlot\n";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int idSlot = resultSet.getInt("idSlot");
                String timeline = resultSet.getString("timeline");
                TimeSlot timeSlot = new TimeSlot(idSlot, timeline);
                list.add(timeSlot);
                
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return list;
    }
}
