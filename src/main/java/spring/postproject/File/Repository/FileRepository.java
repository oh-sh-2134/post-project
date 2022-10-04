package spring.postproject.File.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.postproject.File.Entity.File;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File,Long> {
    Optional<File> findByFileId(Long id);
}
