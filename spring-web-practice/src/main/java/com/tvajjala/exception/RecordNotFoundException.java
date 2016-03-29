package com.tvajjala.exception;

/**
 * RecordNotFoundException.java
 *
 * @author ThirupathiReddy Vajjala
 *
 */
public class RecordNotFoundException extends ServiceException {

    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = -4638842735049520769L;

    private String property;

    /**
     * Constructor
     *
     */
    public RecordNotFoundException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor
     *
     * @param message
     */
    public RecordNotFoundException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     *
     * Constructor
     *
     * @param property
     * @param message
     */
    public RecordNotFoundException(String property, String message) {
        super(message);
        this.property = property;
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor
     *
     * @param message
     * @param cause
     */
    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public RecordNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor
     *
     * @param cause
     */
    public RecordNotFoundException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * This method returns the value held within the field property.
     *
     * @return the property
     */
    public String getProperty() {
        return property;
    }

}
