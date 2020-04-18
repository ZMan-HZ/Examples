/**
 * 
 */
package com.ssc.quartz.ppt.listener;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @author e586000
 *
 */
public class MyJobListener implements JobListener {

    final static Logger logger = Logger.getLogger(MyJobListener.class);
    
    public String getName() {
	return "my job listener";
    }

    public void jobToBeExecuted(JobExecutionContext context) {
	logger.info("job To Be Executed");
    }

    public void jobExecutionVetoed(JobExecutionContext context) {
	logger.info("job To Be ExecutionVetoed");
    }

    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
	logger.info("job Was Executed");
    }

 
}