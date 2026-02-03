package com.url.shortner_sb.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ClickEventDTO {
    public LocalDate getClickDate() {
        return clickDate;
    }

    public void setClickDate(LocalDate clickDate) {
        this.clickDate = clickDate;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    private LocalDate clickDate;
    private Long count;

}
