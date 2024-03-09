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

/**
 *
 * @author trung
 */
public class FluterDAO {

    public int fluterRole(String idUser,String url) {
        int result = 0;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select count(f.idFluter)\n"
                    + "from Fluter f join Fluter_Role fr on f.idFluter=fr.idFluter\n"
                    + "			join Role_User ru on ru.idRole=fr.idRole\n"
                    + "where ru.idUser=? and f.url = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(2, url);
            preparedStatement.setString(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
                System.out.println(result);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return result;
    }

}
