package com.cfl.blog.vo;

import java.util.List;

/**
 * @author Administer
 */
public class AboutVo {

    private Long id;

    private String introduce;

    private String hobbys;

    private List<String> hobby;

    private String technologys;
    private List<String> technology;

    private String txPic;

    private String email;

    private String qq;

    private String wxPic;

    private String footerBlog;

    private String aliPay;

    private String wxPay;

    @Override
    public String toString() {
        return "AboutVo{" +
                "id=" + id +
                ", introduce='" + introduce + '\'' +
                ", hobbys='" + hobbys + '\'' +
                ", hobby=" + hobby +
                ", technologys='" + technologys + '\'' +
                ", technology=" + technology +
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

    public AboutVo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIntroduce() {
        return introduce;
    }

    public AboutVo setIntroduce(String introduce) {
        this.introduce = introduce;
        return this;
    }

    public String getHobbys() {
        return hobbys;
    }

    public AboutVo setHobbys(String hobbys) {
        this.hobbys = hobbys;
        return this;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public AboutVo setHobby(List<String> hobby) {
        this.hobby = hobby;
        return this;
    }

    public String getTechnologys() {
        return technologys;
    }

    public AboutVo setTechnologys(String technologys) {
        this.technologys = technologys;
        return this;
    }

    public List<String> getTechnology() {
        return technology;
    }

    public AboutVo setTechnology(List<String> technology) {
        this.technology = technology;
        return this;
    }

    public String getTxPic() {
        return txPic;
    }

    public AboutVo setTxPic(String txPic) {
        this.txPic = txPic;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AboutVo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getQq() {
        return qq;
    }

    public AboutVo setQq(String qq) {
        this.qq = qq;
        return this;
    }

    public String getWxPic() {
        return wxPic;
    }

    public AboutVo setWxPic(String wxPic) {
        this.wxPic = wxPic;
        return this;
    }

    public String getFooterBlog() {
        return footerBlog;
    }

    public AboutVo setFooterBlog(String footerBlog) {
        this.footerBlog = footerBlog;
        return this;
    }

    public String getAliPay() {
        return aliPay;
    }

    public AboutVo setAliPay(String aliPay) {
        this.aliPay = aliPay;
        return this;
    }

    public String getWxPay() {
        return wxPay;
    }

    public AboutVo setWxPay(String wxPay) {
        this.wxPay = wxPay;
        return this;
    }
}
