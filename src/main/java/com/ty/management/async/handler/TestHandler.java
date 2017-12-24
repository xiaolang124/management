package com.ty.management.async.handler;

import com.ty.management.async.EventHandler;
import com.ty.management.async.EventModel;
import com.ty.management.async.EventType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TestHandler implements EventHandler{

    @Override
    public void doHandle(EventModel model) {
        System.out.println("This is test handler");
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.TEST);
    }
}
