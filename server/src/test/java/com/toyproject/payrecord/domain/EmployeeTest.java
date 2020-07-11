package com.toyproject.payrecord.domain;

import com.toyproject.payrecord.domain.employee.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class EmployeeTest {

    private String email;
    private String password;
    private String fcmToken;

    @BeforeEach
    public void setUp() {
        password = "aaa";
        fcmToken = "aaa";
    }

    @DisplayName(value = "이름에 빈 문자열 또는 null 값을 입력할 경우, IllegalArgumentException 이 발생")
    @ParameterizedTest
    @NullAndEmptySource
    void testEmptyOrNull(final String inputText) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Employee(inputText, password, fcmToken));
    }
}
