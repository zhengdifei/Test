package schedule;

import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
  
  private Timer timer = null;

  public void contextInitialized(ServletContextEvent event) {
    timer = new Timer(true);

    timer.schedule(new MyTask(), 0, 3000);
  }

  public void contextDestroyed(ServletContextEvent event) {
    timer.cancel();
  }
  
}