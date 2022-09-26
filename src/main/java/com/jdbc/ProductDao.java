package com.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDAO{

    List<Product> productList = new ArrayList<>();
    public ProductDao() {
        }

        protected static Connection getConnection() {
            Connection connection = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/manage_product?useSSL=false", "root", "123456");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return connection;
        }

    @Override
    public void add(Product product) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into product(id,name ,price ,quantyti) value (?,?,?,?)");) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select *from product");) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = Integer.parseInt(rs.getString("price"));
                int quantyti = Integer.parseInt(rs.getString("quantyti"));
                products.add(new Product(id, name, price,quantyti));
            }
        } catch (SQLException e) {

        }
        return products;
    }

    @Override
    public boolean update(Product product) throws SQLException {
            boolean editt = false;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("update product set name = ? , price = ? , quantyti = ?  where id = ?");) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setInt(2, product.getPrice());
                preparedStatement.setInt(2, product.getQuantity());
                preparedStatement.setInt(3, product.getId());
                editt = preparedStatement.executeUpdate() > 0;
            }
            System.out.println(product.toString());
            return editt;
    }

    @Override
    public boolean delete(int id) throws SQLException {
            boolean xoa = false;
            try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id=?;")){
                preparedStatement.setInt(1,id);
                xoa = preparedStatement.executeUpdate()>0;
            }
            return xoa;
    }

    @Override
    public Product findIndexById(int id) {
        List<Product> products = findAll();
        Product product = new Product();
        for (Product pd: products
        ) {
            if(id == pd.getId()) {
                product = pd;
            }
        }
        return product;
    }
}
