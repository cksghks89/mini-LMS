package com.example.minilms.member.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberInput {
    private String userId;
    private String userName;
    private String password;
    private String phoneNumber;
}
