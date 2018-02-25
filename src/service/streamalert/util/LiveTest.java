package streamalert.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import utilities.Validation;

public class LiveTest {

	// This path to StreamAlert must be replaced by your local setup
	private String pathToStreamAlert = "/Users/AlexGenio/Documents/School/Concordia/WebProg_year_3/Winter_2018/SOEN_487/project/streamalert/";
	private String pathToTests = pathToStreamAlert + "/tests/integration/rules/soen487/";
	private String name = "test_slack_xml.json";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LiveTest tester = new LiveTest();
		
		tester.XMLLiveTest("test_slack_xml.json", "tag=JavaNode attrname=NewConfig attrvalue=fromService");
	}
	
	/**
	 * Performs a StreamAlert live-test to send an alert to Slack
	 * with the given data. Makes use of helper methods to create the
	 * test file and to execute the live-test.
	 * 
	 * @param filename The name of the test file to be created
	 * @param data The key-value data to be alerted in Slack
	 *             (must comply with "soen487-xml-kv" schema defined
	 *              streamalert/conf/logs.json)
	 * @return
	 */
	public void XMLLiveTest(String filename, String data) {
		
		// use default test name if the filename passed is not valid
		if(Validation.isValidString(filename)) {
			name = filename;
		}
		
		String filePath = pathToTests + name;
		
		//splitting  data in case it is too large
		//String[] splitedData = data.split("\\s+");
		//splitedData = generateStreamAlertFormat(splitedData);
		
		//for (String singularData : splitedData) {
			// create the test file to perform a live-test on
			createTest(filePath, data);
			
			// run the live test for the --cluster prod --rules xml_to_slack_2
			executeLiveTest();
		//}	
	}
	
	/**
	 * 
	 * @param splitedData
	 * @return
	 */
	private String[] generateStreamAlertFormat(String[] splitedData) {
		//making each split data into tag, attrname and attrvalue format
		splitedData[0] += " attrname=... attrvalue=..."; 
		splitedData[1] = "tag=... " + splitedData[1] + " attrvalue=...";
		splitedData[2] = "tag=... attrname=... " + splitedData[2];
		return splitedData;
	}
	/**
	 * Creates a test file under streamalert/tests/integration/rules/soen487
	 * which will trigger a rule which alerts the data into a Slack channel. The
	 * source bucket must be globally unique and is hardcoded as such. The rule
	 * to be triggered is also hardcoded to a custom rule responsible for handling
	 * logs whose schema reflects the output of the XMLParser service.
	 * 
	 * See https://www.streamalert.io/conf-schemas.html for more details about
	 * log schemas and rules.
	 * 
	 * @param filePath The absolute path to the test to create
	 * @param data The key-value data to be used in the test and sent to the rule
	 * @return
	 */
	private void createTest(String filePath, String data) {
		
		File file;
		FileWriter writer;
		PrintWriter output;
		
		try {
			// create the file
			file = new File(filePath);
			file.getParentFile().mkdirs();
			
			writer = new FileWriter(file, false);
			output = new PrintWriter(writer);
			
			// write the test data to the file
			output.println("{");
			output.println("  \"records\": [");
			output.println("    {");
			output.println("      \"data\": \"" + data + "\",");
			output.println("      \"description\": \"Sending key value pairs with a tag name should trigger an alert.\",");
			output.println("      \"log\": \"soen487-xml-kv\",");
			output.println("      \"source\": \"soen487g2.testing.xml.soap\",");
			output.println("      \"service\": \"s3\",");
			output.println("      \"trigger_rules\": [");
			output.println("        \"xml_to_slack_2\"");
			output.println("      ]");
			output.println("    }");
			output.println("  ]");
			output.println("}");
			output.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * Executed a test using the "live-test" option to carry out
	 * the alert. Required to activate the virtual environment and
	 * specify the AWS profile to use before actually running the
	 * test. Displays standard output and standard error.
	 * 
	 * @return
	 */
	private void executeLiveTest() {
		try {
			String s = null;
			System.out.println("Executing the live test on cluster 'prod' and rule 'xml_to_slack_2'.");
			
			// commands to run to execute the live test
			String changeToStreamAlertRoot = "cd " + pathToStreamAlert + ";";
			String activateVenv = "source venv/bin/activate;";
			String selectAWSProfile = "export AWS_PROFILE=streamalert;";
			String listAccountS3Buckets = "aws s3 ls;";
			String runLiveTest = pathToStreamAlert + "manage.py live-test --cluster prod --rules xml_to_slack_2;";
			String[] cmd = { "/bin/sh", "-c", changeToStreamAlertRoot + activateVenv + selectAWSProfile + listAccountS3Buckets + runLiveTest };
			
			// execute the live test
			Process p = Runtime.getRuntime().exec(cmd);
			
			BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            
            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

}
