package streamalert.util;

public class LiveTestProxy implements streamalert.util.LiveTest {
  private String _endpoint = null;
  private streamalert.util.LiveTest liveTest = null;
  
  public LiveTestProxy() {
    _initLiveTestProxy();
  }
  
  public LiveTestProxy(String endpoint) {
    _endpoint = endpoint;
    _initLiveTestProxy();
  }
  
  private void _initLiveTestProxy() {
    try {
      liveTest = (new streamalert.util.LiveTestServiceLocator()).getLiveTest();
      if (liveTest != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)liveTest)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)liveTest)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (liveTest != null)
      ((javax.xml.rpc.Stub)liveTest)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public streamalert.util.LiveTest getLiveTest() {
    if (liveTest == null)
      _initLiveTestProxy();
    return liveTest;
  }
  
  public void XMLLiveTest(java.lang.String filename, java.lang.String data) throws java.rmi.RemoteException{
    if (liveTest == null)
      _initLiveTestProxy();
    liveTest.XMLLiveTest(filename, data);
  }
  
  
}