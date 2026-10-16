package jobportal.presentation.controller;

import ch.qos.logback.core.util.StringUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
public class FileController {

//    @PostMapping("/file")
//    public String upload(@RequestParam("file") MultipartFile file) {
//        String fileName = "";
//
//        // If user has been uploaded file => get file name
//        if (!("").equals(file.getOriginalFilename())) {
//            fileName = StringUtils
//                    .cleanPath(
//                            Objects.requireNonNull(
//                                    file.getOriginalFilename()
//                            )
//                    );
//        }
//    }

    @GetMapping("/file")
    public ResponseEntity<Resource> getFile() throws MalformedURLException {
        // Get current paths
        Path path = Paths.get("src/main/resources/static/resumes/pdfExp.pdf");

        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
