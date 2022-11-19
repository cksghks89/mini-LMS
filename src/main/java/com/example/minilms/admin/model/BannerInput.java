package com.example.minilms.admin.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerInput{
    long id;

    String bannerId;
    String linkPath;
    String openMethod;
    long sortValue;
    boolean isPublic;

    // ADD
    String fileName;
    String urlFileName;
}
