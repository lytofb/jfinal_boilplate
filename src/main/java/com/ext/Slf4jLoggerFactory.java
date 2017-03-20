package main.java.com.ext;

import com.jfinal.log.ILogFactory;
import com.jfinal.log.Log;

public class Slf4jLoggerFactory implements ILogFactory {

	public Log getLog(Class<?> clazz) {
		return new Slf4jLogger(clazz);
	}

	public Log getLog(String name) {
		return new Slf4jLogger(name);
	}
}
