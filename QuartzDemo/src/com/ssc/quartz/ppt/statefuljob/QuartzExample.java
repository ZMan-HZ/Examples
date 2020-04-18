/**
 * 
 */
package com.ssc.quartz.ppt.statefuljob;

import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
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
		    .newJob(StatefulJob.class)
		    .withIdentity("statefulJob", "group1")
		    .build();

	    Trigger trigger = TriggerBuilder
		    .newTrigger()
		    .withIdentity("myTrigger", "group1")
		    .startNow()
		    .withSchedule(SimpleScheduleBuilder
			    .simpleSchedule()
			    .withIntervalInSeconds(3)
			    .repeatForever())
		    .build();

	    sched.scheduleJob(job, trigger);

	    sched.start();
	    Thread.sleep(15L * 1000L);
	    sched.shutdown(true);
	} catch (Exception e) {
	    logger.error(e);
	}
    }

}
