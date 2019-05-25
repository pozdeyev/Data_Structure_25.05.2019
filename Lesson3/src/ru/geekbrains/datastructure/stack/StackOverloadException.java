package ru.geekbrains.datastructure.stack;

public class StackOverloadException extends RuntimeException {


    public StackOverloadException(){
        super ("Stack is full");
    }

}
