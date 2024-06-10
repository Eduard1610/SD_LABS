package WSSOAP;
import javax.xml.ws.Endpoint;

public class PublishServices {
    public static void main(String[] args) {
        /*
         * Se publican los servicios a través de un servidor virtual.
         * El puerto puede ser cualquiera.
         * Una vez ejecutada la aplicación, se publica el contrato WSDL.
         */
        Endpoint.publish("http://localhost:1516/WS/Users", new SOAPImpl());
    }
}
