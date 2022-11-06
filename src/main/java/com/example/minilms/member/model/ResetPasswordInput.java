package com.example.minilms.member.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordInput {
    private String userId;
    private String userName;

    private String id;
    private String password;
}
