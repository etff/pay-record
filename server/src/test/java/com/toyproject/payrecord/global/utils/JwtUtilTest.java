package com.toyproject.payrecord.global.utils;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private static final String SECRET = "1234567890123456789012345678901234567890";

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @DisplayName("토큰 생성 테스트")
    @Test
    public void createToken() {

        JwtUtil jwtUtil = new JwtUtil(SECRET);

        String token = jwtUtil.createToken(1L,"abc@abc.com");

        assertThat(token, containsString("."));
    }

}
