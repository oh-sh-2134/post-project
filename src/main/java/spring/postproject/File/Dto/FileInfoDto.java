package spring.postproject.File.Dto;

import lombok.Builder;
import lombok.Getter;
import spring.postproject.File.Entity.File;


@Getter
public class FileInfoDto {
    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

    @Builder
    public FileInfoDto(File file){
        this.origFilename = file.getOrigFilename();
        this.filePath = file.getFilePath();
        this.filename = file.getFilename();
    }

}
