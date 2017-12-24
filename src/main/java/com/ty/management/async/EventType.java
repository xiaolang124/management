package com.ty.management.async;

public enum EventType {
    TEST(99);

    private int value;

    EventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
