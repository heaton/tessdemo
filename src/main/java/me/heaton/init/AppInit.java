package me.heaton.init;

import javax.imageio.ImageIO;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInit implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
    	ImageIO.scanForPlugins();
    }

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
