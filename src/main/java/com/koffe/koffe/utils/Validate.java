package com.koffe.koffe.utils;

public class Validate {
    private static final String NUMBER = "^[0-9]+$";
    private static final String FULL_NAME_PATTERN = "^([A-za-z]+\\s)+[a-zA-z]+$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{6,}$";
    private static final String PHONE_NUMBER_PATTERN = "^[0-9]{10}$";
    private static final String EMAIL_PATTERN = "[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    private static final String TEXT_AREA_PATTERN = "^([A-za-z0-9.!);]+\\s)+[a-zA-z.!);?]+$";

    public static boolean isMatchesFullName(String fullName) {
        return fullName.matches(FULL_NAME_PATTERN);
    }

    public static boolean isMatchesPassword(String password) {
        return password.matches(PASSWORD_PATTERN);
    }

    public static boolean isMatchesPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(PHONE_NUMBER_PATTERN);
    }

    public static boolean isMatchesEmail(String email) {
        return email.matches(EMAIL_PATTERN);
    }

    public static boolean isNumber(String number) {
        return number.matches(NUMBER);
    }

    public static boolean isMatchesTextArea(String textArea) {
        return textArea.matches(TEXT_AREA_PATTERN);
    }

}
