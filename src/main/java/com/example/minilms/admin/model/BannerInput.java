package com.example.minilms.admin.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerInput{
    Long id;

    String bannerId;
    String linkPath;
    String openMethod;
    long sortValue;
    String isPublic;
    String alterText;

    // 삭제 리스트
    String idList;

    // ADD
    String fileName;
    String urlFileName;
}
