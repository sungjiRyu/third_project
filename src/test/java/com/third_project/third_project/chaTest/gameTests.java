package com.third_project.third_project.chaTest;

import com.third_project.third_project.entity.MemberRankingView;
import com.third_project.third_project.game.service.ScoreService;
import com.third_project.third_project.game.vo.ScoreResponseVO;
import com.third_project.third_project.repository.MemberRankingViewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import static org.mockito.Mockito.*;

@SpringBootTest
class gameTests {

	@Autowired
	MemberRankingViewRepository mrRepo;
	@Autowired
	ScoreService service;

	@Test
	public void testGetMemberScore() {
		// given
		Long seq = 1L;
		MemberRankingView mrentity = new MemberRankingView();
		mrentity.setMiSeq(seq);
		mrentity.setMingUrl("http://example.com");
		mrentity.setGsTime(LocalTime.parse("00:00:15"));
		mrentity.setMiNickName("TestUser");
		mrentity.setRank(1);
		mrentity.setMiClassNum("ClassA");

		// when
		when(mrRepo.findByMiSeq(seq)).thenReturn(mrentity);

		// then
		ScoreResponseVO response = service.getMemberScore(seq);
		assertNotNull(response);
		assertTrue(response.getStatus());
		assertEquals("지난주의 성적입니다", response.getMessage());
		assertEquals(HttpStatus.OK, response.getCode());
		assertEquals(15, response.getScore());
		assertEquals("TestUser", response.getNickname());
		assertEquals(1, response.getRank());
		assertEquals("http://example.com", response.getUrl());
		assertEquals("ClassA", response.getBan());
	}
}
