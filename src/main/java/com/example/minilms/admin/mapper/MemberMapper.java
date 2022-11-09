package com.example.minilms.admin.mapper;

import com.example.minilms.admin.dto.MemberDto;
import com.example.minilms.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    long selectListCount(MemberParam parameter);
    List<MemberDto> selectList(MemberParam parameter);
}
