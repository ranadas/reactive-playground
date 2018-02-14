package com.rdas.commandpatterns;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by rdas on 14/02/2018.
 */
public class ReactorCommand extends StreamCommand {
    @Override
    String getName() {
        return "Reactor";
    }

    @Override
    void executeInternal(Consumer consumer) throws Exception {
        Flux<Integer> numbers = Flux.fromIterable(getNumbers());

        String values = numbers.parallel(THREAD_NUMBER)
                .runOn(Schedulers.newParallel("reactor", THREAD_NUMBER, true))
                .map(this::slowMappingFunction)
                .sequential()
                .collect(Collectors.joining(","))
                .block();
        consumer.accept(values);
    }
}
