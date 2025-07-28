package com.logger.api.model.dtos;

import java.time.LocalDate;
import com.logger.api.enums.LogEnum;

public class LogFilterDTO {
    public LocalDate startDate;
    public LocalDate endDate;
    public LogEnum level;
}
