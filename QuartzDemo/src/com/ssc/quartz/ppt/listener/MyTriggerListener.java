/**
 * 
 */
package com.ssc.quartz.ppt.listener;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

/**
 * @author e586000
 *
 */
public class MyTriggerListener implements TriggerListener {
    
    final static Logger logger = Logger.getLogger(MyTriggerListener.class);

    public String getName() {
	return "My trigger listener";
    }

    public void triggerFired(Trigger trigger, JobExecutionContext context) {
	logger.info("Trigger Fired");
    }

    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
	return false;
    }

    public void triggerMisfired(Trigger trigger) {
	logger.info("Trigger Misfired");

    }

    public void triggerComplete(Trigger trigger, JobExecutionContext context,
	    CompletedExecutionInstruction triggerInstructionCode) {
	logger.info("Trigger Completed");

    }

}
