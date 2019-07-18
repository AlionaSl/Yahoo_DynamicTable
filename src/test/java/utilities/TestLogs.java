package utilities;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLogs {

	public static Logger log = Logger.getLogger(TestLogs.class.getName());

	public static void main(String[] args) {

		Date date = new Date();
//		System.out.println(date.toString().replace(":", "_").replaceAll(" ", "_"));
		System.setProperty("current.date", date.toString().replace(":", "_").replaceAll(" ", "_"));

		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
		log.info("This is the information log");
		log.error("Here the error logs will be pronted");

	}

}
