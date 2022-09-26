package com.jdbc;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ProductDao productDao =new ProductDao();
//        productDao.add(new Product(5,"hung",20,50));
        productDao.findAll();

    }
}
