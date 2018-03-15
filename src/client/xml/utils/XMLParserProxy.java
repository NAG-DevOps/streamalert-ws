package xml.utils;

public class XMLParserProxy implements xml.utils.XMLParser {
  private String _endpoint = null;
  private xml.utils.XMLParser xMLParser = null;
  
  public XMLParserProxy() {
    _initXMLParserProxy();
  }
  
  public XMLParserProxy(String endpoint) {
    _endpoint = endpoint;
    _initXMLParserProxy();
  }
  
  private void _initXMLParserProxy() {
    try {
      xMLParser = (new xml.utils.XMLParserServiceLocator()).getXMLParser();
      if (xMLParser != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)xMLParser)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)xMLParser)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (xMLParser != null)
      ((javax.xml.rpc.Stub)xMLParser)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public xml.utils.XMLParser getXMLParser() {
    if (xMLParser == null)
      _initXMLParserProxy();
    return xMLParser;
  }
  
  public void main(java.lang.String[] args) throws java.rmi.RemoteException{
    if (xMLParser == null)
      _initXMLParserProxy();
    xMLParser.main(args);
  }
  
  public java.lang.String parseXML(int markup, java.lang.String uri, int parserType, java.lang.String searchTerm) throws java.rmi.RemoteException{
    if (xMLParser == null)
      _initXMLParserProxy();
    return xMLParser.parseXML(markup, uri, parserType, searchTerm);
  }
  
  
}