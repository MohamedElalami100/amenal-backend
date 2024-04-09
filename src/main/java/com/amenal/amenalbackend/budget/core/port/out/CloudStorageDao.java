package com.amenal.amenalbackend.budget.application.port.out;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import com.amenal.amenalbackend.budget.application.domain.Document;

public interface CloudStorageDao {

	public Document uploadFileToCloud(File file) throws GeneralSecurityException, IOException;
}
