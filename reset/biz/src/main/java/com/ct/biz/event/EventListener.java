package com.ct.biz.event;

public interface EventListener<T> {

    void doEvent(T result);

}