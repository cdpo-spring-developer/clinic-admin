package com.springlessons.clinicadmin.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminException extends Exception{
    public AdminException(String message) {
        super(message);

        log.error(message);
    }

    public AdminException(String message, Throwable cause) {
        super(message, cause);

        log.error(message, cause);
    }
}
