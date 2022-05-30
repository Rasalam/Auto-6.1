package ru.netology.bdd.pageobject;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.bdd.testdata.TestData;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement transferSumFiled = $("[data-test-id=amount] input");
    private final SelenideElement transferFromFiled = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement errorNotificationCardNumber = $("[data-test-id=error-notification] .notification__content");
    private final SelenideElement errorNotificationSumTransfer = $("[data-test-id=error-notification] .notification__content");


    public DashboardPage transferMoney(int transferSum, TestData.CardNumber cardNumber) {
        transferSumFiled.setValue(String.valueOf((transferSum)));
        transferFromFiled.setValue(String.valueOf(cardNumber));
        transferButton.click();
        return new DashboardPage();
    }

    public void numberCardIsInvalid() {
        errorNotificationCardNumber
                .shouldHave(exactText("Ошибка! Проверьте номер карты"))
                .shouldBe(Condition.visible);
    }

    public void cardLimitIsExceeded() {
        errorNotificationSumTransfer
                .shouldHave(exactText("Ошибка! На карте недостаточно средств для списания!"))
                .shouldBe(Condition.visible);
    }

}

