package com.example.minilms.member.exception;

public class MemberNotEmailAuthException extends RuntimeException {
    public MemberNotEmailAuthException(String error) {
        super(error);
    }

}
