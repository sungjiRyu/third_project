package com.third_project.third_project.main.vo.response;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetLevelExVO {
    @Schema(description = "운동종목" , example = "1")
    public Long etSeq;
    @Schema(description = "운동명" , example = "오래달리기")
    public String etName;
    @Schema(description = "운동 상세설명" , example = "달려요")
    public String etDetail;
    @Schema(description = "운동 썸네일" , example = "url")
    public String url;
    
    
    
    

}
