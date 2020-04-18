package example13.ClusteredQuartz;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SchedulerException;
import org.quartz.utils.DBConnectionManager;

/**
 * 
 * @author e633229
 *
 */

@PersistJobDataAfterExecution
@DisallowConcurrentExecution  
public class ClusteredJob2 implements Job {

    private static final Logger logger = Logger.getLogger(ClusteredJob2.class);
    
//    public static final String EXECUTION_COUNT = "count";
    String count = null;
//并不需要get方法
//    public String getCount() {
//		return this.count;
//	}
	public void setCount(String count) {
		this.count = count;
	}
	public ClusteredJob2(){
    }
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	
    	JobKey jobKey = context.getJobDetail().getKey();
	    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
//	    JobDataMap dataMap = context.getMergedJobDataMap();    此方法参数count不会持久
//	    int count = data.getInt(EXECUTION_COUNT);
	    int seq  ;
	    String hostName = null; 
		try {
			hostName = context.getScheduler().getSchedulerInstanceId().substring(0, 12);
	    	logger.info("jobKey:  " + jobKey +" running at : { "+ hostName + " } with sequence NO. : " + count);
			nrm2oub(Integer.parseInt(count),hostName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		seq = Integer.parseInt(count);
		seq++ ;
		dataMap.put("count", String.valueOf(seq)); //不能直接传seq，数据类型Int不会put到JobDataMap中，原因待深究
	    
	    logger.info("jobKey:  " + jobKey +" running at : { "+ hostName + " } Completed !!");
	
    }
    
    private void nrm2oub(Integer seq,String hostName) throws SQLException {
		
    	String sp = "insert into CLOTRGS.MAWANG_TEST_QRTZ select ? as LOG_TXT, ? as HOSTNAME,CURRENT_DATE as UPDATED_AT from dual";
    	
    	/* 2 ways to Getting Connection 
    	*  Connection conn = MyDataSourceLoader.dataSource().getConnection();
		*  OR using below
        *  Connection conn = DBConnectionManager.getInstance().getConnection("myDS");
        */
		try(Connection conn = DBConnectionManager.getInstance().getConnection("myDS");
				CallableStatement cstmt = conn.prepareCall(sp);) {
			cstmt.setInt(1, seq);
			cstmt.setString(2, hostName);
			cstmt.execute();
		} catch(SQLException e) {
			logger.error("nrm2oub error", e);
			throw new SQLException(e);
		}  
		
	}
    
    
    
}
