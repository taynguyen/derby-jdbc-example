/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yooup.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author TAY
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        // Test
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "taynguyen");
        connectionProps.put("password", "taynguyen");
        connectionProps.put("driver_class", "org.apache.derby.jdbc.ClientDriver");
        
        try {
            conn = DriverManager.getConnection(
                    "jdbc:derby:" +
                            "D:\\Projects\\POS\\derby-example\\database\\taynguyen-example" +
                            ";create=true",
                    connectionProps);
        } catch (SQLException ex) {
            Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
