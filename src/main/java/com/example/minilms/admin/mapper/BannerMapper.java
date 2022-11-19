package com.example.minilms.admin.mapper;

import com.example.minilms.admin.dto.BannerDto;
import com.example.minilms.admin.model.BannerParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    long selectListCount(BannerParam parameter);
    List<BannerDto> selectList(BannerParam parameter);
}
