package com.third_project.third_project.main.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.third_project.third_project.main.vo.request.POSTGameNoticeVO;
import com.third_project.third_project.main.vo.request.UPDATEGameNoticeVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;


import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.entity.ExVideoEntity;
import com.third_project.third_project.entity.GameNoticeEntity;
import com.third_project.third_project.main.vo.ErrorResponse;
import com.third_project.third_project.main.vo.exceptionHendler.ExceptionHendler;
import com.third_project.third_project.main.vo.response.GetDetailNoticeVO;
import com.third_project.third_project.main.vo.response.GetNoticeVO;
import com.third_project.third_project.main.vo.response.GetVideoVO;
import com.third_project.third_project.main.vo.response.POSTNoticeVO;
import com.third_project.third_project.main.vo.response.ResponseMessage;
import com.third_project.third_project.repository.ExTypeRepository;
import com.third_project.third_project.repository.ExVideoRepository;
import com.third_project.third_project.repository.GameNoticeRepository;
import com.third_project.third_project.utilities.RandomNameUtils;

import jakarta.transaction.Transactional;

@Service
public class NoticeService{
    @Autowired GameNoticeRepository gameNoticeRepo;
    @Autowired ExVideoRepository exVideoRepo;
    @Autowired ExTypeRepository exTypeRepo;
    // 영상파일 저장경로
    @Value("${file.video.exercise.main}") String excriseVideoPath;
   
