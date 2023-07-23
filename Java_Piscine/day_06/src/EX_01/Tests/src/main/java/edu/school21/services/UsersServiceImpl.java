package edu.school21.services;

import edu.school21.exeptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

import java.sql.*;

public class UsersServiceImpl implements UsersRepository {

    private final Connection connection;

    public UsersServiceImpl(Connection connection) {
        this.connection = connection;
    }

    boolean authenticate(String login, String password){
        User user = findByLogin(login);

        if(user.isAuthenticationSuccessStatus()){
            throw new AlreadyAuthenticatedException();
        }

        user.setAuthenticationSuccessStatus(password.equals(user.getPassword()));
        update(user);
        return user.isAuthenticationSuccessStatus();
    }

    @Override
    public User findByLogin(String login)  {
       User user;
       try {
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
           statement.setString(1,login);
           ResultSet userInDataBase = statement.executeQuery();
           if(userInDataBase.next()){
               user = new User( userInDataBase.getLong("id"), userInDataBase.getString("login"),
                       userInDataBase.getString("password"),
                       userInDataBase.getBoolean("authenticationSuccessStatus"));
           } else {
               throw new EntityNotFoundException();
           }
       } catch (SQLException e){
           e.printStackTrace();
           return null;
       }
       return user;
    }

    @Override
    public void update(User user) {

        String sql = "UPDATE users SET " +
                "login = ?, " +
                "password = ?" +
                "authenticationSuccessStatus = ?" +
                "WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3,user.isAuthenticationSuccessStatus());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
