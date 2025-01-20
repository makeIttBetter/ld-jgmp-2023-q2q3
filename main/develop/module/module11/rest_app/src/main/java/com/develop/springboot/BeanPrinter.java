package com.develop.springboot;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BeanPrinter implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }

//    @Autowired
//    private ApplicationContext context;

//    public void printBeans() {
//        String[] beanNames = context.getBeanDefinitionNames();
//        for (String beanName : beanNames) {
//            System.out.println(beanName + " of Type :: " + context.getBean(beanName).getClass().getSimpleName());
//        }
//    }

//    @Override
//    public void run(String... args) throws Exception {
//        String[] beanNames = context.getBeanDefinitionNames();
//        for (String beanName : beanNames) {
//            if (Objects.equals(beanName, "apiSecurityConfig")) {
//                System.out.println(beanName + " of Type :: " + context.getBean(beanName).getClass().getSimpleName());
//            }
//        }
//    }
}