    // 게임 공지사항 등록( 이미지, 영상 첨부가능 )
    // 입력할때 제목, 내용, 영상, 운동종류를 받음
    @Transactional
    public ResponseMessage postNotice(POSTGameNoticeVO data){
        GameNoticeEntity gameNoticeEntity = null;
        ExVideoEntity exVideoEntity = null;
        ExTypeEntity exTypeEntity = null;
        exTypeEntity = exTypeRepo.findByEtSeq(data.getGnEtSeq());

        if(data.getGnTitle() == null)
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("제목을 입력하세요.")));
        else if(data.getGnContent() == null)
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("내용을 입력하세요.")));
        else if(data.getGnEtSeq() == null)
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("운동을 선택해주세요.")));
        else if(data.getFiles() == null)
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("첨부할 영상을 선택해주세요.")));
        else if(data.getGnEtSeq() == null)
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("운동을 선택해주세요.")));
        else if(data.getGnEtSeq() == null)
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("선택한 운동이 존재하지 않습니다.")));            
        else if(exTypeEntity.getEtEsSeq() != 3)
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("게임용 운동을 선택해주세요.")));

        else{
            try{
                String contentType = data.getFiles().getContentType();
                String originalFileExtension = "";
                if (contentType.contains("video/mp4")) {
                    originalFileExtension = ".mp4";
                }
                    // String url = UUID.randomUUID().toString();

                    // if (ObjectUtils.isEmpty(contentType))  // 확장자명이 없다면(잘못된 파일)
                    //     break;
                    String newFileName = "Notice_" + Calendar.getInstance().getTimeInMillis() + originalFileExtension;
                    String url = excriseVideoPath + "/" + newFileName;
                     exVideoEntity = ExVideoEntity.builder()
                            .evName(newFileName)
                            .evUrl(url)
                            .evEtSeq(data.getGnEtSeq())
                            .build();
                    exVideoRepo.save(exVideoEntity);
                    File file = new File(excriseVideoPath);
                    // 저장할 위치의 디렉토리가 존재하지 않을 경우 디렉토리를 생성
                    if(!file.exists())  file.mkdirs();
                    Path savePath = Paths.get(excriseVideoPath+File.separator+newFileName);
                    try{
                    data.getFiles().transferTo(savePath);
                }
                    catch(Exception e){
                        e.printStackTrace();
                    }
            }
            catch(Exception e){ 
                e.printStackTrace();
            }
            // ExVideoEntity exVideoEntity = exVideoRepo.findByEvSeq(());
            gameNoticeEntity = GameNoticeEntity.builder()
            .gnTitle(data.getGnTitle())
            .gnContent(data.getGnContent())
            .gnEtSeq(data.getGnEtSeq())
            .gnMiSeq(data.getGnMiSeq())
            .gnEvSeq(exVideoEntity.getEvSeq())
            .gnRegDt(null)
            .build();

            gameNoticeRepo.save(gameNoticeEntity);
            ResponseMessage response = new ResponseMessage(true, HttpStatus.OK, "공지사항이 등록되었습니다.");
            return response;
        }
    }

        // 공지사항 제목 조회
        public List<GetNoticeVO> GetNotice(Integer page, Integer size){
            List<GetNoticeVO> data = null; // 공지사항 제목과 등록일, 운동종목을 리스트로 출력
            if(page == null) page = 0;
            if(size == null) size = 10;
            PageRequest pageRequest = PageRequest.of(page,size,Sort.by("gnRegDt").descending());
            data = gameNoticeRepo.findAllBy(pageRequest);
            return data;
        }

        // 공지사항 상세 조회
        public GetDetailNoticeVO GetDetailNotice(Long gnSeq){
            GetDetailNoticeVO notice = null;
            notice = gameNoticeRepo.findByGnSeq(gnSeq);
            if(notice == null)  throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("없는 글번호입니다.")));
            return notice;
        }

        // 공지사항 수정
        @Transactional
        public ResponseMessage UpdateNotice(UPDATEGameNoticeVO data){
            GameNoticeEntity notice = null;
            ExVideoEntity exVideoEntity = null;
            notice = gameNoticeRepo.findBygnSeq(data.getGnSeq());
            if(notice == null)
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("존재하지않는 공지사항입니다.")));
            else if(notice.getType().getEtEsSeq() != 3)
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("게임용 운동만 선택할 수 있습니다.")));
            else{
                if(data.getGnTitle() != null){
                    notice.setGnTitle(data.getGnTitle());
                }
                if(data.getGnContent() != null){
                    notice.setGnContent(data.getGnContent());
                }
                if(data.getGnEtSeq() != null){
                    notice.setGnEtSeq(data.getGnEtSeq());
                }
                if(data.getFiles() != null){
                // 파일 삭제
                exVideoEntity = exVideoRepo.findByEvSeq(notice.getGnEvSeq());
                if (exVideoEntity == null) {
                    throw new RuntimeException("삭제할 동영상 파일이 존재하지 않습니다.");
                }
                exVideoRepo.delete(exVideoEntity);
                Path filePath = Paths.get(notice.getVideo().getEvUrl());
                try{
                Files.delete(filePath);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                // 파일 추가
                try{
                    String contentType = data.getFiles().getContentType();
                    String originalFileExtension = "";
                    if (contentType.contains("video/mp4")) {
                        originalFileExtension = ".mp4";
                    }
                        // String url = UUID.randomUUID().toString();
    
                        // if (ObjectUtils.isEmpty(contentType))  // 확장자명이 없다면(잘못된 파일)
                        //     break;
                        String newFileName = "Notice_" + Calendar.getInstance().getTimeInMillis() + originalFileExtension;
                        String url = excriseVideoPath + "/" + newFileName;
                        ExVideoEntity newExVideoEntity = ExVideoEntity.builder()
                                .evName(newFileName)
                                .evUrl(url)
                                .evEtSeq(data.getGnEtSeq())
                                .build();
                        exVideoRepo.save(newExVideoEntity);
                        // 새로 저장된 파일과 공지사항의 영상파일 외래키를 연결
                        notice.setGnEvSeq(newExVideoEntity.getEvSeq());
                        File file = new File(excriseVideoPath);
                        // 저장할 위치의 디렉토리가 존재하지 않을 경우 디렉토리를 생성
                        if(!file.exists())  file.mkdirs();
                        Path savePath = Paths.get(excriseVideoPath+File.separator+newFileName);
                        data.getFiles().transferTo(savePath);
                        }  
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            
            ResponseMessage response = new ResponseMessage(true, HttpStatus.OK, "공지사항이 수정되었습니다.");
            return response;
        }
        
        // 공지사항 삭제
        @Transactional
        public ResponseMessage DeleteNotice(Long gnSeq){
            GameNoticeEntity notice = null;
            ExVideoEntity video = null;
            notice = gameNoticeRepo.findBygnSeq(gnSeq);
            if(notice == null)
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("존재하지않는 공지사항입니다.")));
            video = exVideoRepo.findByEvSeq(notice.getGnEvSeq());
            if(video == null)
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("파일이 존재하지 않습니다.")));
            gameNoticeRepo.delete(notice);
            exVideoRepo.delete(video);
            
            ResponseMessage response = new ResponseMessage(true, HttpStatus.OK, "공지사항이 삭제되었습니다.");
            return response;
        }


        

}