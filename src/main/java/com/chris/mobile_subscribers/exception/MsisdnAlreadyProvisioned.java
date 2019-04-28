package com.chris.mobile_subscribers.exception;

import java.util.concurrent.ExecutionException;

public class MsisdnAlreadyProvisioned extends ExecutionException {

    private static final long serialVersionUID = -5851619114639857539L;

    public MsisdnAlreadyProvisioned(String message) {
        super(message);
    }
}