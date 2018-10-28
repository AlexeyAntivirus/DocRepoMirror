package com.revex.docrepo.exceptions;

public class DocRepoFileNotUploadedException extends DocRepoApplicationException {
	public DocRepoFileNotUploadedException() {
	}

	public DocRepoFileNotUploadedException(String message) {
		super(message);
	}

	public DocRepoFileNotUploadedException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocRepoFileNotUploadedException(Throwable cause) {
		super(cause);
	}
}
