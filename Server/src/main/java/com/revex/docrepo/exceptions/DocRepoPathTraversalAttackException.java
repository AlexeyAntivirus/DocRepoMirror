package com.revex.docrepo.exceptions;

public class DocRepoPathTraversalAttackException extends DocRepoApplicationException {
    public DocRepoPathTraversalAttackException() {
    }

    public DocRepoPathTraversalAttackException(String message) {
        super(message);
    }

    public DocRepoPathTraversalAttackException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocRepoPathTraversalAttackException(Throwable cause) {
        super(cause);
    }
}
