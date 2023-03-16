package com.third_project.third_project.main.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.third_project.third_project.main.vo.request.POSTGameNoticeVO;


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

import com.third_project.third_project.entity.ExVideoEntity;
import com.third_project.third_project.entity.GameNoticeEntity;
import com.third_project.third_project.main.vo.ErrorResponse;
import com.third_project.third_project.main.vo.exceptionHendler.ExceptionHendler;
import com.third_project.third_project.main.vo.response.GetDetailNoticeVO;
import com.third_project.third_project.main.vo.response.GetNoticeVO;
import com.third_project.third_project.main.vo.response.GetVideoVO;
import com.third_project.third_project.main.vo.response.POSTNoticeVO;
import com.third_project.third_project.main.vo.response.ResponseMessage;
import com.third_project.third_project.repository.ExVideoRepository;
import com.third_project.third_project.repository.GameNoticeRepository;
import com.third_project.third_project.utilities.RandomNameUtils;

import jakarta.transaction.Transactional;

@Service
public class NoticeService{
    @Autowired GameNoticeRepository gameNoticeRepo;
    @Autowired ExVideoRepository exVideoRepo;
    // 영상파일 저장경로
    @Value("${file.video.exercise}") String excriseVideoPath;

    // 게임 공지사항 등록( 이미지, 영상 첨부가능 )
    // 입력할때 제목, 내용, 영상, 운동종류를 받음
    @Transactional
    public ResponseMessage postNotice(POSTGameNoticeVO data){
        GameNoticeEntity gameNoticeEntity = null;
        ExVideoEntity exVideoEntity = null;

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
        else{
            try{
                String contentType = data.getFiles().getContentType();
                String originalFileExtension = "";
                if (contentType.contains("image/jpeg")) {
                    originalFileExtension = ".jpg";
                }
                if (contentType.contains("video/mp4")) {
                    originalFileExtension = ".mp4";
                }
                    // String url = UUID.randomUUID().toString();

                    // if (ObjectUtils.isEmpty(contentType))  // 확장자명이 없다면(잘못된 파일)
                    //     break;
                    String newFileName = "Notice_" + Calendar.getInstance().getTimeInMillis() + "." + originalFileExtension;
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
            if(notice == null)       throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("없는 글번호입니다.")));
            return notice;
        }

}