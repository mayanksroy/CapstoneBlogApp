package com.mayank.service;

import java.util.List;

import com.mayank.dto.CommentDTO;

import jakarta.validation.Valid;

public interface CommentService {

	CommentDTO postComment(@Valid CommentDTO commentdto);

	List<CommentDTO> getAllComments();

}
