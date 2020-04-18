/**
 * 
 */
package com.ssc.quartz.ppt.crontrigger;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author e586000
 *
 */
public class CronJob implements Job {
    private static final Logger logger = Logger.getLogger(CronJob.class);
    
    public void execute(JobExecutionContext context) throws JobExecutionException {
	// TODO Auto-generated method stub
	logger.info("CronJob is running ... ...");
    }

}
