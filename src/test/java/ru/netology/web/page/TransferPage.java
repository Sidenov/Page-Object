package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement replenishmentCard = $(withText("Пополнение карты"));;
    private SelenideElement amountMoney = $("[data-test-id=\"amount\"] input");
    private SelenideElement from = $(("[data-test-id=\"from\"] input"));
    private SelenideElement transferButton = $(withText("Пополнить"));
    private SelenideElement cancelButton = $(withText("Отмена"));

    public TransferPage() {
        replenishmentCard.should(Condition.visible);
    }

    public DashboardPage cancelButton() {
        cancelButton.click();
        return new DashboardPage();
    }

    public DashboardPage fromCardToCard(DataHelper.Amount amount, DataHelper.CardNumber cardNumber) {
        amountMoney.setValue(amount.getAmount());
        from.setValue(cardNumber.getCardNumber());
        transferButton.click();
        return new DashboardPage();
    }

}
