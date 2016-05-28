package com.innominds.vo;

import java.io.Serializable;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class User implements Serializable {

    /**
     * This serialVersionUID useful when this user object is serialized and de-serialized
     */
    private static final long serialVersionUID = 2482030741065046868L;

    public User() {

    }

    public User(long id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    private long id;

    private String username;

    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + (int) (id ^ id >>> 32);
        result = prime * result + (username == null ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (age != other.age) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("User [id=%s, username=%s, age=%s]", id, username, age);
    }

}
