/**
 * 
 */
package com.ssc.quartz.ppt.statefuljob;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

/**
 * @author e586000
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class StatefulJob implements Job {
    
    private static final Logger logger = Logger.getLogger(StatefulJob.class);
    
    public void execute(JobExecutionContext context) throws JobExecutionException {
	logger.info("I'm statefuljob ["+this.hashCode()+"]");
	
	JobDataMap dataMap = context.getJobDetail().getJobDataMap();
	String JobData = dataMap.getString("JobData");
	
	if(JobData==null||"".equalsIgnoreCase(JobData)){
	    logger.info("no JobData found");
	    dataMap.put("JobData","JobData");
	}else{
	    logger.info("JobData found : " +JobData);
	}
	
	
	try {
	    Thread.sleep(5000);
	} catch (InterruptedException e) {
	    logger.error(e);
	}
    }

}
