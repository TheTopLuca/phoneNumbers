package com.PhoneNumbers.springboot.exception;

public class InvalidInputException extends Exception {
    String message;

    public InvalidInputException(String message){
        this.message = message;
    }
}
