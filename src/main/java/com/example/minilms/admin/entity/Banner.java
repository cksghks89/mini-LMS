package com.example.minilms.admin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
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
    String openMethod;

    LocalDateTime regDt;

    // 파일 저장
    String fileName;
    String urlFileName;
}
