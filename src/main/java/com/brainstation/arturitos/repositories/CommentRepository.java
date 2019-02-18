package com.brainstation.arturitos.repositories;

import com.brainstation.arturitos.dtos.CommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentDTO, Integer>{
}
