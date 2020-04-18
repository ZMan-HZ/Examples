/**
 * 
 */
package com.ssc.quartz.ppt.crontrigger;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author e586000
 *
 */
public class QuartzExample {

    private static final Logger logger = Logger.getLogger(QuartzExample.class);

    /**
     * @param args
     */
    public static void main(String[] args) {

	QuartzExample quartzExample = new QuartzExample();
	quartzExample.start();
    }

    public void start() {

	try {
	    SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();

	    
	    JobDetail job = JobBuilder
		    .newJob(CronJob.class)
		    .withIdentity("myJob", "group1")
		    .build();
	    
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))
                    .build();


	    sched.scheduleJob(job,trigger);

	    sched.start();
	    Thread.sleep(240L * 1000L);
	    sched.shutdown(true);
	} catch (Exception e) {
	    logger.error(e);
	}
    }

}
