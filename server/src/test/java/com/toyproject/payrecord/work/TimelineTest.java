package com.toyproject.payrecord.work;

import com.toyproject.payrecord.work.domain.Timeline;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class TimelineTest {

    @DisplayName(value = "이름에 빈 문자열 또는 null 값을 입력할 경우, IllegalArgumentException 이 발생")
    @ParameterizedTest
    @NullAndEmptySource
    void testEmptyOrNull(final String inputText) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Timeline(inputText));
    }
}
