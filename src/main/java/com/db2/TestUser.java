package com.db2;

public class TestUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TEST_USER.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TEST_USER.NAME
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TEST_USER.BIRTHDAY
     *
     * @mbggenerated
     */
    private String birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TEST_USER.LONGCONTENT
     *
     * @mbggenerated
     */
    private byte[] longcontent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TEST_USER.ID
     *
     * @return the value of TEST_USER.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TEST_USER.ID
     *
     * @param id the value for TEST_USER.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TEST_USER.NAME
     *
     * @return the value of TEST_USER.NAME
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TEST_USER.NAME
     *
     * @param name the value for TEST_USER.NAME
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TEST_USER.BIRTHDAY
     *
     * @return the value of TEST_USER.BIRTHDAY
     *
     * @mbggenerated
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TEST_USER.BIRTHDAY
     *
     * @param birthday the value for TEST_USER.BIRTHDAY
     *
     * @mbggenerated
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TEST_USER.LONGCONTENT
     *
     * @return the value of TEST_USER.LONGCONTENT
     *
     * @mbggenerated
     */
    public byte[] getLongcontent() {
        return longcontent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TEST_USER.LONGCONTENT
     *
     * @param longcontent the value for TEST_USER.LONGCONTENT
     *
     * @mbggenerated
     */
    public void setLongcontent(byte[] longcontent) {
        this.longcontent = longcontent;
    }
}