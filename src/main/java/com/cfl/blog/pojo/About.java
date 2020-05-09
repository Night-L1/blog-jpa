package com.cfl.blog.pojo;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Administer
 */
@Entity
@Table(name = "t_about")
public class About {
    @Id
    @GeneratedValue
    private Long id;

    private String introduce;

    private String hobbys;

    private String technologys;

    private String txPic;

    private String email;

    private String qq;

    private String wxPic;

    private String footerBlog;

    private String aliPay;

    private String wxPay;

    public About() {
    }

    @Override
    public String toString() {
        return "About{" +
                "id=" + id +
                ", introduce='" + introduce + '\'' +
                ", hobbys='" + hobbys + '\'' +
                ", technologys='" + technologys + '\'' +
                ", txPic='" + txPic + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", wxPic='" + wxPic + '\'' +
                ", footerBlog='" + footerBlog + '\'' +
                ", aliPay='" + aliPay + '\'' +
                ", wxPay='" + wxPay + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public About setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIntroduce() {
        return introduce;
    }

    public About setIntroduce(String introduce) {
        this.introduce = introduce;
        return this;
    }

    public String getHobbys() {
        return hobbys;
    }

    public About setHobbys(String hobbys) {
        this.hobbys = hobbys;
        return this;
    }

    public String getTechnologys() {
        return technologys;
    }

    public About setTechnologys(String technologys) {
        this.technologys = technologys;
        return this;
    }

    public String getTxPic() {
        return txPic;
    }

    public About setTxPic(String txPic) {
        this.txPic = txPic;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public About setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getQq() {
        return qq;
    }

    public About setQq(String qq) {
        this.qq = qq;
        return this;
    }

    public String getWxPic() {
        return wxPic;
    }

    public About setWxPic(String wxPic) {
        this.wxPic = wxPic;
        return this;
    }

    public String getFooterBlog() {
        return footerBlog;
    }

    public About setFooterBlog(String footerBlog) {
        this.footerBlog = footerBlog;
        return this;
    }

    public String getAliPay() {
        return aliPay;
    }

    public About setAliPay(String aliPay) {
        this.aliPay = aliPay;
        return this;
    }

    public String getWxPay() {
        return wxPay;
    }

    public About setWxPay(String wxPay) {
        this.wxPay = wxPay;
        return this;
    }
}
