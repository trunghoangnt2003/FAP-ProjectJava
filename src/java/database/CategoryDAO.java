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

/**
 *
 * @author trung
 */
public class CategoryDAO {

    public Category selectById(int id) {
        Category category = null;
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select *\n"
                    + "from Category\n"
                    + "where [idCategory] = ?";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idRoom = resultSet.getInt(1);
                String nameRoom = resultSet.getString(2);
                category = new Category(idRoom, nameRoom);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return category;
    }

    public ArrayList<Category> selectCategoryByIdCourse(int id) {
        ArrayList<Category> list = new ArrayList<>();
        try {
            Connection connection = JDBC.getConnection();
            String sql = "select DISTINCT c.*\n"
                    + "from Category c join Point p on c.[idCategory]=p.[idCategory]\n"
                    + "where p.idCourse=?\n"
                    + "order by idCategory asc";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idRoom = resultSet.getInt(1);
                String nameRoom = resultSet.getString(2);
                Category category = new Category(idRoom, nameRoom);
                list.add(category);
            }
            JDBC.closeConnection(connection);
        } catch (Exception e) {
        }
        return list;
    }
}
