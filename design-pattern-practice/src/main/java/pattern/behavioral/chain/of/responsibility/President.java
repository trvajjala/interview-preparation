package pattern.behavioral.chain.of.responsibility;

public class President extends Handler {
    @Override
    public void handleRequest( HandlerRequest handlerRequest ) {
        if ( RequestType.LEASE == handlerRequest.getRequestType() ) {
            System.out.println( "President[Leasing handled ]" );
        } else {
            System.out.println( "president -> handed over to the next handler " );
            handler.handleRequest( handlerRequest );
        }

    }
}
