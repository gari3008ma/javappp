package com.data.factory;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class AnnotationFactory<A extends Annotation, H> {
    private static final Logger log = LoggerFactory.getLogger(AnnotationFactory.class);

    public AnnotationFactory() {
    }

    protected abstract Class<? extends A> getAnnotation();

    protected abstract Class<? extends H> getHandler();

    protected String getPackagePath() {
        return "com.data";
    }

    public final void start() {
        log.debug("initialise annotation factory {}", this.getAnnotation());
        this.registerAllHandlers();
    }

    public void stop() {
        log.debug("Stopping Annotation factory {}", this.getAnnotation());
    }

    public void registerAllHandlers(){
        this.getAllHandlers().forEach(this::registerHandler);
    }

    protected Set<Class<? extends H>> getAllHandlers() {
        Reflections reflections = new Reflections(this.getPackagePath(), new Scanner[0]);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(this.getAnnotation());
        Stream var10000 = annotatedClasses.stream();
        Class var100001 = this.getHandler();
        var100001.getClass();
        return (Set)var10000.filter(var100001::isAssignableFrom).map((klass)->{
            return klass;
        }).collect(Collectors.toSet()) ;
    }

    protected abstract void registerHandler(Class<? extends H> var1);

    public String toString() {
        return "AnnotationFactory()";
    }
}
