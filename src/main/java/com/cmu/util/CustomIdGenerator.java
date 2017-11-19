package com.cmu.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIdGenerator implements IdentifierGenerator {
	@Override
	public Serializable generate(SessionImplementor session, Object object)
            throws HibernateException {

        String prefix = "AUDIO_";
        Connection connection = session.connection();
        PreparedStatement ps=null;
        try {

        	 ps= connection
        			 .prepareStatement("SELECT max(id) as value from QUESTION_COUNT");

             ResultSet rs = ps.executeQuery();
             if(rs==null){
            	 int id = 0;
                 String code = prefix + new Integer(id).toString();
                 System.out.println("Generated Stock Code: " + code);
                 return code;
             }
             if (rs.next()) {
                 int id = rs.getInt("value");
                 String code = prefix + new Integer(id).toString();
                 System.out.println("Generated Stock Code: " + code);
                 return code;
             }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Throwable e) {
                    // log error, or rethrow exception
                }
            }
        }
        return null;
    }

    
}
