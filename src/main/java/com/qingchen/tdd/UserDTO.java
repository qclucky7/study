package com.qingchen.tdd;

/**
 * @ClassName UserDTO
 * @description:
 * @author: WangChen
 * @create: 2020-06-14 15:14
 **/
public class UserDTO {

     private String userName;
     private String password;
     private Long phone;
     private String email;

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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
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
