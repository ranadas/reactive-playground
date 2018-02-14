package com.rdas.service;

import com.rdas.commandpatterns.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rdas on 14/02/2018.
 */
@Service
public class StreamRunner {
    public void start() {
        List<StreamCommand> COMMANDS = Arrays.asList(
                new StreamApiCommand(),
                new CompletableFutureCommand(),
                new ReactorCommand(),
                new RxJavaCommand());
        COMMANDS.forEach(StreamCommand::execute);
    }
}
