/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.edu.xml.dtos;

import java.io.Serializable;

/**
 *
 * @author huynh
 */
public class StudentDTO implements Serializable {

    private String id;
    private String lastname;
    private String middlename;
    private String firstname;
    private String password;
    private String classname;
    private String address;
    private boolean sex;
    private String status;

    public StudentDTO() {
    }

    public StudentDTO(String id, String lastname, String middlename, String firstname, String password, String classname, String address, boolean sex, String status) {
        this.id = id;
        this.lastname = lastname;
        this.middlename = middlename;
        this.firstname = firstname;
        this.password = password;
        this.classname = classname;
        this.address = address;
        this.sex = sex;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
