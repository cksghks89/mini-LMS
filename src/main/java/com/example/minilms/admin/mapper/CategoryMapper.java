package com.example.minilms.admin.mapper;

import com.example.minilms.admin.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDto> selectList(CategoryDto parameter);
}
