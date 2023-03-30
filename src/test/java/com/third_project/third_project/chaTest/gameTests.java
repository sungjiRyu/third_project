package com.third_project.third_project.chaTest;

import com.third_project.third_project.entity.*;
import com.third_project.third_project.game.service.ScoreService;
import com.third_project.third_project.game.vo.*;
import com.third_project.third_project.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class gameTests {
@Value("${file.video.exercise.game}") String game_video_path;
	@Autowired MemberRankingViewRepository mrRepo;
	@Autowired TripleRankingViewRepository tRepo;
	@Autowired MemberInfoRepository miRepo;
	@Autowired ExTypeRepository eRepo;
	@Autowired GameScoreRepository gsRepo;

	@Autowired
	ScoreService service;

	@Test
	@Transactional
	public void testGetMemberScore() { // 회원 개인 점수 조회 테스트

		MemberRankingView mrentity = mrRepo.findByMiSeq(1l);

		ScoreResponseVO response = new ScoreResponseVO();

		if(mrentity == null) { // 회원의 기록이 없을때
			response = ScoreResponseVO.builder()
					.status(false)
					.message("저번주의 기록이 없습니다")
					.code(HttpStatus.BAD_REQUEST)
					.build();
			assertNotNull(response); // response 객체가 제대로 생성되었는데 확인 실패시 테스트 통과 안됨
			fail("저번주의 기록이 없습니다");
		}
		else if(mrentity.getMingUrl() == null) { //회원의 이미지 url 값이 없을때
			response = ScoreResponseVO.builder()
					.status(true)
					.message("지난주의 성적입니다")
					.code(HttpStatus.OK)
					.score(mrentity.getGsTime())
					.nickname(mrentity.getMiNickName())
					.rank(mrentity.getRank())
					.url(null)
					.ban(mrentity.getMiClassNum())
					.build();
			assertNotNull(response);
			System.out.println(response);
		}
		else {
			response = ScoreResponseVO.builder()
					.status(true)
					.message("지난주의 성적입니다")
					.code(HttpStatus.OK)
					.score(mrentity.getGsTime())
					.nickname(mrentity.getMiNickName())
					.rank(mrentity.getRank())
					.url(mrentity.getMingUrl())
					.ban(mrentity.getMiClassNum())
					.build();
			assertNotNull(response);
			System.out.println(response);
		}
	}

	@Test
	@Transactional
	public void getTotalScore() { // 1~3위까지의 랭킹 조회 테스트
		List<TripleRankingView> tlist = tRepo.findByEtSeq(1L);
		List<RankListResponseVO> entity = new ArrayList<>();

		for(int i=0; i< tlist.size(); i++) {
			RankListResponseVO vo = RankListResponseVO.builder()
					.ban(tlist.get(i).getMiClassNum())
					.nickname(tlist.get(i).getMiNickName())
					.rank(tlist.get(i).getRank())
					.score(tlist.get(i).getGsTime())
					.url(tlist.get(i).getMingUrl())
					.build();
			entity.add(vo);
		}
		if(tlist.isEmpty()) { // list의 값이 비었을때 예외처리
			RankResponseVO response = RankResponseVO.builder()
					.status(false)
					.message("해당 운동은 저번주에 진행되지 않았습니다")
					.code(HttpStatus.BAD_REQUEST).build();
			assertNotNull(response);
			System.out.println(response);
		}
		RankResponseVO response = RankResponseVO.builder()
				.status(true)
				.message("지난주 상위 3위까지의 기록입니다")
				.code(HttpStatus.OK)
				.list(entity)
				.build();
		assertNotNull(response);
		System.out.println(response);
	}

	@Test
	@Transactional
	public void insertGameRecord() { // 게임 기록 테스트
		GameResponseVO response = new GameResponseVO();
		MemberInfoEntity entity = miRepo.findByMiSeq(1L);
		ExTypeEntity exentity = eRepo.findByEtSeq(1L);

		if(entity==null) {
			response = GameResponseVO.builder()
					.status(false)
					.message("해당 회원은 존재하지 않습니다")
					.code(HttpStatus.BAD_REQUEST)
					.build();
			assertNotNull(response);
			System.out.println(response);
		}

		else if(exentity==null) {
			response = GameResponseVO.builder()
					.status(false)
					.message("해당 운동은 존재하지 않습니다")
					.code(HttpStatus.BAD_REQUEST)
					.build();
			assertNotNull(response);
			System.out.println(response);
		}

		LocalTime time = LocalTime.parse("00:00:15");

		GameScoreEntity score = GameScoreEntity.builder()
				.member(entity)
				.exType(exentity)
				.gsTime(time)
				.build();

		response = GameResponseVO.builder()
				.status(true)
				.message("기록 등록이 완료되었습니다")
				.code(HttpStatus.OK)
				.build();

		gsRepo.save(score);
		assertNotNull(response);
		System.out.println("gs_mi_seq : "+score.getMember().getMiSeq());
		System.out.println("gs_et_seq : "+score.getExType().getEtSeq());
		System.out.println("score : "+score.getGsTime());
		System.out.println(response);
	}
}
