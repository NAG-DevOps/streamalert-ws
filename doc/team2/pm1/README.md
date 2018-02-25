## Dependencies

* Install [Eclipse Neon 3](https://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/neon3)
* Install [Tomcat 7](https://tomcat.apache.org/download-70.cgi)
* Download the project source files
* Configure the project to work with your installed Tomcat
	+ The local Tomcat server used to generate the WSDL file and Client code was using port `9999`
* Prior to doing part 3, please refer to [StreamAlert Preconfigured Setup](https://bitbucket.org/soen487-w18-02/soen487-w18-team02/wiki/StreamAlert%20Preconfigured%20Setup)

## Part 1 - XML Parsing Java Application 

### Setting Up The Application In Eclipse ###

* Create a new empty Java project
* Copy `src/service/xml` from the zip and paste it in the Eclipse project under `src`
* Delete the `xml.WebContent` and `xml.WebContent.wsdl` packages
* Copy `src/utilities` from the zip and paste it in the Eclipse project under `src`

### Running The Application From Eclipse ###

* The main method is located in `src/xml/utils/XMLParser.java`
* Click the dropdown on the run symbol
* Select "Run Configurations..."
* In the right window pane, click the "Arguments" tab
* Enter the parameters in the "Program arguments" textbox
* The following are the possible usages of the program (note that the braces should not be entered as part of the arguments):
	+ To use the default URI for a given Markup and no search term
		- [Markup] [Parser]
	+ To use the default URI for a given Markup and a search term (do not use quotations in the SearchTerm)
		- [Markup] [Parser] "" [SearchTerm]
	+ To specify a new URI and no search term (do not use quotations in the URI)
		- [Markup] [Parser] [URI]
	+ To specify a new URI and a search term (do not use quotations in the URI or SearchTerm)
		- [Markup] [Parser] [URI] [SearchTerm]
* Possible options for [Markup] are:
	+ 0: RSS
	+ 1: NN
	+ 2: MARFCATIN
	+ 3: MARFCATOUT
	+ 4: WSDL
* Possible options for [Parser] are:
	+ 0: DOM
	+ 1: SAX
* Click "Apply", and then "Run"
* The results are then displayed in the Console

### Running The Application From The Command Line ###

* From the command line, locate where the jar file was downloaded
* The following are the possible usages of the program (note that the braces should not be entered as part of the arguments):
	+ To use the default URI for a given Markup and no search term
		- programName [Markup] [Parser]
	+ To use the default URI for a given Markup and a search term (do not use quotations in the SearchTerm)
		- programName [Markup] [Parser] "" [SearchTerm]
	+ To specify a new URI and no search term (do not use quotations in the URI)
		- programName [Markup] [Parser] [URI]
	+ To specify a new URI and a search term (do not use quotations in the URI or SearchTerm)
		- programName [Markup] [Parser] [URI] [SearchTerm]
* Possible options for [Markup] are:
	+ 0: RSS
	+ 1: NN
	+ 2: MARFCATIN
	+ 3: MARFCATOUT
	+ 4: WSDL
* Possible options for [Parser] are:
	+ 0: DOM
	+ 1: SAX
* The results are then displayed in the command line

### References and Resources Used ###

* We used the following GitHub pages to help out our parsing:
	+ [https://github.com/smokhov/atsm/tree/master/examples/ws/XML/XMLParsing/src](https://github.com/smokhov/atsm/tree/master/examples/ws/XML/XMLParsing/src)
	+ [https://github.com/smokhov/atsm/blob/master/examples/ws/XML/XMLParsing/src/SAXSample.java](https://github.com/smokhov/atsm/blob/master/examples/ws/XML/XMLParsing/src/SAXSample.java)
	+ [https://github.com/smokhov/atsm/blob/master/examples/ws/XML/XMLParsing/src/XPathSample.java](https://github.com/smokhov/atsm/blob/master/examples/ws/XML/XMLParsing/src/XPathSample.java)


## Part 2 - XML Parsing as a SOAP Service

### Setting Up The Web Service In Eclipse ###

* Create a new empty Dynamic Web Project called **PM1_Service**
* Copy `src/service/xml` from the zip and paste it in the Eclipse project under `src`
* Delete the `xml.WebContent` and `xml.WebContent.wsdl` packages
* Copy `src/utilities` from the zip and paste it in the Eclipse project under `src`
* Copy `src/service/xml/WebContent` from the zip and paste it in the root of the Eclipse project. Overwrite the existing files
* If you did not name your project **PM1_Service**, in Eclipse, open the **`XMLParser.wsdl`** in source view and change the **`wsdlsoap:address`** location attribute to have the name of your project in the path
* If your Tomcat server is using a port other than `9999`, open the **`XMLParser.wsdl`** in source view and change the **`wsdlsoap:address`** location attribute to have the port of your Tomcat server in the path

### Setting Up The Web Client In Eclipse ###

* Create a new empty Dynamic Web Project called **PM1_Client**
* Copy `src/client/xml` from the zip and paste it in the Eclipse project under `src`
* Delete the `xml.WebContent` package
* Copy `src/utilities` from the zip and paste it in the Eclipse project under `src`
* Copy `src/client/xml/WebContent` from the zip and paste it in the root of the Eclipse project. Overwrite the existing files
* If you did not name your service project **PM1_Service** in the previous section, in Eclipse, open **`src/xml/utils/XMLParserServiceLocator.java`** and change the value assigned to **`XMLParser_address`** to have the name of your project in the path
* If your Tomcat server is using a port other than 9999, open **`src/xml/utils/XMLParserServiceLocator.java`** and change the value assigned to **`XMLParser_address`** to have the port of your Tomcat server in the path

### Running The Web Client In Eclipse ###

* Select the **PM1_Client** project in the tree view pane
* Click the dropdown on the run symbol
* Select "Run As" -> "Run on Server"
* Select an existing `Apache Tomcat v7.0` server instance and click on finish
* An HTML form with the parsing parameters and a button to parse is displayed

### Notes ###

Part 2 uses the same **`ReaderServlet.java`** and **`index.jsp`** as Part 3, and therefore has dependencies on the **`LiveTest`** StreamAlert service created in Part 3.
If you want to run Part 2 standalone, comment out the following lines:

* In **`ReaderServlet.java`**
    * Line 10: `import streamalert.util.LiveTestProxy;`
	* Line 44: `String fileName = request.getParameter("fileName");`
	* Lines 50-51: 
	```
    LiveTestProxy streamAlertProxy = new LiveTestProxy();
    streamAlertProxy.XMLLiveTest(fileName, output);
    ```
* In **`index.jsp`**
    * Lines 32-35:
    ```
    <div>
       <label>File Name: </label>
       <input type="text" name="fileName" placeholder="Name for StreamAlert Test"/>
    </div>
    ```

## Part 3 - StreamAlert as a SOAP Service

### Setting Up The Web Service In Eclipse ###

* Create a new empty Dynamic Web Project called **`PM1_StreamAlert_Service`**
* Copy `src/service/streamalert` from the zip and paste it in the Eclipse project under `src`
* Delete the `streamalert.WebContent` and `streamalert.WebContent.wsdl` packages
* Copy `src/utilities` from the zip and paste it in the Eclipse project under `src`
* Copy `src/service/streamalert/WebContent` from the zip and paste it in the root of the Eclipse project. Overwrite the existing files
* In Eclipse, open `src/streamalert/util/LiveTest.java` and change the variable **`pathToStreamAlert`** to have your path to the streamalert root directory.
* If you did not name your project **`PM1_StreamAlert_Service`**, in Eclipse, open the **`LiveTest.wsdl`** in source view and change the **`wsdlsoap:address`** location attribute to have the name of your project in the path
* If your Tomcat server is using a port other than `9999`, open the **`LiveTest.wsdl`** in source view and change the **`wsdlsoap:address`** location attribute to have the port of your Tomcat server in the path

### Setting Up The Web Client In Eclipse ###

* Follow the steps outlined in `Part 2 - Running The Web Client In Eclipse` to create a Client that uses the XMLParser service.
* Copy `src/client/streamalert` from the zip and paste it in the Eclipse project under `src`
* Be sure that the `xml.WebContent` package is deleted
* Be sure that `src/utilities` from the zip and is in the Eclipse project under `src`
* Be sure that `src/client/xml/WebContent` from the zip and is in the root of the Eclipse project
* Make sure that if your service project name is not **PM1_StreamAlert_Service** in the previous section, in Eclipse, open **`src/streamalert/util/LiveTestServiceLocator.java`** and change the value assigned to **`LiveTest_address`** to have the name of your project in the path
* Make sure that your Tomcat server is using a port other than `9999`, open **`src/streamalert/util/LiveTestServiceLocator.java`** and change the value assigned to **`LiveTest_address`** to have the port of your Tomcat server in the path.

### Running The Web Client In Eclipse ###

* Select the client project in the tree view pane
* Click the dropdown on the run symbol
* Select "Run As" -> "Run on Server"
* Select an existing Tomcat v7.0 server instance and click on finish Add a comment to this line
* An HTML form with the parsing parameters, test file name, and a button to parse is displayed.

### Notes ###

* To test, do not select `RSS, MARFCATIN, or MARFCATOUT` default XML files to parse due to [StreamAlert-Slack integration limitations.](https://bitbucket.org/soen487-w18-02/soen487-w18-team02/wiki/StreamAlert%20Issues%20Identification)
* When inputting a file name in the input box, be sure to include `.json` as part of the file name. Ex: `my_test_file.json`.
    * If left empty, the default file name of `test_slack_xml.json` will be used.

### FAQ ###
* Why am I getting a `The superclass "javax.servlet.http.HttpServlet" was not found on the Java Build Path` in my `index.jsp` file?
	* There is no Target Runtimes specified. To specify -> Right click on the project `PM1_Client`, select `Properties`, from the left panel, select the `Target Runtimes` menu option, check the `Apache Tomcat v7.0` checkbox and `Save` changes.

### Who do I talk to? ###

* [Alex Genio - Team Leader](mailto:alexgenio1995@gmail.com)
