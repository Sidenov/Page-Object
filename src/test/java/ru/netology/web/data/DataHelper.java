package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardNumber {
        String cardNumber;
    }

    public static CardNumber cardOne() {
        return new CardNumber("5559 0000 0000 0001");
    }

    public static CardNumber cardTwo() {
        return new CardNumber("5559 0000 0000 0002");
    }

    @Value
    public static class Amount {
        String amount;
    }

    public static Amount oneThousand() {
        return new Amount("1000");
    }

    public static Amount twoThousand() {
        return new Amount("2000");
    }

    public static String cardBalance(String balance) {
        return balance.substring(balance.indexOf(":") + 2, balance.indexOf("р") - 1);
    }
}
