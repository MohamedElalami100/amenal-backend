package com.amenal.amenalbackend.application.project.port.out;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import com.amenal.amenalbackend.application.project.domain.Document;

public interface GoogleDriveDao {

	public Document uploadFileToDrive(File file) throws GeneralSecurityException, IOException;
}
