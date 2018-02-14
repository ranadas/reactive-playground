package com.rdas.commandpatterns;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rdas on 14/02/2018.
 */
public class StreamCommandTest {
    private final List<StreamCommand> COMMANDS = Arrays.asList(
            new StreamApiCommand(),
            new CompletableFutureCommand(),
            new ReactorCommand(),
            new RxJavaCommand()
    );
    @Test
    public void runCommand() {
        COMMANDS.forEach(StreamCommand::execute);
    }
}