package com.example.minilms.admin.dto;

import com.example.minilms.admin.entity.Banner;
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
    String openMethod;

    // 파일 저장
    String fileName;
    String urlFileName;

    // 페이징 처리
    long totalCount;
    long seq;

    public static BannerDto of(Banner banner){
        return BannerDto.builder()
                .id(banner.getId())
                .bannerId(banner.getBannerId())
                .linkPath(banner.getLinkPath())
                .sortValue(banner.getSortValue())
                .isPublic(banner.isPublic())
                .alterText(banner.getAlterText())
                .openMethod(banner.getOpenMethod())
                .fileName(banner.getFileName())
                .urlFileName(banner.getUrlFileName())
                .build();
    }
}
