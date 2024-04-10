package com.github.dimagithahahab.booklib.repository;

import com.github.dimagithahahab.booklib.model.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
