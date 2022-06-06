package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement yourCards = $(withText("Ваши карты"));
    private SelenideElement firstButtonDepositeMoney = $$("[data-test-id=\"action-deposit\"]").first();
    private SelenideElement secondButtonDepositeMoney = $$("[data-test-id=\"action-deposit\"]").last();
    private SelenideElement balanceOfFirstCard = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement balanceOfSecondCard = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    public DashboardPage() {
        yourCards.should(Condition.visible);
    }

    public SelenideElement getBalanceOfFirstCard() {
        return balanceOfFirstCard;
    }

    public SelenideElement getBalanceOfSecondCard() {
        return balanceOfSecondCard;
    }

    public TransferPage replenishTheFirstCard() {
        firstButtonDepositeMoney.click();
        return new TransferPage();
    }

    public TransferPage replenishTheSecondCard() {
        secondButtonDepositeMoney.click();
        return new TransferPage();
    }
}
