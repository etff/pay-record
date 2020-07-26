package com.toyproject.payrecord.global.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class TimeUtilTest {

    @ParameterizedTest
    @ValueSource(strings = {"090", "42", "99999", "-999"})
    void length(String time) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TimeUtil.ParseToIntHour(time));
    }

    @DisplayName(value = "시간에 빈 문자열 또는 null 값을 입력할 경우, IllegalArgumentException 이 발생")
    @ParameterizedTest
    @NullAndEmptySource
    void emptyOrNull(final String time) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TimeUtil.ParseToIntHour(time));
    }

    @Test
    void convert() {
        // given
        String time = "0930";

        // when
        int expected = TimeUtil.ParseToIntHour(time);

        // then
        assertThat(expected).isEqualTo(570);
    }
}
