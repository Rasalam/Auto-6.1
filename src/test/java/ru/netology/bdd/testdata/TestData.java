/* ****************************************
TEST DATA
login               = "vasya"
password            = "qwerty123"
verification code   = "12345"
first card number   = "5559 0000 0000 0001"
first card balance  = "10_000"
second card number  = "5559 0000 0000 0002"
second card balance = "10_000"
******************************************* */

package ru.netology.bdd.testdata;

import lombok.Value;

public class TestData {
    private TestData() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardNumber {
        String cardNumber;

    }

    public static CardNumber getCardONumberOne() {
        return new CardNumber("5559000000000001");

    }

    public static CardNumber getCardNumberTwo() {
        return new CardNumber("5559000000000002");
    }


    public static CardNumber getInvalidCardNumber() {
        return new CardNumber("5559000000000003");
    }
}

