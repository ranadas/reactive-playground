package com.rdas.commandpatterns;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by rdas on 14/02/2018.
 */
//@Slf4j
public abstract class StreamCommand {
    Logger LOG = LoggerFactory.getLogger(StreamCommand.class);

    int THREAD_SLEEP_MS = 500;
    int NUMBERS_COUNT = 20;
    int THREAD_NUMBER = 3;

    public void execute() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            executeInternal((value) -> LOG.info("{}: value: [{}], Elapsed: {}, ", getName(), value, stopwatch.toString()));
        } catch (Exception e) {
            LOG.error("Could not execute command: {}", e.getMessage(), e);
        }
    }

    protected  String slowMappingFunction(Integer number) {
        LOG.debug("{}: executing time consuming task on thread [{}]...", getName(), Thread.currentThread().getName());
        try {
            Thread.sleep(THREAD_SLEEP_MS);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
        return String.valueOf(number);
    }

    protected  List<Integer> getNumbers() {
        return IntStream.range(0, NUMBERS_COUNT).boxed().collect(Collectors.toList());
    }

    abstract String getName();

    abstract void executeInternal(Consumer consumer) throws Exception;
}
