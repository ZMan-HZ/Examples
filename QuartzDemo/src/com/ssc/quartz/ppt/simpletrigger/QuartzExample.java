/**
 * 
 */
package com.ssc.quartz.ppt.simpletrigger;

import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
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
		    .newJob(SimpleJob.class)
		    .withIdentity("myJob", "group1")
		    .usingJobData("jobSays", "Hello World!")
		    .usingJobData("myFloatValue", 3.141f)
		    .build();

	    SimpleTrigger trigger = TriggerBuilder
		    .newTrigger()
		    .withIdentity("myTrigger", "group1")
		    .startNow()
		    .withSchedule(SimpleScheduleBuilder
			    .simpleSchedule()
			    .withIntervalInSeconds(5)
			    .repeatForever())
		    .build();

	    sched.scheduleJob(job, trigger);
	    
	    sched.start();
	    
	    Thread.sleep(10L * 1000L);
	    sched.shutdown(true);
	} catch (Exception e) {
	    logger.error(e);
	}
    }

}
