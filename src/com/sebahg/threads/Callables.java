package com.sebahg.threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Callables {

    public static void main(String args[]) {
        Callables callables = new Callables();
        try {
            callables.execute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void execute() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Callable<String> callable1 = () -> "Calling callable 1";
        Callable<String> callable2 = () -> "Calling callable 2";

        List<Future<String>> futureList = executor.invokeAll(Arrays.asList(callable1, callable2));
        futureList.stream().forEach(c -> {
            try {
                System.out.println(c.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
