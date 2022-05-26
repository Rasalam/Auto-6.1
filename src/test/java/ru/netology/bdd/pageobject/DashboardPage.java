package ru.netology.bdd.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final SelenideElement cardNumberOne = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private final SelenideElement cardNumberTwo = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private final SelenideElement cardNumberOneButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] > .button");
    private final SelenideElement cardNumberTwoButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] > .button");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getBalanceCardOne() {
        return Integer.parseInt(cardNumberOne.getText().substring(29)
                .replace(" р.\nПополнить", ""));
    }

    public int getBalanceCardTwo() {
        return Integer.parseInt(cardNumberTwo.getText().substring(29)
                .replace(" р.\nПополнить", ""));
    }

    public void pushTransferButtonCardOne() {
        cardNumberOneButton.click();

    }

    public void pushTransferButtonCardTwo() {
        cardNumberTwoButton.click();

    }

}