package example13.ClusteredQuartz;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class ClusteredJobExample {

    private static final Logger logger = Logger.getLogger(ClusteredJobExample.class);

    /**
     * @param args
     */
    public static void main(String[] args) {

	ClusteredJobExample clusteredJobExample = new ClusteredJobExample();
	clusteredJobExample.start();
    }

    public void start() {

	try {
	    SchedulerFactory sf = new StdSchedulerFactory("quartz_clusteredJobJDBC.properties");
//	    SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();
	    
	    sched.start();
	    
	    Thread.sleep(1*60L * 1000L);
	    
	    sched.shutdown(true);
	} catch (Exception e) {
	    logger.error(e);
	}
    }

}
