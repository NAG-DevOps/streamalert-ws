import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class AWSUtils {

	private String bucketName;
	private String region;
	
	private AmazonS3 s3Client;
	
	public AWSUtils(String bucketName, String region) {
		this.bucketName = bucketName;
		this.region = region;
		
        s3Client = AmazonS3ClientBuilder.standard()
        		.withCredentials(new EnvironmentVariableCredentialsProvider()) 
                .withRegion(this.region)
                .build();
	}
	
	public void upload(InputStream inputStream, String keyName) {       
        try {
            System.out.println("Uploading a new object to S3 from a file\n");
            s3Client.putObject(new PutObjectRequest(
            		                 bucketName, keyName, inputStream, new ObjectMetadata()));

         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
	}
}
