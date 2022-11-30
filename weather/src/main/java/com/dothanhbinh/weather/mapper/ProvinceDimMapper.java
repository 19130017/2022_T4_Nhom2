package com.dothanhbinh.weather.mapper;

import com.dothanhbinh.weather.dto.ProvinceDimDto;
import com.dothanhbinh.weather.entity.ProvinceDim;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProvinceDimMapper implements IMapper<ProvinceDim, ProvinceDimDto> {
    @Override
    public ProvinceDim toEntity(ProvinceDimDto dto) {
        if (dto == null) return null;
        return ProvinceDim.builder()
                .id(dto.getId())
                .name(dto.getName())
                .code_name(dto.getCode_name())
                .build();
    }

    @Override
    public ProvinceDimDto toDto(ProvinceDim entity) {
        if (entity == null) return null;
        return ProvinceDimDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code_name(entity.getCode_name())
                .build();
    }

    @Override
    public List<ProvinceDim> toEntities(List<ProvinceDimDto> provinceDimDtos) {
        List<ProvinceDim> provinces = new ArrayList<>();
        for (ProvinceDimDto provinceDimDto : provinceDimDtos) {
            provinces.add(toEntity(provinceDimDto));
        }
        return provinces;
    }

    @Override
    public List<ProvinceDimDto> toDtos(List<ProvinceDim> entities) {
        List<ProvinceDimDto> provinceDimDtos = new ArrayList<>();
        for (ProvinceDim entity : entities) {
            provinceDimDtos.add(toDto(entity));
        }
        return provinceDimDtos;
    }
}
