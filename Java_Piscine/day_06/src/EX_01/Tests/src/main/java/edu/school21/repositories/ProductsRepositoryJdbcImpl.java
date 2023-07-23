package edu.school21.repositories;

import edu.school21.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private final Connection connection;

    public ProductsRepositoryJdbcImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Product> findALl() {
        List<Product> productList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet products = statement.executeQuery("SELECT * FROM products");

            while (products.next()){
                Long id = products.getLong("id");
                String name = products.getString("name");
                Integer price = products.getInt("price");

                Product product = new Product(id,name,price);
                productList.add(product);
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) throws RuntimeException {
        Optional<Product> product = Optional.empty();

        try {
            Statement statement = connection.createStatement();
            ResultSet products = statement.executeQuery("SELECT * FROM products WHERE id = " + id);

            if(products.next()){
                product = Optional.of(new Product(products.getLong("id"), products.getString("name"),
                        products.getInt("price")));
            } else {
                throw new  RuntimeException();
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE products SET " +
                "name = ?, " +
                "price = ?" +
                "WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
