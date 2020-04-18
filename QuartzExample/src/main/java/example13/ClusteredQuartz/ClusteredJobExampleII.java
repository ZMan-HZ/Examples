package example13.ClusteredQuartz;

import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

/**
 * This Example will demonstrate how job parameters can be passed into jobs and how state can be maintained
 * 
 * @author Bill Kratzer
 */
public class ClusteredJobExampleII {

  public void run() throws Exception {
    Logger log = LoggerFactory.getLogger(ClusteredJobExampleII.class);

    log.info("------- Initializing -------------------");

    // First we must get a reference to a scheduler
    SchedulerFactory sf = new StdSchedulerFactory();
    Scheduler sched = sf.getScheduler();

    // get a "nice round" time a few seconds in the future....
    Date startTime = nextGivenSecondDate(null, 10);

    // job1 will only run 5 times (at start time, plus 4 repeats), every 10 seconds
    JobDetail job1 = newJob(ClusteredjobII.class).withIdentity("job1", "group1").build();

    SimpleTrigger trigger1 = newTrigger().withIdentity("trigger1", "group1").startAt(startTime).withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4)).build();

    // pass initialization parameters into the job
    job1.getJobDataMap().put(ClusteredjobII.EXECUTION_COUNT, 0);

    // schedule the job to run
    Date scheduleTime1 = sched.scheduleJob(job1, trigger1);
    log.info(job1.getKey() + " will run at: " + scheduleTime1 + " and repeat: " + trigger1.getRepeatCount() + " times, every " + trigger1.getRepeatInterval() / 1000 + " seconds");

    // All of the jobs have been added to the scheduler, but none of the jobs
    // will run until the scheduler has been started
    sched.start();

    try {
      // wait five minutes to show jobs
      Thread.sleep(60L * 1000L);
      // executing...
    } catch (Exception e) {
      //
    }

    sched.shutdown(true);

    log.info("------- Shutdown Complete -----------------");

//    SchedulerMetaData metaData = sched.getMetaData();
//    log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");

  }

  public static void main(String[] args) throws Exception {

    ClusteredJobExampleII example = new ClusteredJobExampleII();
    example.run();
  }

}
