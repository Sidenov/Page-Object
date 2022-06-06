package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.*;

public class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("успешный перевод 1000 рублей с первой карты на вторую")
    void shouldSuccessTransferFromCardOneToCardTwo() {
        var dashboardPage = new DashboardPage();
        int startBalanceOfCardOne = Integer.parseInt(DataHelper.cardBalance(dashboardPage.getBalanceOfFirstCard().getText()));
        int startBalanceOfCardTwo = Integer.parseInt(DataHelper.cardBalance(dashboardPage.getBalanceOfSecondCard().getText()));
        var transferPage = dashboardPage.replenishTheFirstCard();
        var amountToTransfer = DataHelper.oneThousand();
        transferPage.fromCardToCard(amountToTransfer, DataHelper.cardTwo());

        int expectedBalanceOfCardOne = startBalanceOfCardOne + Integer.parseInt(DataHelper.oneThousand().getAmount());
        int expectedBalanceOfCardTwo = startBalanceOfCardTwo - Integer.parseInt(DataHelper.oneThousand().getAmount());

        int actualBalanceOfCardOne = Integer.parseInt(DataHelper.cardBalance(dashboardPage.getBalanceOfFirstCard().getText()));
        int actualBalanceOfCardTwo = Integer.parseInt(DataHelper.cardBalance(dashboardPage.getBalanceOfSecondCard().getText()));
    }

    @Test
    @DisplayName("успешный перевод 2000 рублей с первой карты на вторую")
    void shouldSuccessTransferFromTwoToCardOne() {
        var dashboardPage = new DashboardPage();
        int startBalanceOfCardOne = Integer.parseInt(DataHelper.cardBalance(dashboardPage.getBalanceOfFirstCard().getText()));
        int startBalanceOfCardTwo = Integer.parseInt(DataHelper.cardBalance(dashboardPage.getBalanceOfSecondCard().getText()));
        var transferPage = dashboardPage.replenishTheSecondCard();
        var amountToTransfer = DataHelper.twoThousand();
        transferPage.fromCardToCard(amountToTransfer, DataHelper.cardOne());

        int expectedBalanceOfCardOne = startBalanceOfCardOne - Integer.parseInt(DataHelper.twoThousand().getAmount());
        int expectedBalanceOfCardTwo = startBalanceOfCardTwo + Integer.parseInt(DataHelper.twoThousand().getAmount());

        int actualBalanceOfCardOne = Integer.parseInt(DataHelper.cardBalance(dashboardPage.getBalanceOfFirstCard().getText()));
        int actualBalanceOfCardTwo = Integer.parseInt(DataHelper.cardBalance(dashboardPage.getBalanceOfSecondCard().getText()));
    }




}

