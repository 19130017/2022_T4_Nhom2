package com.dothanhbinh.weather.mapper;

import java.util.List;

public interface IMapper<T, Dto> {
    public T toEntity(Dto dto);

    public Dto toDto(T entity);

    public List<T> toEntities(List<Dto> dtos);

    public List<Dto> toDtos(List<T> entities);
}
