package com.revex.docrepo.services;

import com.revex.docrepo.exceptions.DocRepoFileNotDeletedException;
import com.revex.docrepo.exceptions.DocRepoFileNotUploadedException;
import com.revex.docrepo.exceptions.DocRepoPathTraversalAttackException;
import com.revex.docrepo.utils.DocumentType;
import com.revex.docrepo.utils.UploadFileOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DocumentUploadingService {

    private final Logger logger = LogManager.getLogger(DocumentUploadingService.class);

    private Path docsFilePath;

    public DocumentUploadingService(@Value("${docrepo.docs.filepath}") Path path) {
        initRepository(path);
    }

    public Path uploadFile(UploadFileOptions options, MultipartFile file) {
        String relativeDirectoryPath = options.toString();

        Path relativeFilePath = Paths.get(relativeDirectoryPath, file.getOriginalFilename()).normalize();
        Path absoluteDirectoryPath = docsFilePath.resolve(relativeFilePath).normalize();
        Path sanitizedPath = this.sanitizeFile(docsFilePath.resolve(relativeDirectoryPath), absoluteDirectoryPath);

        try {
            Files.createFile(sanitizedPath);
            Files.write(sanitizedPath, file.getBytes());

            return relativeFilePath;
        } catch (IOException e) {
            throw new DocRepoFileNotUploadedException(e);
        }
    }

    public void deleteFile(Path relativePath) {
        Path absolutePath = docsFilePath.resolve(relativePath);

        try {
            Files.delete(absolutePath);
        } catch (IOException e) {
            throw new DocRepoFileNotDeletedException(e);
        }
    }

    private Path sanitizeFile(Path expectedPathStart, Path actualPath) {
        if (!actualPath.startsWith(expectedPathStart)) {
            throw new DocRepoPathTraversalAttackException("Filename contains path traversal payload.");
        }

        return actualPath;
    }

    private void initRepository(Path path) {
        this.docsFilePath = path;

        try {
            if (Files.notExists(this.docsFilePath)) {
                Files.createDirectory(this.docsFilePath);
            }

            for (DocumentType type : DocumentType.values()) {
                if (Files.notExists(this.docsFilePath.resolve(type.toString()))) {
                    Files.createDirectory(this.docsFilePath);
                }
            }
        } catch (FileAlreadyExistsException e) {
            logger.warn(e.getFile() + " already exists!");
        } catch (IOException e) {
            logger.fatal("Problems in working with files!", e);
        }
    }
}