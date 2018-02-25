package com.sid.onlonebanking.serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.sid.onlonebanking.repository.PrimaryTransactionRepository;
import com.sid.onlonebanking.repository.UserRepository;
import com.sid.onlonebanking.service.S3Service;
import com.sid.onlonebanking.vo.PrimaryTransaction;
import com.sid.onlonebanking.vo.Users;

@Service
public class S3ServiceImpl implements S3Service {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	protected TransferManager transferManager;
	
	@Autowired
	private AmazonS3 s3client;
 
	@Value("${jsa.s3.bucket}")
	private String bucketName;

	@Override
	public void downloadFile(String keyName, String downloadFilePath) {
		System.out.println("Downloading an object");
		final GetObjectRequest request = new GetObjectRequest(bucketName, keyName);
		request.setGeneralProgressListener(new ProgressListener() {
			@Override
			public void progressChanged(ProgressEvent progressEvent) {
				String transferredBytes = "Downloaded bytes: " + progressEvent.getBytesTransferred();
				System.out.println((transferredBytes));
		}
		});
        //System.out.println("Content-Type: "  + s3object.getObjectMetadata().getContentType());
        Download download = transferManager.download(request, new File(downloadFilePath));
       // Utility.displayText(s3object.getObjectContent());
        try {
			download.waitForCompletion();
		} catch (AmazonServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AmazonClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}

	@Override
	public void uploadFile(String keyName, String uploadFilePath, String username) {
		try {
		File file = new File(uploadFilePath);
        s3client.putObject(new PutObjectRequest(bucketName, keyName, file));
        System.out.println("Upload Success");
        List<String> statementList = listAllObjectInS3Bucket(username);
        Collections.reverse(statementList);
        Users user = userRepository.findByUsername(username);
        user.setAccountStatements(statementList);
        userRepository.save(user);
		}catch(AmazonServiceException e) {
			System.out.println(e);
		}
	}
	
	public void displayText(InputStream input) throws IOException{
		// Read one text line at a time and display.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            System.out.println("    " + line);
        }
	}
	
	private List<String> listAllObjectInS3Bucket(String username) { //razi_, ham2095_
		ObjectListing o1 = s3client.listObjects(bucketName);
		List<String> finalOutput = new ArrayList<>();
        List<S3ObjectSummary> objects = o1.getObjectSummaries();
        for(S3ObjectSummary os : objects) {
        	String[] split = os.getKey().split("_");
        	String zeroIndex = split[0];
        	if(zeroIndex.equals(username)) {
				finalOutput.add(os.getKey());
			}
        }
        return finalOutput;
	}

}
