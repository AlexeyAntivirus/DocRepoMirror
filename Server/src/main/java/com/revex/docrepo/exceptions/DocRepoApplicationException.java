package com.revex.docrepo.exceptions;

public class DocRepoApplicationException extends RuntimeException {
	public DocRepoApplicationException() {
	}

	public DocRepoApplicationException(String message) {
		super(message);
	}

	public DocRepoApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocRepoApplicationException(Throwable cause) {
		super(cause);
	}
}
