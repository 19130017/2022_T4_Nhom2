package com.dothanhbinh.weather.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Entity
@Getter
@Setter
@Table(name = "date_dim")
public class DateDim {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "date_id", nullable = false)
    private Long date_id;

    @Column(name = "date")
    private Date date;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private String month;

    @Column(name = "month_of_year")
    private String month_of_year;

    @Column(name = "day_of_month")
    private Integer day_of_month;

    @Column(name = "day")
    private String day;

    @Column(name = "day_of_week")
    private Integer day_of_week;

    @Column(name = "weekend")
    private String weekend;

    @Column(name = "day_of_year")
    private Integer day_of_year;

    @Column(name = "week_of_year")
    private String week_of_year;

    @Column(name = "quarter")
    private Integer quarter;

    @Column(name = "previous_day")
    private Date previous_day;

    @Column(name = "next_day")
    private Date next_day;


    @OneToMany(mappedBy = "forecast_date")
    Set<FactWeather> dates;

}
