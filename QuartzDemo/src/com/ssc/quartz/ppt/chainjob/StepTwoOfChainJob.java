/**
 * 
 */
package com.ssc.quartz.ppt.chainjob;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author e586000
 *
 */
public class StepTwoOfChainJob implements Job {

    final static Logger logger = Logger.getLogger(StepTwoOfChainJob.class);
    
    public void execute(JobExecutionContext context) throws JobExecutionException {
	logger.info("i'm executing step two of chain job");

    }

}
