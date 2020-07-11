package com.toyproject.payrecord.global.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
    private static final String NOT_EMAIL_PATTERN = "이메일 형식이 올바르지 않습니다.";
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 20;
    private static final String NAME_LENGTH_ERROR = "이름은 2자 이상 20자 이하여야 합니다.";
    private static final String NULL_NOT_ALLOW = "공백을 입력할 수 없습니다.";
    private static final String PASSWORD_LENGTH_ERROR = "비밀번호는 공백을 제외하고 8자 이상 20자 이하여야합니다.";
    private static final String SPECIAL_CHARACTER_NOT_ALLOW = "한글,숫자,영문 10자 이내로 작성하여 주세요.";
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣\\s+]{1,10}");

    private StringUtil() {
    }

    public static boolean isBlank(String value) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return value.trim().isEmpty();
    }

    public static Matcher isPatternMatched(final String name, final Pattern pattern) {
        return pattern.matcher(name);
    }

    public static void validateEmail(final String email) {
        if (isBlank(email)) {
            throw new IllegalArgumentException(NULL_NOT_ALLOW);
        }

        if (!isPatternMatched(email, EMAIL_PATTERN).find()) {
            throw new IllegalArgumentException(NOT_EMAIL_PATTERN);
        }
    }

    public static void validateName(final String name) {
        if (isBlank(name)) {
            throw new IllegalArgumentException(NULL_NOT_ALLOW);
        }
        int nameLength = name.trim().length();

        if (checkNameRange(nameLength)) {
            throw new IllegalStateException(NAME_LENGTH_ERROR);
        }

        if (!isPatternMatched(name, NAME_PATTERN).find()) {
            throw new IllegalArgumentException(SPECIAL_CHARACTER_NOT_ALLOW);
        }
    }

    public static void validatePassword(final String password) {
        if (!isBlank(password)) {
            int passwordLength = password.trim().length();

            if (checkNameRange(passwordLength)) {
                throw new IllegalArgumentException(PASSWORD_LENGTH_ERROR);
            }
        }
    }

    private static boolean checkNameRange(final int nameLength) {
        return nameLength < MIN_NAME_LENGTH || nameLength > MAX_NAME_LENGTH;
    }

    private static boolean checkPasswordRange(final int passwordLength) {
        return passwordLength < MIN_PASSWORD_LENGTH || passwordLength > MAX_PASSWORD_LENGTH;
    }
}
