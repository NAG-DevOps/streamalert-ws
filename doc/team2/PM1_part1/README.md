### How do I get set up? ###

* Install Eclipse Neon 3 (https://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/neon3)
* Install Tomcat 7 (https://tomcat.apache.org/download-70.cgi)
* Download the project source files
* Import the project into Eclipse
* Configure the project to work with your installed Tomcat

### Running the application from Eclipse ###

* The main method is located in src/pm1.xmlexercise/XMLParser.java
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
* The results are then desplayed in the Console

### Running the application from the command line ###

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
* The results are then desplayed in the command line

### Who do I talk to? ###

* Alex Genio
