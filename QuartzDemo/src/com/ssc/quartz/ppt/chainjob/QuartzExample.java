/**
 * 
 */
package com.ssc.quartz.ppt.chainjob;

import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.quartz.listeners.JobChainingJobListener;

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



	    JobDetail jobStepOne = JobBuilder
		    .newJob(StepOneOfChainJob.class)
		    .withIdentity("chainJobStep1", "group1")
		    .build();

	    SimpleTrigger trigger = TriggerBuilder
		    .newTrigger()
		    .withIdentity("chainTrigger", "group1")
		    .startNow()
		    .withSchedule(SimpleScheduleBuilder
			    .simpleSchedule()
			    .withIntervalInSeconds(5)
			    .repeatForever())
		    .build();

	    sched.scheduleJob(jobStepOne, trigger);
	    
	    JobDetail jobStepTwo = JobBuilder
		    .newJob(StepTwoOfChainJob.class)
		    .withIdentity("chainJobStep2", "group1")
		    .storeDurably(true)
		    .build();
	     
	    sched.addJob(jobStepTwo, true);
	    
	    JobChainingJobListener chain = new JobChainingJobListener("chainJob");
            chain.addJobChainLink( jobStepOne.getKey(), jobStepTwo.getKey()); 
            
            Matcher<JobKey> jobMatcher = KeyMatcher.keyEquals(jobStepOne.getKey());
            sched.getListenerManager().addJobListener(chain, jobMatcher);
	    
	    sched.start();
	    Thread.sleep(65L * 1000L);
	    sched.shutdown(true);
	} catch (Exception e) {
	    logger.error(e);
	}
    }

}
