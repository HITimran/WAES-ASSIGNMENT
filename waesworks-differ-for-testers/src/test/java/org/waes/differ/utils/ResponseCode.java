package org.waes.differ.utils;

public enum ResponseCode {
    OK(200,"Success"),
    UNSUPPORTED_MEDIA_TYPE_JSON(415,"Value in request body must be in JSON format."),
    NOT_FOUND(404,"not initialized."),
    BAD_REQUEST(400,"Value in request body cannot be empty."),
    REQUEST_FAILED(500,"Request Failed"),
    NOT_IMPLEMENTED(501,"This side is not supported, please use either 'left' or 'right'."),
    BASE64Exception(415,"Data in body not Base64 formatted."),
    NO_CONTENT(204,"No Content");

    private final int code;
    private final String reason;

    ResponseCode(int statusCode, String reasonPhrase) {
        this.code = statusCode;
        this.reason = reasonPhrase;
    }

   public int getStatusCode()
    {
        return code;
    }

    public String getreason()
    {
        return reason;
    }

    }
