package ent;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Click implements Serializable {

    private static final long serialVersionUID = 1745888125448925641L;

    private Long idc;
    private Date date;
    private String ipaddress;
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
