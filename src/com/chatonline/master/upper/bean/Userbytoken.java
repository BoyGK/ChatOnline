package com.chatonline.master.upper.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Userbytoken {
    private String token;
    private int id;

    @Id
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Userbytoken that = (Userbytoken) object;
        return id == that.id &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {

        return Objects.hash(token, id);
    }
}
