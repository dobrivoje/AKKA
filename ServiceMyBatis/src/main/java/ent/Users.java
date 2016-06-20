package ent;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

public class Users implements Serializable {

    private static final long serialVersionUID = 5738459867893112389L;

    private Long idu;
    private String name;
    private String surname;
    private String username;
    private List<Click> clickList;

    public Users() {
    }

    public Users(Long idu) {
        this.idu = idu;
    }

    public Users(Long idu, String name, String surname, String username) {
        this.idu = idu;
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    public Users(String name, String surname, String username) {
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    public Long getIdu() {
        return idu;
    }

    public void setIdu(Long idu) {
        this.idu = idu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlTransient
    public List<Click> getClickList() {
        return clickList;
    }

    public void setClickList(List<Click> clickList) {
        this.clickList = clickList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idu != null ? idu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.idu == null && other.idu != null) || (this.idu != null && !this.idu.equals(other.idu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + name + " ], [" + surname + "], [" + username + "]";
    }

}
