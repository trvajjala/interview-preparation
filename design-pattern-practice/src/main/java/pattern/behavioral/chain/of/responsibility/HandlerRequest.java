package pattern.behavioral.chain.of.responsibility;

public class HandlerRequest {

    private RequestType requestType;

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType( RequestType requestType ) {
        this.requestType = requestType;
    }

}
