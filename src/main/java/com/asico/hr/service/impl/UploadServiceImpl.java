package com.asico.hr.service.impl;

import com.asico.hr.miniIo.MiniIoService;
import com.asico.hr.service.FileUploadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */

@Service
@AllArgsConstructor
public class UploadServiceImpl implements FileUploadService {

    MiniIoService miniIoService;

    @Override
    public String upload(MultipartFile multipartFile, String codeMeli, String fileName, String path) {
        return miniIoService.uploadFile(multipartFile, codeMeli, path +"/"+ fileName);
    }
}
