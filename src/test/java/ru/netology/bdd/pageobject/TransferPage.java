package ru.netology.bdd.pageobject;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.bdd.testdata.TestData.*;

public class TransferPage {

    private static final SelenideElement transferSum = $("[data-test-id=amount] input");
    private static final SelenideElement transferFrom = $("[data-test-id=from] input");
    private static final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private static final SelenideElement errorNotificationCardNumber = $("[data-test-id=error-notification] .notification__content");
    private static final SelenideElement errorNotificationSumTransfer = $("[data-test-id=error-notification] .notification__content");

    private static final SelenideElement cancelButton = $(withText("Отмена"));

    public static DashboardPage transferMoney(int sumIndex, int cardIndex) {
        transferSum.setValue(String.valueOf(getSumOfTransfer(sumIndex)));
        transferFrom.setValue(getCardNumber(cardIndex));
        // Selenide.sleep(3000);
        transferButton.click();
        return new DashboardPage();
    }

    public static void clearTransferPage() {
        transferSum.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        transferFrom.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);

    }

    public static void numberCardIsInvalid() {
       errorNotificationCardNumber
                .shouldHave(exactText("Ошибка! Проверьте номер карты"))
                .shouldBe(Condition.visible);
    }

    public static void cardLimitIsExceeded() {
        errorNotificationSumTransfer
                .shouldHave(exactText("Ошибка! На карте недостаточно средств для списания!"))
                .shouldBe(Condition.visible);
    }

    public static void pushCancelButton() {
        cancelButton.click();
    }
}

