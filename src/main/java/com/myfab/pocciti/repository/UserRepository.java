package com.myfab.pocciti.repository;

import com.myfab.pocciti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update U" +
            "ser set firstName=:firstname,lastName=:lastname where userId like :id")
    int updateUserName(final String id, final String firstname, final String lastname);
}
