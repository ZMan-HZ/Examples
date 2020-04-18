/**
 * 
 * 
 */
package com.ssc.quartz.ppt.listener;
import org.apache.log4j.Logger;
import org.quartz.Trigger;
import org.quartz.listeners.SchedulerListenerSupport;

/**
 * @author e586000
 *
 */
public class MySchedulerListener extends SchedulerListenerSupport {

    final static Logger logger = Logger.getLogger(MySchedulerListener.class);
    
    @Override
    public void schedulerStarted() {
	logger.info("scheduler Started");
    }

    @Override
    public void schedulerShutdown() {
	logger.info("scheduler Shutdown");
    }

    @Override
    public void jobScheduled(Trigger trigger) {
	logger.info("Job scheduled ");
    }

}