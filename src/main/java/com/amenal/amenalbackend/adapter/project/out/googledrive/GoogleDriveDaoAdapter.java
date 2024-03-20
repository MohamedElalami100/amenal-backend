package com.amenal.amenalbackend.adapter.project.out.googledrive;

import com.amenal.amenalbackend.application.project.domain.Document;
import com.amenal.amenalbackend.application.project.port.out.CloudStorageDao;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;
import org.springframework.stereotype.Service;

@SuppressWarnings("deprecation")
@Service
public class GoogleDriveDaoAdapter implements CloudStorageDao{

	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String SERVICE_ACOUNT_KEY_PATH = getPathToGoodleCredentials();

	private static String getPathToGoodleCredentials() {
		String currentDirectory = System.getProperty("user.dir");
		Path filePath = Paths.get(currentDirectory, "amenal-docs.json");
		return filePath.toString();
	}

	public Document uploadFileToCloud(File file) throws GeneralSecurityException, IOException {
		try {

			String folderId = "1yqkhDExYAq4ajWM9JB9u4sjbUr9ONvGe";
			Drive drive = createDriveService();
			
			//determine file name:
			com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
			fileMetaData.setName(file.getName());
			fileMetaData.setParents(Collections.singletonList(folderId));
			
			// Determine the file type (MIME type)
	        Path filePath = Paths.get(file.getAbsolutePath());
	        String type = Files.probeContentType(filePath);

			FileContent mediaContent = new FileContent("application/octet-stream", file);

			com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetaData, mediaContent)
					.setFields("id, name, description, size").execute();

			String url = "https://drive.google.com/uc?export=view&id="+uploadedFile.getId();			
			String name = uploadedFile.getName();
			Long size = uploadedFile.getSize();
			
			Document document = new Document(url, name, type, size, null);
			
			file.delete();

			return document;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	private Drive createDriveService() throws GeneralSecurityException, IOException {

		GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACOUNT_KEY_PATH))
				.createScoped(Collections.singleton(DriveScopes.DRIVE));

		return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential).build();

	}
}
