package com.third_project.third_project.repository;

import com.third_project.third_project.entity.GameNoticeEntity;
import com.third_project.third_project.main.vo.response.GetDetailNoticeVO;
import com.third_project.third_project.main.vo.response.GetNoticeVO;
import com.third_project.third_project.main.vo.response.GetVideoVO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameNoticeRepository extends JpaRepository<GameNoticeEntity, Long> {
    // 공지사항 리스트 출력
    public List<GetNoticeVO> findAllBy(PageRequest pageRequest);
    // 공지사항 글 번호로 검색(상세보기)
    public GetDetailNoticeVO findByGnSeq(Long gnSeq);
    // 공지사항 삭제
    public GameNoticeEntity findBygnSeq(Long gnSeq);

    //공지사항 관리자 페이지  리스트 조회
   Page<GameNoticeEntity> findByGnTitleContains(String gnTitle, Pageable pageable);

}
