/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Room;

/**
 *
 * @author trung
 */
public class RoomDAO {

    public Room selectById(int id) {
        Room room = null;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select [idRoom],[nameRoom]\n"
                    + "from Room\n"
                    + "where idRoom = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idRoom = resultSet.getInt("idRoom");
                String nameRoom = resultSet.getString("nameRoom");
                room = new Room(idRoom, nameRoom);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return room;
    }
}
