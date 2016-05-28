package com.innominds.persistence.vo;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7193164838364962544L;

    private String accessToken;

    private String name;

    private String username;

    public LoginResponse() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("LoginResponse [");
        if (accessToken != null) {
            builder.append("accessToken=");
            builder.append(accessToken);
            builder.append(", ");
        }
        if (name != null) {
            builder.append("name=");
            builder.append(name);
            builder.append(", ");
        }
        if (username != null) {
            builder.append("username=");
            builder.append(username);
        }
        builder.append("]");
        return builder.toString();
    }

}
