package com.tvajjala.dao;

import java.util.List;

import com.tvajjala.bean.User;

/**
 * <b>There is no implementation required</b> <br>
 * it will take <b>user-mapping.xml</b> method name should match the id of this XML
 *
 * @author ThirupathiReddy V
 *
 */
public interface UserDao {

    public void insertRecord(User testing);

    public User getUserRecordsById(Integer id);

    public List<User> getUserRecords();

}