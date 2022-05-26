package ru.netology.bdd.tests;

import org.junit.jupiter.api.*;
import ru.netology.bdd.pageobject.LoginPage;
import ru.netology.bdd.pageobject.TransferPage;
import ru.netology.bdd.testdata.TestData;
import ru.netology.bdd.pageobject.DashboardPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        int cardIndex = 1;                  // [0]5559000000000001, [1]5559000000000002, [2]5559000000000003 (invalid number)
        int sumIndex = 0;                   // [0]1_000, [1]20_000 (invalid sum)
        var DashboardPage = new DashboardPage();
        var cardOneBalanceBefore = DashboardPage.getBalanceCardOne();
        var cardTwoBalanceBefore = DashboardPage.getBalanceCardTwo();
        DashboardPage.pushTransferButtonCardOne();
        TransferPage.transferMoney(sumIndex, cardIndex);
        assertEquals(cardOneBalanceBefore + TestData.getSumOfTransfer(sumIndex), DashboardPage.getBalanceCardOne());
        assertEquals(cardTwoBalanceBefore - TestData.getSumOfTransfer(sumIndex), DashboardPage.getBalanceCardTwo());
    }

    @Test
    void shouldTransferMoneySuccessFromCardOneToCardTwo() {
        int cardIndex = 0;                  // [0]5559000000000001, [1]5559000000000002, [2]5559000000000003 (invalid number)
        int sumIndex = 0;                   // [0]1_000, [1]20_000 (invalid sum)
        var DashboardPage = new DashboardPage();
        var cardOneBalanceBefore = DashboardPage.getBalanceCardOne();
        var cardTwoBalanceBefore = DashboardPage.getBalanceCardTwo();
        DashboardPage.pushTransferButtonCardTwo();
        TransferPage.transferMoney(sumIndex, cardIndex);
        assertEquals(cardOneBalanceBefore - TestData.getSumOfTransfer(sumIndex), DashboardPage.getBalanceCardOne());
        assertEquals(cardTwoBalanceBefore + TestData.getSumOfTransfer(sumIndex), DashboardPage.getBalanceCardTwo());

    }

    @Test
    void shouldShowMessageIfCardNumberIsInvalid() {
        int cardIndex = 2;                  // [0]5559000000000001, [1]5559000000000002, [2]5559000000000003 (invalid number)
        int sumIndex = 0;                   // [0]1_000, [1]20_000 (invalid sum)
        var DashboardPage = new DashboardPage();
        DashboardPage.pushTransferButtonCardOne();
        TransferPage.transferMoney(sumIndex, cardIndex);
        TransferPage.numberCardIsInvalid();

    }

    @Test
    void shouldShowMessageIfCardLimitExceeded() {
        int cardIndex = 0;                  // [0]5559000000000001, [1]5559000000000002, [2]5559000000000003 (invalid number)
        int sumIndex = 1;                   // [0]1_000, [1]20_000 (invalid sum)
        var DashboardPage = new DashboardPage();
        DashboardPage.pushTransferButtonCardTwo();
        TransferPage.transferMoney(sumIndex, cardIndex);
        TransferPage.cardLimitIsExceeded();

    }
}







