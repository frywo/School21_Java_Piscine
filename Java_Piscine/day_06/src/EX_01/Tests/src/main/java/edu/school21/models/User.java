package edu.school21.models;

import java.util.Objects;

public class User {
    final Long id;
    private String login;
    private String password;
    private boolean authenticationSuccessStatus;

    public User(Long id, String login, String password, boolean authenticationSuccessStatus){
        this.id = id;
        this.login = login;
        this.password = password;
        this.authenticationSuccessStatus = authenticationSuccessStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, authenticationSuccessStatus);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if(obj==null || getClass()!= obj.getClass()) return  false;

        User user = (User) obj;

        return id.equals(user.getId()) &&
                login.equals(user.getLogin()) && password.equals(user.getPassword()) &&
                authenticationSuccessStatus == user.isAuthenticationSuccessStatus();
    }

    @Override
    public String toString() {
        return "User: {" +
                "\n id = " + id +
                ",\n login = " + login +
                ",\n, password = " + password +
                ",\n, authentication status - " + authenticationSuccessStatus +
                "\n }";
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAuthenticationSuccessStatus() {
        return authenticationSuccessStatus;
    }

    public void setAuthenticationSuccessStatus(boolean authenticationSuccessStatus) {
        this.authenticationSuccessStatus = authenticationSuccessStatus;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
