package pattern.behavioral.chain.of.responsibility;

public abstract class Handler {

    Handler handler;

    public void setHandler( Handler handler ) {
        this.handler = handler;
    }

    public abstract void handleRequest( HandlerRequest handlerRequest );

}
