package spring.postproject.Common.File;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.postproject.Post.Entity.Post;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileHandler {


    public List<File> convertMultipartFilesFileList(List<MultipartFile> multipartFiles) throws IOException {
        //반환될 파일 리스트
        List<File> fileList = new ArrayList<>();



        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        for (MultipartFile multipartFile : multipartFiles) {

            //multipartFiles.isEmpty()로 반복문 밖에 두면 리스트에 비어 있는 element 때문에 쓰레기 값이 저장됨
            if(multipartFile.isEmpty()){
                return fileList;
            }

            UUID uuid = UUID.randomUUID();
            //저장될 fileName
            String fileName = uuid + "_" + multipartFile.getOriginalFilename();
            //파일 저장
            Path saveFilePath = Paths.get(projectPath + "\\" + fileName);
            multipartFile.transferTo(saveFilePath);

            File file = File.builder()
                    .origFilename(multipartFile.getOriginalFilename())
                    .filename(fileName)
                    .filePath(projectPath)
                    .build();
            fileList.add(file);
        }
        return fileList;

    }


}
