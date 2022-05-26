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
        String[] cardNumber = new String[3];

        private CardNumber() {
            cardNumber[0] = "5559000000000001";
            cardNumber[1] = "5559000000000002";
            cardNumber[2] = "5559000000000003";
        }

    }

    public static String getCardNumber(int index) {
        return new CardNumber().cardNumber[index];

    }

    @Value
    public static class SumOfTransfer {
        int[] sum = new int[2];

        private  SumOfTransfer () {
            sum[0]= 1_000;
            sum[1]= 20_000;
        }
    }

    public static int getSumOfTransfer(int index) {
        return new SumOfTransfer().sum[index];
    }
}

