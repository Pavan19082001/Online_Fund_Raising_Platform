package com.bootcamp.funds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.funds.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	// @Query("from Post p join p.user u where u.username=:username")
	// List<Post> findByUsername(String username);

}
