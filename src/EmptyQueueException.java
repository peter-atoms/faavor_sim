// Created originally for Green Line simulation
// For CSCI 1933 at University of Minnesota
// Instructor: Chris Dovolis (dovolis@cs.umn.edu)

public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException(String s){
        super(s);
    } // One argument
    public EmptyQueueException(){super();} // default
} // End EmptyQueueException