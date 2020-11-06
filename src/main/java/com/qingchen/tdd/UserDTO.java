package com.qingchen.tdd;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserDTO
 * @description:
 * @author: WangChen
 * @create: 2020-06-14 15:14
 **/
public class UserDTO {


     @NotBlank(message = "{message.username}")
     private String userName;
     @NotBlank(message = "{message.password}")
     private String password;
     @NotBlank(message = "{message.phone}")
     //@Pattern(regexp = "^1\\d{10}$", message = "请输入正确手机号")
     private String phone;
     @NotEmpty(message = "不能为空")
    //@Pattern(regexp = "^1\\d{10}$", message = "请输入正确手机号")
    private List<String> tests;
     //@NotBlank(message = "邮箱不能为空")
     //@Email(message = "请输入正确的邮箱")
     private String email;
     //@NotNull(message = "出生日期不能为空")
     //@Past(message = "错误出生日期")
     private Date birthDate;

    public List<String> getTests() {
        return tests;
    }

    public void setTests(List<String> tests) {
        this.tests = tests;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }

}
