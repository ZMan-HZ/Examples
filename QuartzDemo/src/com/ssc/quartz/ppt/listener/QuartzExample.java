/**
 * 
 */
package com.ssc.quartz.ppt.listener;

import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerListener;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.TriggerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import com.ssc.quartz.ppt.simpletrigger.SimpleJob;

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
	    
	    JobListener jobListener = new MyJobListener();
	    Matcher<JobKey> jobMatcher = KeyMatcher.keyEquals(job.getKey());
	    sched.getListenerManager().addJobListener(jobListener,jobMatcher);
	    
	    
	    Matcher<TriggerKey> triggerMatcher = KeyMatcher.keyEquals(trigger.getKey());
	    TriggerListener triggerListener = new MyTriggerListener();
	    sched.getListenerManager().addTriggerListener(triggerListener, triggerMatcher);
	    
	    SchedulerListener mySchedulerListener = new  MySchedulerListener();
	    sched.getListenerManager().addSchedulerListener(mySchedulerListener);
	    
	    
	    sched.start();
	    Thread.sleep(65L * 1000L);
	    sched.shutdown(true);
	} catch (Exception e) {
	    logger.error(e);
	}
    }

}
