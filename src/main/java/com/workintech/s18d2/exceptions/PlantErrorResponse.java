package com.workintech.s18d2.exceptions;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@ToString
@AllArgsConstructor
public class PlantErrorResponse {
    private Integer status;
    private String message;
    private LocalDateTime dateTime;
}
