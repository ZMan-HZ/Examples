package example13.ClusteredQuartz;

import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.quartz.utils.PropertiesParser;

/**
 * 
 * @author e633229
 *
 */

/* This class exposes the data store configured in quartz.properties. */
public class MyDataSourceLoader {
	private static final String DATA_SOURCE_CONFIG = "quartz_clusteredJobJDBC.properties";
	private static final String DATA_SOURCE_PREFIX = "org.quartz.dataSource.myDS";

	private static final DataSource dataSource;

	static {
		try(InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(DATA_SOURCE_CONFIG);){
			Properties quartzConfig = new Properties();
			quartzConfig.load(in);

			PropertiesParser propertiesParser = new PropertiesParser(quartzConfig);
			Properties dataSourceConfig = propertiesParser.getPropertyGroup(DATA_SOURCE_PREFIX, true);
			MyDataSource mds = new MyDataSource(dataSourceConfig);
			dataSource = mds.dataSource();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static DataSource dataSource() {
		return dataSource;
	}
}