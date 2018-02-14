package com.rdas.service;

import com.rdas.model.StockInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rdas on 14/02/2018.
 * https://github.com/gabor-bata/rx-java-sample
 */
@Slf4j
@Service
public class StockService {

    public void start() {
        List<String> stockSymbols = Arrays.asList("GOOG", "AMZN", "ITC");

        Observable<StockInfo> feed = StockService.getFeed(stockSymbols);
        log.debug("Got observable...");

        feed.retry()
                .filter(stockInfo -> stockInfo.getValue() > 500.0)
                .map(stockInfo -> new StockInfo(stockInfo.getTicker(), stockInfo.getValue() * 0.9))
                .subscribe(
                        stockInfo -> log.info(stockInfo.toString()),
                        error -> log.warn(error.getMessage()),
                        () -> log.info("DONE")
                );

    }

    public static Observable<StockInfo> getFeed(List<String> symbols) {
        log.debug("Create observable...");
        return Observable.create(emitter -> emitPrice(emitter, symbols));
    }

    private static void emitPrice(ObservableEmitter<StockInfo> emitter, List<String> symbols) {
        log.debug("Ready to emit...");
        int count = 0;
        while (count < 5) {
            symbols.stream().map(StockInfo::fetch).forEach(emitter::onNext);
            sleep();
            count++;
            if (Math.random() > 0.8) {
                log.warn("Some error happened...");
                emitter.onError(new RuntimeException("Oops - thrown intentionally..."));
            }
        }

        emitter.onComplete();
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn(e.getMessage());
        }
    }
}
