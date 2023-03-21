package com.third_project.third_project.main.vo.response;

import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostExRecord {
    @Schema(description = "로그인한 회원 seq", example = "1")
    private Long miSeq;
    @Schema(description = "운동종목(5.걷기 6.사이클링 7. 요가 8. 댄스 9. 코어트레이닝 10. 필라테스 11. 수영 12. 하이킹)", example = "5")
    private Long etSeq;
    @Schema(description = "운동시간", example = "00:09:00")
    private LocalTime time;
}
