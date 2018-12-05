package cn.luischen.model;

import java.util.Date;

/**
 * @ClassName : pvDomain
 * @DesCription :TODO
 * @Author : young
 * @Date: 2018/12/4 17:19
 **/
public class PvDomain {

    private String id;

    private String ip;

    private Date created;

    private String country;

    private String city;

    private String isp;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
