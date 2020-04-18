package example13.ClusteredQuartz;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.SchedulerException;
import org.quartz.utils.PoolingConnectionProvider;
import org.quartz.utils.PropertiesParser;

/**
 * 
 * @author e633229
 *
 */
class MyDataSource extends PoolingConnectionProvider {
	
    public MyDataSource(Properties config) throws SchedulerException, SQLException {
        super(config);
    }

    public DataSource dataSource() {
        return getDataSource();
    }
}
