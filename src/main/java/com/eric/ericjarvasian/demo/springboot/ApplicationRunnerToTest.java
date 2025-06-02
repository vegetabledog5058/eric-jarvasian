package com.eric.ericjarvasian.demo.springboot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

//@Component
public class ApplicationRunnerToTest implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("原始参数 (getSourceArgs):");
        for (String arg : args.getSourceArgs()) {
            System.out.println(arg);
        }

        System.out.println("\n非选项参数 (getNonOptionArgs):");
        for (String arg : args.getNonOptionArgs()) {
            System.out.println(arg);
        }

        System.out.println("\n选项参数名称 (getOptionNames):");
        for (String name : args.getOptionNames()) {
            System.out.println(name);
        }
    }

}
