package com.rdas.commandpatterns;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Created by rdas on 14/02/2018.
 */
public class RxJavaCommand extends StreamCommand {
    @Override
    String getName() {
        return "RxJava";
    }

    @Override
    void executeInternal(Consumer consumer) throws Exception {
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(THREAD_NUMBER);
        Scheduler scheduler = Schedulers.from(threadPoolExecutor);

        Observable<Integer> numbers = Observable.fromIterable(getNumbers());
        String values = numbers.flatMap(number -> Observable.just(number).subscribeOn(scheduler).map(this::slowMappingFunction))
                .serialize()
                .collect(() -> new StringJoiner(","), (joiner, text) -> joiner.add(text))
                .blockingGet().toString();
        threadPoolExecutor.shutdown();
        consumer.accept(values);
    }
}
