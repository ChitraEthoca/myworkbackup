package com.automation.framework.core;

public enum Status {

	PASS("PASS"), FAIL("FAIL"), DONE("DONE"), INFO("INFO"), ERROR("ERROR"), WARNING("WARNING"), DBPASS(
			"DBPASS"), DBFAIL("DBFAIL");

	private String logType;

	Status(String logType) {
		this.logType = logType;
	}

	public String getStatus() {
		return logType;
	}

	@Override
	public String toString() {
		return logType;
	}

}
