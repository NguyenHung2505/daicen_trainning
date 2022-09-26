package com.jdbc;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    public  void add(Product product) throws SQLException;
    public List<Product> findAll();
    public boolean update(Product product)throws SQLException;
    public  boolean delete(int id) throws SQLException;
    public Product findIndexById(int id);
}
