/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ent;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "CLICK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Click.findAll", query = "SELECT c FROM Click c"),
    @NamedQuery(name = "Click.findByIdc", query = "SELECT c FROM Click c WHERE c.idc = :idc"),
    @NamedQuery(name = "Click.findByDate", query = "SELECT c FROM Click c WHERE c.date = :date"),
    @NamedQuery(name = "Click.findByIpaddress", query = "SELECT c FROM Click c WHERE c.ipaddress = :ipaddress"),
    @NamedQuery(name = "Click.allUserClicksOverPeriod",
            query = "SELECT c FROM Click c WHERE c.date BETWEEN :from AND :to AND c.fkIdu = :userID")
})
public class Click implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDC")
    private Long idc;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "IPADDRESS")
    private String ipaddress;
    @JoinColumn(name = "FK_IDU", referencedColumnName = "IDU")
    @ManyToOne(optional = false)
    private Users fkIdu;

    public Click() {
    }

    public Click(Users user, Date date, String IPAddress) {
        this.date = date;
        this.ipaddress = IPAddress;
        this.fkIdu = user;
    }

    public Click(Long idc) {
        this.idc = idc;
    }

    public Long getIdc() {
        return idc;
    }

    public void setIdc(Long idc) {
        this.idc = idc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public Users getFkIdu() {
        return fkIdu;
    }

    public void setFkIdu(Users fkIdu) {
        this.fkIdu = fkIdu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idc != null ? idc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Click)) {
            return false;
        }
        Click other = (Click) object;
        if ((this.idc == null && other.idc != null) || (this.idc != null && !this.idc.equals(other.idc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getFkIdu() + "\n"
                + "[" + new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").format(getDate()) + " ] \n"
                + "[" + getIpaddress() + " ] \n";
    }

}
