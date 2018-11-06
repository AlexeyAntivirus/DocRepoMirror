package com.revex.docrepo.advices;

import com.revex.docrepo.controllers.QualificationWorkController;
import com.revex.docrepo.exceptions.DocRepoApplicationException;
import com.revex.docrepo.exceptions.DocRepoFileNotDeletedException;
import com.revex.docrepo.exceptions.DocRepoFileNotUploadedException;
import com.revex.docrepo.exceptions.DocRepoFilesProblemException;
import com.revex.docrepo.exceptions.DocRepoPathTraversalAttackException;
import com.revex.docrepo.utils.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {QualificationWorkController.class})
public class QualificationWorkControllerAdvice {

    @ExceptionHandler({
            DocRepoPathTraversalAttackException.class,
            DocRepoFileNotUploadedException.class,
            DocRepoFileNotDeletedException.class,
            DocRepoFilesProblemException.class
    })
    public final ResponseEntity<ApiError> pathTraversalAttack(DocRepoApplicationException e) {
        return ResponseEntity.badRequest()
                .body(ApiError.builder()
                        .reason(e.getMessage())
                        .build());
    }

}
