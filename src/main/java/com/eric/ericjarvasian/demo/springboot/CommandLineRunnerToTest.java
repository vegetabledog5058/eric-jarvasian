package com.eric.ericjarvasian.demo.springboot;

import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

//@Component
public class CommandLineRunnerToTest implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunnerToTest");
        System.out.println(Arrays.toString(args));
    }
}
