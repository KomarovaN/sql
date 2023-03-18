package ru.netology.sql;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.sql.data.DBHelper;
import ru.netology.sql.data.DataHelper;
import ru.netology.sql.page.LoginPage;
import ru.netology.sql.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.sql.data.DBHelper.cleanDB;

public class DBInteraction {

    @AfterAll
    static void setDown() { cleanDB(); }

    @Test
    void shouldSuccessfulLogin() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfoWithTestData();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPage();
        DataHelper.VerificationCode verificationCode = DBHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }
}



