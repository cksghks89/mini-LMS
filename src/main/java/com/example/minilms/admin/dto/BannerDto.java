package com.example.minilms.admin.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerDto {
    long id;

    String bannerId;

    String linkPath;
    long sortValue;
    boolean isPublic;
    String alterText;
    boolean isTargetBlank;

    // 파일 저장
    String fileName;
    String urlFileName;

    // 페이징 처리
    long totalCount;
    long seq;
}
