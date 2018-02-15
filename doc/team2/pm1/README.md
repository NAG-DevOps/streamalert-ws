## Dependencies

* Install Eclipse Neon 3 (https://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/neon3)
* Install Tomcat 7 (https://tomcat.apache.org/download-70.cgi)
* Download the project source files
* Import the project into Eclipse
* Configure the project to work with your installed Tomcat

## Part 1 - XML Parsing Java Application 

### Setting Up The Application In Eclipse ###

* Create an empty java project
* Copy src/service/xml from the zip and paste it in the Eclipse project under src
* Delete the xml.WebContent and xml.WebContent.wsdl packages
* Copy src/utilities from the zip and paste it in the Eclipse project under src

### Running The Application From Eclipse ###

* The main method is located in src/xml/utils/XMLParser.java
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
	+ https://github.com/smokhov/atsm/tree/master/examples/ws/XML/XMLParsing/src
	+ https://github.com/smokhov/atsm/blob/master/examples/ws/XML/XMLParsing/src/SAXSample.java
	+ https://github.com/smokhov/atsm/blob/master/examples/ws/XML/XMLParsing/src/XPathSample.java


## Part 2 - XML Parsing as a SOAP Service

### Setting Up The Web Service In Eclipse ###

* Create an empty Dynamic Web Project called PM1_Service
* Copy src/service/xml from the zip and paste it in the Eclipse project under src
* Delete the xml.WebContent and xml.WebContent.wsdl packages
* Copy src/utilities from the zip and paste it in the Eclipse project under src
* Copy src/service/xml/WebContent from the zip and paste it in the Eclipse project under src. Overwrite the existing files
* If you did not name your project PM1_Service, in Eclipse, open the **XMLParser.wsdl** in source view and change the **wsdlsoap:address** location attribute to have the name of your project in the path

### Setting Up The Web Client In Eclipse ###

* Create an empty Dynamic Web Project
* Copy src/client/xml from the zip and paste it in the Eclipse project under src
* Delete the xml.WebContent package
* Copy src/utilities from the zip and paste it in the Eclipse project under src
* Copy src/client/xml/WebContent from the zip and paste it in the Eclipse project under src. Overwrite the existing files
* If you did not name your service project PM1_Service in the previous section, in Eclipse, open **src/xml/utils/XMLParserServiceLocator.java** and change the value assigned to **XMLParser_address** to have the name of your project in the path

### Running The Web Client In Eclipse ###

* Select the client project in the tree view pane
* Click the dropdown on the run symbol
* Select "Run As" -> "Run on Server"
* Select an existing Tomcat v7.0 server instance and click on finish
* An HTML form with the parsing parameters and a button to parse is displayed

### Who do I talk to? ###

* Alex Genio (Team Leader)