package com.rdas.commandpatterns;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by rdas on 14/02/2018.
 */
public class StreamApiCommand extends StreamCommand {
    @Override
    String getName() {
        return "Stream";
    }

    @Override
    void executeInternal(Consumer consumer) throws Exception {
        List<Integer> numbers = getNumbers();
        ForkJoinPool threadPool = new ForkJoinPool(THREAD_NUMBER);
        String values = threadPool.submit(() -> numbers.parallelStream().map(this::slowMappingFunction).collect(Collectors.joining(","))).get();
        consumer.accept(values);
    }
}
