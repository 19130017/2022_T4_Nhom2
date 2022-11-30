package com.dothanhbinh.weather.mapper;

import com.dothanhbinh.weather.dto.DateDimDto;
import com.dothanhbinh.weather.entity.DateDim;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("dateMapper")
public class DateDimMapper implements IMapper<DateDim, DateDimDto> {
    @Override
    public DateDim toEntity(DateDimDto dto) {
        if (dto == null) return null;
        return DateDim.builder()
                .date_id(dto.getDate_id())
                .date(dto.getDate())
                .year(dto.getYear())
                .month(dto.getMonth())
                .month_of_year(dto.getMonth_of_year())
                .day_of_month(dto.getDay_of_month())
                .day(dto.getDay())
                .day_of_week(dto.getDay_of_week())
                .weekend(dto.getWeekend())
                .day_of_year(dto.getDay_of_year())
                .week_of_year(dto.getWeek_of_year())
                .quarter(dto.getQuarter())
                .previous_day(dto.getPrevious_day())
                .next_day(dto.getNext_day())
                .build();
    }

    @Override
    public DateDimDto toDto(DateDim entity) {
        if (entity == null) return null;
        return DateDimDto.builder()
                .date_id(entity.getDate_id())
                .date(entity.getDate())
                .year(entity.getYear())
                .month(entity.getMonth())
                .month_of_year(entity.getMonth_of_year())
                .day_of_month(entity.getDay_of_month())
                .day(entity.getDay())
                .day_of_week(entity.getDay_of_week())
                .weekend(entity.getWeekend())
                .day_of_year(entity.getDay_of_year())
                .week_of_year(entity.getWeek_of_year())
                .quarter(entity.getQuarter())
                .previous_day(entity.getPrevious_day())
                .next_day(entity.getNext_day())
                .build();
    }

    @Override
    public List<DateDim> toEntities(List<DateDimDto> dateDimDtos) {
        List<DateDim> dates = new ArrayList<>();
        for (DateDimDto dateDimDto : dateDimDtos) {
            dates.add(toEntity(dateDimDto));
        }
        return dates;
    }

    @Override
    public List<DateDimDto> toDtos(List<DateDim> entities) {
        List<DateDimDto> dateDimDtos = new ArrayList<>();
        for (DateDim entity : entities) {
            dateDimDtos.add(toDto(entity));
        }
        return dateDimDtos;
    }
}
