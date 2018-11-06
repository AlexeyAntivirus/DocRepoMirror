package com.revex.docrepo.exchange.works;

import lombok.Builder;
import lombok.Data;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.zip.ZipFile;

@Data
@Builder
public class SendFilesResponsePayload {
	private Resource resource;
}
