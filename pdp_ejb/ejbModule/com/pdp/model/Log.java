package com.pdp.model;

import java.io.Serializable;

public class Log implements Serializable{
	
	private LogLevel logLevel;
	private String sessionId;
	private String message;
	
	public Log() {
	}
	
	public Log(LogLevel logLevel, String sessionId, String message) {
		this.logLevel = logLevel;
		this.sessionId = sessionId;
		this.message = message;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
