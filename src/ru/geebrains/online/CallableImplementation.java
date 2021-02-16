package ru.geebrains.online;

import java.util.concurrent.Callable;

public interface CallableImplementation extends Callable<Float[]> {
    Float[] call(Float[] arr) throws Exception;
}
