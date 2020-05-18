package com.qingchen.study.jpa.master.entity;


import javax.persistence.*;

/**
 * @ClassName Custom
 * @description:
 * @author: WangChen
 * @create: 2020-05-17 13:51
 **/
@Entity
@Table(name = "cst_customer")
public class Custom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "cust_id")
    private Long custId;
    @Column(nullable = false, name = "cust_name")
    private String custName;
    @Column(nullable = false, name = "cust_source")
    private String custSource;
    @Column(nullable = false, name = "cust_level")
    private String custLevel;
    @Column(nullable = false, name = "cust_industry")
    private String custIndustry;
    @Column(nullable = false, name = "cust_phone")
    private String custPhone;
    @Column(nullable = false, name = "cust_address")
    private String custAddress;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }
}
