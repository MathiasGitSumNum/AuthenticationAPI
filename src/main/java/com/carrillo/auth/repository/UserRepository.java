package com.carrillo.auth.repository;

import com.carrillo.auth.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{

}
