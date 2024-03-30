package com.ankush003.MovieBase.mappers.impl;

import com.ankush003.MovieBase.mappers.Mapper;
import com.ankush003.MovieBase.model.dto.ReviewDto;
import com.ankush003.MovieBase.model.entities.ReviewEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewMapperImpl implements Mapper<ReviewEntity, ReviewDto> {
    private ModelMapper modelMapper;

    @Autowired
    public ReviewMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewDto mapTo(ReviewEntity entity) {
        return modelMapper.map(entity, ReviewDto.class);
    }

    @Override
    public ReviewEntity mapFrom(ReviewDto dto) {
        return modelMapper.map(dto, ReviewEntity.class);
    }

    @Override
    public List<ReviewDto> mapTo(List<ReviewEntity> entities) {
        return entities.stream().map(this::mapTo).collect(Collectors.toList());
    }

    @Override
    public List<ReviewEntity> mapFrom(List<ReviewDto> dtos) {
        return dtos.stream().map(this::mapFrom).collect(Collectors.toList());
    }
}
