package com.revex.docrepo.exceptions;

public class DocRepoFileNotDeletedException extends DocRepoApplicationException {
    public DocRepoFileNotDeletedException() {
    }

    public DocRepoFileNotDeletedException(String message) {
        super(message);
    }

    public DocRepoFileNotDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocRepoFileNotDeletedException(Throwable cause) {
        super(cause);
    }
}
