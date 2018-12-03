/**
 * Date:     2018/11/2723:10
 * AUTHOR:   Administrator
 */
package com.zhou.distributedtransaction.test_001_jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 2018/11/27  23:10
 * created by zhoumb
 */
public class LocalJDBCTranaApplication {
    private static final Logger logger = LoggerFactory.getLogger(LocalJDBCTranaApplication.class);

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(Boolean.FALSE);

            String addSql = "UPDATE T_USER SET amount = amount + 100 where username = ?";
            PreparedStatement addStatement = connection.prepareStatement(addSql);
            String subSql = "UPDATE T_USER SET amount = amount - 100 where username = ?";
            PreparedStatement subStatement = connection.prepareStatement(subSql);

            addStatement.setString(1, "SuperMan");
            subStatement.setString(1, "BadMan");

            addStatement.executeUpdate();
//            int i = 1 / 0;如果抛出异常  则自动会将未完场的事务回滚
            subStatement.executeUpdate();

            connection.commit();

            addStatement.close();
            subStatement.close();
            connection.close();

        } catch (SQLException e) {
            logger.error("获取mysql链接异常", e);
        }
    }

    private static Connection getConnection() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/dist_tran_course?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
        String username = "root";
        String password = "12345";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("class.forName(Driver)异常", e);
        }
        return DriverManager.getConnection(url, username, password);
    }
}
