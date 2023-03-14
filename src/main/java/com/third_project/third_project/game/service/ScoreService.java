package com.third_project.third_project.game.service;

import com.third_project.third_project.entity.GameScoreEntity;
import com.third_project.third_project.game.vo.ScoreResponseVO;
import com.third_project.third_project.repository.GameScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final GameScoreRepository gsRepo;

    public ScoreResponseVO getMemberScore(Long seq) {
//        Optional<GameScoreEntity> entityOpt = gsRepo.
        return null;
    }
}
