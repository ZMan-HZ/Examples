/**
 * 
 */
package com.ssc.quartz.ppt.simpletrigger;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author e586000
 *
 */
public class SimpleJob implements Job {
    private static final Logger logger = Logger.getLogger(SimpleJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {

	JobDataMap dataMap = context.getJobDetail().getJobDataMap();
	
	String jobSays = dataMap.getString("jobSays");
	float myFloatValue = dataMap.getFloat("myFloatValue");
	
	logger.info("Job says: " + jobSays + ", and val is: " + myFloatValue);

    }

}
