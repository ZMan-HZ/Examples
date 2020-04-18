package example13.ClusteredQuartz;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author e633229
 *
 */

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ClusteredjobII implements Job {

	private static Logger logger = LoggerFactory.getLogger(ClusteredjobII.class);

	// parameter names specific to this job
	public static final String EXECUTION_COUNT = "count";

	public ClusteredjobII() {
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		// This job simply prints out its job name and the
		// date and time that it is running
		JobKey jobKey = context.getJobDetail().getKey();

		// Grab and print passed parameters
		JobDataMap data = context.getJobDetail().getJobDataMap();
		int count = data.getInt(EXECUTION_COUNT);
		logger.info("jobKey===  " + jobKey + "   count:::::" + count);

		try {
			nrm2oub(count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// increment the count and store it back into the
		// job map so that job state can be properly maintained
		count++;
		data.put(EXECUTION_COUNT, count);

	}

	private void nrm2oub(Integer seq) throws SQLException {

		String sp = "insert into CLOTRGS.MAWANG_TEST_QRTZ select seq as LOG_TXT, PRTFO_CD as HOSTNAME,LAST_UPDATE_DATE as UPDATED_AT from dmhproc.JTO_MT30X_NRM dataa  where seq = ?";

		try (Connection conn = MyDataSourceLoader.dataSource().getConnection();
				CallableStatement cstmt = conn.prepareCall(sp);) {
			cstmt.setInt(1, seq);
			cstmt.execute();
		} catch (SQLException e) {
			logger.error("nrm2oub error", e);
			throw new SQLException(e);
		}

	}

}