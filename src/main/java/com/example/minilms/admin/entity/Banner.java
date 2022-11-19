package com.example.minilms.admin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String bannerId;

    String linkPath;
    long sortValue;
    boolean isPublic;
    String alterText;
    boolean isTargetBlank;

    LocalDateTime regDt;

    // 파일 저장
    String fileName;
    String urlFileName;
}
