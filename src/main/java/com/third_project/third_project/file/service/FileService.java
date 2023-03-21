package com.third_project.third_project.file.service;

import com.third_project.third_project.file.exception.InvalidInputException;
import com.third_project.third_project.file.vo.FileDownloadVO;
import com.third_project.third_project.repository.CertificationVideoReposritory;
import com.third_project.third_project.repository.ExImgRepository;
import com.third_project.third_project.repository.ExVideoRepository;
import com.third_project.third_project.repository.MemberImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileService {
    private final CertificationVideoReposritory cvRepo;
    private final ExVideoRepository evRepo;
    private final MemberImgRepository miRepo;
    private final ExImgRepository eiRepo;

    @Value("${file.video.exercise.game}") String game_video_path;
    @Value("${file.video.exercise.notice}") String notice_video_path;
    @Value("${file.image.exercise.detail}") String detail_image_path;
    @Value("${file.image.exercise.thumbnail}")   String thumbnail_image_path;
    @Value("${file.image.exercise.member}")   String member_image_path;

    public FileDownloadVO downloadVideoFile(String type, String url) { //동영상 파일 다운로드
        Path folderLocation = null;

        if (type.equals("notice")) {
            folderLocation = Paths.get(notice_video_path);
        }
        else {
            throw new InvalidInputException("유효하지 않은 경로입니다.");
        }

        String filename = "";
        if (type.equals("notice")) {
            if (evRepo.findByEvUrlEquals(url)==null) {
                throw new InvalidInputException("파일이 존재하지 않습니다.");
            }
            else {
                filename = evRepo.findByEvUrlEquals(url).getEvName();
            }
        }
        return new FileDownloadVO(folderLocation, filename);
    }

    public FileDownloadVO downloadImageFile(String type, String url) { //이미지 파일 다운로드
        Path folderLocation = null;

        if (type.equals("member")) {
            folderLocation = Paths.get(member_image_path);
        }
        else if (type.equals("detail")) {
            folderLocation = Paths.get(detail_image_path);
        }
        else if (type.equals("thumbnail")) {
            folderLocation = Paths.get(thumbnail_image_path);
        }
        else {
            throw new InvalidInputException("유효하지 않은 경로입니다.");
        }

        String filename = "";
        if (type.equals("member")) {
            if (miRepo.findByMimgUrlEquals(url)==null) {
                throw new InvalidInputException("파일이 존재하지 않습니다.");
            }
            else {
                filename = miRepo.findByMimgUrlEquals(url).getMimgName();
            }
        }
        else if (type.equals("detail")) {
            if (eiRepo.findByEimgUrlEquals(url)==null) {
                throw new InvalidInputException("파일이 존재하지 않습니다.");
            }
            else {
                filename = eiRepo.findByEimgUrlEquals(url).getEimgName();
            }
        }
        else if (type.equals("thumbnail")) {
            if (eiRepo.findByEimgUrlEquals(url)==null) {
                throw new InvalidInputException("파일이 존재하지 않습니다.");
            }
            else {
                filename = eiRepo.findByEimgUrlEquals(url).getEimgName();
            }
        }
        return new FileDownloadVO(folderLocation, filename);
    }
}
