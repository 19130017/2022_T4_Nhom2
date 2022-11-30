package com.dothanhbinh.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DateDimDto {
    private Long date_id;
    private Date date;
    private Integer year;
    private String month;
    private String month_of_year;
    private Integer day_of_month;
    private String day;
    private Integer day_of_week;
    private String weekend;
    private Integer day_of_year;
    private String week_of_year;
    private Integer quarter;
    private Date previous_day;
    private Date next_day;
}
