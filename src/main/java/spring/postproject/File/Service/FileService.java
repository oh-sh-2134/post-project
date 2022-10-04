package spring.postproject.File.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.File.Entity.File;
import spring.postproject.File.Repository.FileRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    final private FileRepository fileRepository;

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

    @Transactional
    public File findFile(Long id){
        return fileRepository.findByFileId(id).orElseThrow(ExceptionBoard.NOT_FOUND_FILE::getException);
    }


}
