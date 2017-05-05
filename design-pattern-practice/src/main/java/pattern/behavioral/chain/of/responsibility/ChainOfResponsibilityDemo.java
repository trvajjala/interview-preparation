package pattern.behavioral.chain.of.responsibility;

/**
 * Every Day example: java.util.Logger javax.servlet.Filter spring security filter chain
 *
 * @author ThirupathiReddy V
 */
public class ChainOfResponsibilityDemo {

    public static void main( String[] args ) {

        final HandlerRequest handlerRequest = new HandlerRequest();
        handlerRequest.setRequestType( RequestType.PURCHASE );

        final Distributor distributor = new Distributor();

        final Director director = new Director();
        director.setHandler( distributor );

        final President president = new President();
        president.setHandler( director );

        president.handleRequest( handlerRequest );

    }
}
