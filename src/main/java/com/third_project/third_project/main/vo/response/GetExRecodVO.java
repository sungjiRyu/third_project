package com.third_project.third_project.main.vo.response;

import java.time.LocalDate;
import java.time.LocalTime;

public interface GetExRecodVO {
    public Long      getIsSeq();
    public Long      getIsMiSeq();
    public Long      getIsEtSeq();
    public LocalDate getIsRegDt();
    public LocalTime getIsTime();

    
}
