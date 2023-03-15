package ru.netology.sql;

import com.github.javafaker.Faker;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.UserDataHandler;

import java.nio.file.attribute.UserPrincipal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBInteraction {
    @BeforeEach
    void setUp() throws SQLException {
        Faker faker = new Faker();
        var runner = new QueryRunner();
        String user = "app";
        String pass = "9mREsvXDs9Gk89Ef";
        String dataSQL = "INSERT INTO users(login, password) VALUES (?, ?);";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysqldb://localhost:3306/app", user, pass);
        )
        {
            // обычная вставка
            runner.update(conn, dataSQL, faker.name().username(), pass);
            runner.update(conn, dataSQL, faker.name().username(), pass);
        }
    }

    @Test
    void stubTest() throws SQLException {
        String countSQL = "SELECT COUNT(*) FROM users;";
        String usersSQL = "SELECT * FROM users;";
        String user = "app";
        String pass = "9mREsvXDs9Gk89Ef";
        QueryRunner runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysqldb://localhost:3306/app", user, pass);
        )
        {
            MysqlxDatatypes.Object count = runner.query(conn, countSQL, new ScalarHandler<>());
            System.out.println(count);
            UserDataHandler first = runner.query(conn, usersSQL, new BeanHandler<>(UserDataHandler.class));
            System.out.println(first);
            List<UserDataHandler> all = runner.query(conn, usersSQL, new BeanListHandler<>(UserDataHandler.class));
            System.out.println(all);
        }
    }
}



