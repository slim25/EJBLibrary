package com.pdp.businesslogic;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Singleton;

import com.pdp.business.LoggingSystemBeanRemote;
import com.pdp.model.Log;
import com.pdp.model.LogLevel;

@Singleton
public class LoggingSystemBean implements LoggingSystemBeanRemote{
	
	private List<Log> logs = new ArrayList<>();
	private int counter = 0;
	
	public void log(LogLevel level, String sessionId, String message) {
		logs.add( new Log(level,sessionId,message));
	}

	public Collection<Log> getAllLogs() throws IOException {
		return logs;
		
	}
	

}
