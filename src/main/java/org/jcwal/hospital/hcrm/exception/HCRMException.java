package org.jcwal.hospital.hcrm.exception;

import org.macula.exception.MaculaException;

public class HCRMException extends MaculaException {
	private static final long serialVersionUID = 1L;

	public HCRMException(String message) {
		super(message);
	}

	@Override
	public String getParentCode() {
		return "HCRM";
	}
}
