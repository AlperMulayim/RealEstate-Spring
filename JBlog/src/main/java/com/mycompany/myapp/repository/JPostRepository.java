package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.JPost;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the JPost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JPostRepository extends JpaRepository<JPost, Long> {

}
