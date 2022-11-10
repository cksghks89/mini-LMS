package com.example.minilms.admin.model;

import lombok.Data;

@Data
public class MemberStatusInput {
    String userId;
    String userStatus;
    String password;
}
