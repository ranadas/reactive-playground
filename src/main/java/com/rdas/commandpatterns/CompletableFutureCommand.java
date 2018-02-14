package com.rdas.commandpatterns;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by rdas on 14/02/2018.
 */
public class CompletableFutureCommand extends StreamCommand {
    @Override
    public String getName() {
        return "CompletableFuture";
    }

    @Override
    public void executeInternal(Consumer consumer) throws Exception {
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(THREAD_NUMBER);
        List<Integer> numbers = getNumbers();
        List<CompletableFuture<String>> futures = numbers.stream()
                .map(number -> CompletableFuture.supplyAsync(() -> slowMappingFunction(number), threadPoolExecutor))
                .collect(Collectors.toList());
        String values = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.joining(","));
        threadPoolExecutor.shutdown();
        consumer.accept(values);
    }
}
