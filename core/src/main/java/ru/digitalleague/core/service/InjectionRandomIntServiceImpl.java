package ru.digitalleague.core.service;

import lombok.SneakyThrows;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class InjectionRandomIntServiceImpl implements InjectionRandomIntService {

    @InjectRandomInt
    private int number;

    @PostConstruct
    public void init() {

        System.out.println("Hello " + number);
    }

    @SneakyThrows
    @Override
    @LogTime
    public void doSome() {

        Thread.sleep(1000);
    }
}
