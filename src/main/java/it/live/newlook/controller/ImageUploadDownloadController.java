package it.live.newlook.controller;

import it.live.newlook.entity.Attachment;
import it.live.newlook.repository.AttachmentRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageUploadDownloadController {
    private final AttachmentRepository attachmentRepository;
    private static final String uploadDirectory = "Files-Upload";


    @PostMapping("/uploadSystem")
    public String uploadFileSystem(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            String[] split = originalFilename.split("\\.");
            String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
            Attachment attachment = Attachment.builder().contentType(file.getContentType()).fileName(name).size(file.getSize()).fileOriginalName(originalFilename).build();
            Attachment attachmentSave = attachmentRepository.save(attachment);
            attachment.setDownloadUrl("/api/v1/uploadImage/getFileSystem/" + attachmentSave.getId());
            attachmentRepository.save(attachment);
            Path path = Paths.get(uploadDirectory + "/" + name);
            Files.copy(file.getInputStream(), path);
            return "FAYL SAQLANDI ID SI :  " + attachment.getId();
        }
        return "Xatolik";
    }


    @GetMapping("getFileSystem/{id}")
    public void getFileFromSystem(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            response.setHeader("Content-Disposition", "attachment; filename= \"" + attachment.getFileOriginalName());
            response.setContentType(attachment.getContentType());
            FileInputStream fileInputStream = new FileInputStream(uploadDirectory + "/" + attachment.getFileName());
            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
        }
    }
}
