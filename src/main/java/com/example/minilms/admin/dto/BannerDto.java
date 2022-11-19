package com.example.minilms.admin.dto;

import com.example.minilms.admin.entity.Banner;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerDto {
    Long id;

    String bannerId;

    String linkPath;
    long sortValue;
    boolean isPublic;
    String alterText;
    String openMethod;

    // 파일 저장
    String fileName;
    String urlFileName;

    LocalDateTime regDt;

    // 페이징 처리
    long totalCount;
    long seq;

    public String getRegDtText(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return regDt != null ? regDt.format(dateTimeFormatter) : "";
    }

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
                .regDt(banner.getRegDt())
                .build();
    }

    public static List<BannerDto> of(List<Banner> bannerList){
        if(bannerList.isEmpty()){
            return new ArrayList<>();
        }
        return bannerList.stream().map(BannerDto::of).collect(Collectors.toList());
    }
}
