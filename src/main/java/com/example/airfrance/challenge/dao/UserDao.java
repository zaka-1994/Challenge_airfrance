package com.example.airfrance.challenge.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.airfrance.challenge.entities.User;

/**
 * User Dao extends of crudRepository to use save and findById methods
 * @author zhaimi
 * @Date 05/12/2021
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

}
