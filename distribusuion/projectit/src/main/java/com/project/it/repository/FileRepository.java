package com.project.it.repository;

import com.project.it.domain.FileUpload;
import com.project.it.dto.FileUploadDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends JpaRepository<FileUpload, Long> {
    //파일리스트 검색-category+assetNum(asset 별)
    @Query("select new com.project.it.dto.FileUploadDTO(f.fno, f.category, f.assetNum, f.originFileName, f.saveFileName, f.folderPath, f.size, f.deleteOrNot) from FileUpload f where f.category=:category and f.assetNum=:assetNum")
    List<FileUploadDTO> findAssetFileList(@Param("category") String category, @Param("assetNum") Long assetNum);

    //삭제처리 - asset으로 접근시
    @Modifying
    @Query("update FileUpload file set file.deleteOrNot = :state where file.category=:category and file.assetNum=:assetNum")
    void updateDelState(@Param("category") String category, @Param("assetNum") Long assetNum, @Param("state")boolean state);

    //삭제(assetNum으로 여러개 함께 삭제)
    @Query("delete from FileUpload f where f.category=:category and f.assetNum=:assetNum")
    void deleteFilesByAssetNum(@Param("category") String category, @Param("assetNum") Long assetNum);

    //전체 파일 검색(Paging)
    @Query("select files from FileUpload files ")
    Page<FileUpload> searchAllWithPaging(Pageable pageable);

    //전체 파일 검색(Paging) - 삭제된 파일만
    @Query("select files from FileUpload files where files.deleteOrNot=true ")
    Page<FileUpload> searchDeletedFileWithPaging(Pageable pageable);

    //전체 파일 검색(Paging) - 삭제 안 된 파일만
    @Query("select files from FileUpload files where files.deleteOrNot=false ")
    Page<FileUpload> searchFilesWithPaging(Pageable pageable);

    //삭제처리 - fileList
    @Modifying
    @Query("update FileUpload file set file.deleteOrNot=:state where file.fno = :fno")
    int updateFileState(@Param("fno")Long fno, @Param("state")boolean state);

    //영구삭제 - fileList
    @Query("delete from FileUpload file where file.fno=:fno")
    int deleteByFileList(Long fno);
}
