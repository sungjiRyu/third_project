package com.third_project.third_project.file.service;

import com.third_project.third_project.file.exception.InvalidInputException;
import com.third_project.third_project.file.vo.FileDownloadVO;
import com.third_project.third_project.repository.CertificationVideoReposritory;
import com.third_project.third_project.repository.ExVideoRepository;
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

    @Value("${file.video.exercise.game}") String game_video_path;
    @Value("${file.video.exercise.detail}") String detail_video_path;
    @Value("${file.video.exercise.main}") String main_video_path;
    @Value("${file.image.exercise.detail}") String detail_image_path;
    @Value("${file.image.exercise.main}")   String   main_image_path;
    @Value("${file.image.exercise.member}")   String   member_image_path;

    public FileDownloadVO downloadVideoFile(String type, String uri) { //동영상 파일 다운로드
        Path folderLocation = null;

        if (type.equals("game")) {
            folderLocation = Paths.get(game_video_path);
        }
        else if (type.equals("detail")) {
            folderLocation = Paths.get(detail_video_path);
        }
        else if (type.equals("main")) {
            folderLocation = Paths.get(main_video_path);
        }
        else {
            throw new InvalidInputException("유효하지 않은 경로입니다.");
        }

        String filename = "";
        if (type.equals("game")) {
            if (cvRepo.findByCvUrlEquals(uri)==null) {
                throw new InvalidInputException("파일이 존재하지 않습니다.");
            }
            else {
                filename = cvRepo.findByCvUrlEquals(uri).getCvName();
            }
        }
        else if (type.equals("detail")) {
            if (cvRepo.findByCvUrlEquals(uri)==null) {
                throw new InvalidInputException("파일이 존재하지 않습니다.");
            }
            else {
                filename = cvRepo.findByCvUrlEquals(uri).getCvName();
            }
        }
        else if (type.equals("main")) {
            if (evRepo.findByEvUrlEquals(uri)==null) {
                throw new InvalidInputException("파일이 존재하지 않습니다.");
            }
            else {
                filename = evRepo.findByEvUrlEquals(uri).getEvName();
            }
        }
        return new FileDownloadVO(folderLocation, filename);
    }

//    public FileDownloadVO downloadImageFile(String type, String uri) { //이미지 파일 다운로드
//        Path folderLocation = null;
//
//        if (type.equals("member")) {
//            folderLocation = Paths.get(game_image_path);
//        }
//        else if (type.equals("detail")) {
//            folderLocation = Paths.get(detail_image_path);
//        }
//        else if (type.equals("main")) {
//            folderLocation = Paths.get(main_image_path);
//        }
//        else {
//            throw new InvalidInputException("유효하지 않은 경로입니다.");
//        }
//
//        String filename = "";
//        if (type.equals("member")) {
//            if (cvRepo.findByCvUrlEquals(uri)==null) {
//                throw new InvalidInputException("파일이 존재하지 않습니다.");
//            }
//            else {
//                filename = cvRepo.findByCvUrlEquals(uri).getCvName();
//            }
//        }
//        else if (type.equals("detail")) {
//            if (cvRepo.findByCvUrlEquals(uri)==null) {
//                throw new InvalidInputException("파일이 존재하지 않습니다.");
//            }
//            else {
//                filename = cvRepo.findByCvUrlEquals(uri).getCvName();
//            }
//        }
//        else if (type.equals("main")) {
//            if (cvRepo.findByCvUrlEquals(uri)==null) {
//                throw new InvalidInputException("파일이 존재하지 않습니다.");
//            }
//            else {
//                filename = cvRepo.findByCvUrlEquals(uri).getCvName();
//            }
//        }
//        return new FileDownloadVO(folderLocation, filename);
//    }
}
