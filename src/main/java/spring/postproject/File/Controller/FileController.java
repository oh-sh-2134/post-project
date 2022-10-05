package spring.postproject.File.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriUtils;
import spring.postproject.File.Entity.File;
import spring.postproject.File.Service.FileService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> fileDownload(@PathVariable Long id) throws IOException {
        File file = fileService.findFile(id);

        UrlResource urlResource = new UrlResource("file:" + file.getFilePath() + "\\" + file.getFilename());

        String encodeUploadFileName = UriUtils.encode(file.getOrigFilename(), StandardCharsets.UTF_8);

        String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }

}
