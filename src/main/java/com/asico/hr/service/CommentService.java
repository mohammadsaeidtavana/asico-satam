package com.asico.hr.service;

import com.asico.hr.domain.CommentModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface CommentService {


    CommentModel search(String coursId);

    CommentModel add(CommentModel bulletinModel);

    List<CommentModel> getAll(String coursId);

    CommentModel delete(CommentModel model);

    CompletableFuture<CommentModel> saveAsync(CommentModel model, String coursId);

}
