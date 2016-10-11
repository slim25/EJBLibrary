package com.pdp.business;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import com.pdp.model.Log;
import com.pdp.model.LogLevel;

@Remote
public interface LoggingSystemBeanRemote {
	
	void log(LogLevel level, String sessionId, String message);
	
	Collection<Log> getAllLogs() throws IOException;
}
