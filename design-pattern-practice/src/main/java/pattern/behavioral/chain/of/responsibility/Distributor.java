package pattern.behavioral.chain.of.responsibility;

public class Distributor extends Handler {

    @Override
    public void handleRequest( HandlerRequest handlerRequest ) {

        if ( RequestType.PURCHASE == handlerRequest.getRequestType() ) {
            System.out.println( "Distributor[  handling purchase ] " );
        } else {
            // System.out.println( "distributor -> handed over to the next handler " );
            // handler.handleRequest( handlerRequest );
        }
    };
}
