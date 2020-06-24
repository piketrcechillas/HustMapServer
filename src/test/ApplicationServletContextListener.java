package test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import processor.Processing;

public class ApplicationServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
    	try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Processing.loadData();
        System.out.println("Application started");  
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }
}