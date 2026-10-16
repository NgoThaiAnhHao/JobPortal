package jobportal.application.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FileUtils {

    public static String getFileName(MultipartFile file) {
        String fileName = "";

        // If user has been uploaded file => get file name
        if (!("").equals(file.getOriginalFilename())) {
            fileName = StringUtils
                    .cleanPath(
                            Objects.requireNonNull(
                                    file.getOriginalFilename()
                            )
                    );
        }

        // Check null
        if (fileName.trim().isEmpty()) {
            throw new RuntimeException("Upload file was failed.");
        }

        return fileName;
    }

    public static void saveFile(String dir, String fileName, MultipartFile file) throws IOException {
        // Get path
        Path uploadPath = Paths.get(dir);

        // Check path not exist => create new path
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Đọc nội dung nhị phân của file.
        try (InputStream inputStream = file.getInputStream();) {
            // Tạo đường dẫn đầy đủ đến file cần lưu.
            // vd: uploads/avatars/avatar.png
            Path path = uploadPath.resolve(fileName);

            System.out.println("FilePath " + path);
            System.out.println("fileName " + fileName);

            // Ghi file xuống ổ đĩa
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
