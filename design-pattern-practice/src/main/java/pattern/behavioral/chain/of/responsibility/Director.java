package pattern.behavioral.chain.of.responsibility;

public class Director extends Handler {

    @Override
    public void handleRequest( HandlerRequest handlerRequest ) {

        if ( RequestType.BOOKING == handlerRequest.getRequestType() ) {
            System.out.println( "Director[ Booking handled]" );
        } else {
            System.out.println( "director -> handed over to the next handler " );
            handler.handleRequest( handlerRequest );
        }
    }
}
