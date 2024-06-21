package com.example.inventory.mapper;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * DBUnitを利用してテストする場合のJavaConfigです。
 */
@TestConfiguration
public class DbUnitConfiguration {

    /**
     * IDatabaseTesterのBeanを設定します。
     * @param url 接続URL
     * @param username ユーザー名
     * @param password パスワード
     * @return IDatabaseTesterのインスタンス
     * @throws Exception
     */
    @Bean
    public IDatabaseTester iDatabaseTester(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password
    ) throws Exception {
        // URLからスキーマ名を取得
        String schema = url.replaceFirst("jdbc:\\w+://[^/]+/(\\w+)", "$1");
        // JDBCドライバクラス名を動的に取得
        String driverClassName = null;
        for (Enumeration<Driver> drivers = DriverManager.getDrivers(); drivers.hasMoreElements(); ) {
            Driver driver = drivers.nextElement();
            if (driver.acceptsURL(url)) {
                driverClassName = driver.getClass().getName();
                break;
            }
        }
        return new JdbcDatabaseTester(driverClassName, url, username, password, schema) {
            @Override
            public IDatabaseConnection getConnection() throws Exception {
                MySqlConnection mySqlConnection = new MySqlConnection(super.getConnection().getConnection(), getSchema());
                mySqlConnection.getConfig().setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
                return mySqlConnection;
            }
        };
    }
}
