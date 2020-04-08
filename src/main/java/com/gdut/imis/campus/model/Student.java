package com.gdut.imis.campus.model;

import java.util.Date;

public class Student {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.id
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.account_id
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.password
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.type
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.nickname
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String nickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.name
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.birth
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private Date birth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.dept
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String dept;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.degree
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String degree;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.email
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.address
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.avatar_url
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String avatarUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.gmt_create
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.gmt_modified
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.bio
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String bio;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.major
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String major;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.status
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.phone
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.target_type
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String targetType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.target_job
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    private String targetJob;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.id
     *
     * @return the value of student.id
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.id
     *
     * @param id the value for student.id
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.account_id
     *
     * @return the value of student.account_id
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.account_id
     *
     * @param accountId the value for student.account_id
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.password
     *
     * @return the value of student.password
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.password
     *
     * @param password the value for student.password
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.type
     *
     * @return the value of student.type
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.type
     *
     * @param type the value for student.type
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.nickname
     *
     * @return the value of student.nickname
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.nickname
     *
     * @param nickname the value for student.nickname
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.name
     *
     * @return the value of student.name
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.name
     *
     * @param name the value for student.name
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.birth
     *
     * @return the value of student.birth
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.birth
     *
     * @param birth the value for student.birth
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.dept
     *
     * @return the value of student.dept
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getDept() {
        return dept;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.dept
     *
     * @param dept the value for student.dept
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.degree
     *
     * @return the value of student.degree
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getDegree() {
        return degree;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.degree
     *
     * @param degree the value for student.degree
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.email
     *
     * @return the value of student.email
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.email
     *
     * @param email the value for student.email
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.address
     *
     * @return the value of student.address
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.address
     *
     * @param address the value for student.address
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.avatar_url
     *
     * @return the value of student.avatar_url
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.avatar_url
     *
     * @param avatarUrl the value for student.avatar_url
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.gmt_create
     *
     * @return the value of student.gmt_create
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.gmt_create
     *
     * @param gmtCreate the value for student.gmt_create
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.gmt_modified
     *
     * @return the value of student.gmt_modified
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.gmt_modified
     *
     * @param gmtModified the value for student.gmt_modified
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.bio
     *
     * @return the value of student.bio
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getBio() {
        return bio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.bio
     *
     * @param bio the value for student.bio
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setBio(String bio) {
        this.bio = bio == null ? null : bio.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.major
     *
     * @return the value of student.major
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getMajor() {
        return major;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.major
     *
     * @param major the value for student.major
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.status
     *
     * @return the value of student.status
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.status
     *
     * @param status the value for student.status
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.phone
     *
     * @return the value of student.phone
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.phone
     *
     * @param phone the value for student.phone
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.target_type
     *
     * @return the value of student.target_type
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getTargetType() {
        return targetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.target_type
     *
     * @param targetType the value for student.target_type
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType == null ? null : targetType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.target_job
     *
     * @return the value of student.target_job
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public String getTargetJob() {
        return targetJob;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.target_job
     *
     * @param targetJob the value for student.target_job
     *
     * @mbg.generated Sun Apr 05 15:41:03 CST 2020
     */
    public void setTargetJob(String targetJob) {
        this.targetJob = targetJob == null ? null : targetJob.trim();
    }
}