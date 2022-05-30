package ru.netology.bdd.tests;

import org.junit.jupiter.api.*;
import ru.netology.bdd.pageobject.LoginPage;
import ru.netology.bdd.pageobject.TransferPage;
import ru.netology.bdd.testdata.TestData;
import ru.netology.bdd.pageobject.DashboardPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @BeforeEach
    public void preparePage() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = TestData.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = TestData.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

    }

    @Test
    void shouldTransferMoneySuccessFromCardTwoToCardOne() {
        int transferSum = 1000;
        var dashboardPage = new DashboardPage();
        var cardOneBalanceBefore = dashboardPage.getBalanceCardOne();
        var cardTwoBalanceBefore = dashboardPage.getBalanceCardTwo();
        dashboardPage.pushTransferButtonCardOne();
        var transferPage = new TransferPage();
        transferPage.transferMoney(transferSum, TestData.getCardNumberTwo());
        assertEquals(cardOneBalanceBefore + transferSum, dashboardPage.getBalanceCardOne());
        assertEquals(cardTwoBalanceBefore - transferSum, dashboardPage.getBalanceCardTwo());
    }

    @Test
    void shouldTransferMoneySuccessFromCardOneToCardTwo() {
        int transferSum = 1000;
        var dashboardPage = new DashboardPage();
        var cardOneBalanceBefore = dashboardPage.getBalanceCardOne();
        var cardTwoBalanceBefore = dashboardPage.getBalanceCardTwo();
        dashboardPage.pushTransferButtonCardTwo();
        var transferPage = new TransferPage();
        transferPage.transferMoney(transferSum, TestData.getCardONumberOne());
        assertEquals(cardOneBalanceBefore - transferSum, dashboardPage.getBalanceCardOne());
        assertEquals(cardTwoBalanceBefore + transferSum, dashboardPage.getBalanceCardTwo());

    }

    @Test
    void shouldShowMessageIfCardNumberIsInvalid() {
        int transferSum = 1000;
        var dashboardPage = new DashboardPage();
        dashboardPage.pushTransferButtonCardOne();
        var transferPage = new TransferPage();
        transferPage.transferMoney(transferSum, TestData.getInvalidCardNumber());
        transferPage.numberCardIsInvalid();

    }

    @Test
    void shouldShowMessageIfCardLimitExceeded() {
        var dashboardPage = new DashboardPage();
        int transferSum = dashboardPage.getBalanceCardOne() + 1;
        dashboardPage.pushTransferButtonCardTwo();
        var transferPage = new TransferPage();
        transferPage.transferMoney(transferSum, TestData.getCardONumberOne());
        transferPage.cardLimitIsExceeded();

    }
}







