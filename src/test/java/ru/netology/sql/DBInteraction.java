package ru.netology.sql;

import com.github.javafaker.Faker;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.UserDataHandler;
import ru.netology.sql.data.DBHelper;
import ru.netology.sql.data.DataHelper;
import ru.netology.sql.page.LoginPage;
import ru.netology.sql.page.VerificationPage;

import java.nio.file.attribute.UserPrincipal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

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



