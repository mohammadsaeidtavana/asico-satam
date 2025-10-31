package com.asico.hr.service;

import com.asico.hr.domain.UserModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface FileUploadService {

    String upload(MultipartFile multipartFile,String codeMeli,String fileName,String path);


}
