package com.revex.docrepo.exceptions;

public class DocRepoFilesProblemException extends RuntimeException {
	public DocRepoFilesProblemException() {
	}

	public DocRepoFilesProblemException(String message) {
		super(message);
	}

	public DocRepoFilesProblemException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocRepoFilesProblemException(Throwable cause) {
		super(cause);
	}
}
