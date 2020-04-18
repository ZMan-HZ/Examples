/**
 * 
 */
package com.ssc.quartz.ppt.configuration;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import oracle.jdbc.OracleTypes;

/**
 * @author e586000
 *
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class ClusteredJob implements Job {

    private static final Logger logger = Logger.getLogger(ClusteredJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
	try {
	    logger.info("ClusteredJob starts running at " + context.getScheduler().getSchedulerInstanceId());
	    logger.info("ClusteredJob gets value of someKey :"
		    + context.getJobDetail().getJobDataMap().getString("someKey"));
	    insertLog();
	    logger.info("ClusteredJob ends running");
	} catch (Exception e) {
	    logger.error(e);
	}

    }

    public void insertLog() throws SQLException {
	PreparedStatement cstmt = null;
	Connection con=null;
	String sql = "insert into mawang_test_qrtz values(?,?,current_date)";
	try {
	    con=getConnection();
	    cstmt = con.prepareStatement(sql);
	    cstmt.setString(1, "ZR");
	    cstmt.setString(2, "2450");

	    cstmt.execute();

	} catch (Exception e) {
	    throw new SQLException(e);
	} finally {
	    if (cstmt != null)
		cstmt.close();
	    if (con != null)
		con.close();
	}
    }

    public Connection getConnection() throws Exception {
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@hz4dw3223:1521:O01DMHDEV";
	String user = "dmh";
	String password = "dmh";
	Connection conn = null;
	Class.forName(driver);
	conn = DriverManager.getConnection(url, user, password);
	return conn;
    }

}
