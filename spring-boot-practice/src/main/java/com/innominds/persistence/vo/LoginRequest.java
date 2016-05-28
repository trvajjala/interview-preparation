package com.innominds.persistence.vo;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8473459948904602555L;
    private String username;
    private String password;

    public LoginRequest() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("LoginRequest [");
        if (username != null) {
            builder.append("username=");
            builder.append(username);
            builder.append(", ");
        }
        if (password != null) {
            builder.append("password=");
            builder.append(password);
        }
        builder.append("]");
        return builder.toString();
    }

}
