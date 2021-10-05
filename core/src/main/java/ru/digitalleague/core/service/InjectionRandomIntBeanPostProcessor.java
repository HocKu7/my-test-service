package ru.digitalleague.core.service;

import lombok.SneakyThrows;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class InjectionRandomIntBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Method> beanName2Method = new HashMap<>();

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        Field[] declaredFields = bean.getClass().getDeclaredFields();
//
//        for (Field declaredField : declaredFields) {
//
//            Annotation[] annotations = declaredField.getAnnotations();
//            for (Annotation annotation : annotations) {
//
//                if (InjectRandomInt.class.equals(annotation.annotationType())) {
//                    declaredField.setAccessible(true);
//                    int i = new Random().nextInt();
//                    declaredField.setInt(bean, i % 20);
//                }
//            }
//        }

        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {

            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {

                if (LogTime.class.equals(annotation.annotationType())) {

                    beanName2Method.put(beanName, method);
                }
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (beanName2Method.get(beanName) == null) {
            return bean;
        }
        Class<?>[] interfaces = bean.getClass().getInterfaces();
        Object proxyInstance = Proxy.newProxyInstance(this.getClass().getClassLoader(), interfaces,
                (proxy, method, args) -> {

                    System.out.println("Start method " + beanName2Method.get(beanName).getName());
                    Object result = method.invoke(args);
                    System.out.println("End method: " + beanName2Method.get(beanName).getName());
                    return result;
                });
        return proxyInstance;
    }
}
