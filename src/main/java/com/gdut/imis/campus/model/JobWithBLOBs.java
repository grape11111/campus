package com.gdut.imis.campus.model;

public class JobWithBLOBs extends Job {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column job.content
     *
     * @mbg.generated Thu Feb 27 14:48:31 CST 2020
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column job.requirement
     *
     * @mbg.generated Thu Feb 27 14:48:31 CST 2020
     */
    private String requirement;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column job.content
     *
     * @return the value of job.content
     *
     * @mbg.generated Thu Feb 27 14:48:31 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column job.content
     *
     * @param content the value for job.content
     *
     * @mbg.generated Thu Feb 27 14:48:31 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column job.requirement
     *
     * @return the value of job.requirement
     *
     * @mbg.generated Thu Feb 27 14:48:31 CST 2020
     */
    public String getRequirement() {
        return requirement;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column job.requirement
     *
     * @param requirement the value for job.requirement
     *
     * @mbg.generated Thu Feb 27 14:48:31 CST 2020
     */
    public void setRequirement(String requirement) {
        this.requirement = requirement == null ? null : requirement.trim();
    }
}