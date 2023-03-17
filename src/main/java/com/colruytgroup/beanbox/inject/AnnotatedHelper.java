package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.IntrospectException;
import com.colruytgroup.beanbox.util.ReflectionUtil;

import javax.enterprise.inject.spi.*;
import javax.enterprise.inject.spi.AnnotatedType;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

public final class AnnotatedHelper {

    private AnnotatedHelper() {
    }

    /**
     * Introspects all constructors to make Annotated objects for use in injection. Only the top layer needs
     * to be introspected as we cannot instantiate a type with the superclass constructors
     *
     * @param type The type for which to introspect constructors
     * @param <T>  The type of the base java class of the introspected class
     * @return A set of annotated constructors
     */
    private static <T> Set<AnnotatedConstructor<T>> introspectConstructors(AnnotatedType<T> type) {
        Set<AnnotatedConstructor<T>> constructors = new HashSet<>();

        for (Constructor<?> constructor : type.getJavaClass().getDeclaredConstructors()) {
            Set<Annotation> annotations = new HashSet<>(Arrays.asList(constructor.getDeclaredAnnotations()));
            AnnotatedConstructor<T> annotated = new AnnotatedConstructorImpl<>(type.getJavaClass(), annotations, ReflectionUtil.<Constructor<T>>cast(constructor), type);
            Set<AnnotatedParameter<T>> parameters = new HashSet<>();

            for (int i = 0; i < constructor.getParameters().length; i++) {
                Set<Annotation> parameterAnnotations = new HashSet<>(Arrays.asList(constructor.getParameters()[i].getDeclaredAnnotations()));

                parameters.add(new AnnotatedParameterImpl<>(constructor.getParameters()[i].getType(), type.getTypeClosure(), parameterAnnotations, i, annotated));
            }

            annotated.getParameters().addAll(parameters);
            constructors.add(annotated);
        }

        return constructors;
    }


    /**
     * Introspects all methods to make Annotated objects for use in injection. All methods will be introspected
     * including superclass level regardless of annotation. As methods might be overridden  with a different
     * annotation
     *
     * @param type The type for which to introspect methods
     * @param <T>  The type of the base java class of the introspected class
     * @return A set of annotated methods
     */
    private static <T> Set<AnnotatedMethod<? super T>> introspectMethods(AnnotatedType<T> type) {
        Set<AnnotatedMethod<? super T>> methods = new HashSet<>();

        for (Type t : type.getTypeClosure()) {
            Class<? super T> c = ReflectionUtil.cast(t);
            for (Method method : c.getDeclaredMethods()) {
                method.setAccessible(true);

                Set<Annotation> annotations = new HashSet<>(Arrays.asList(method.getDeclaredAnnotations()));
                AnnotatedMethod<T> annotated = new AnnotatedMethodImpl<>(method.getReturnType(), annotations, method, type);

                // Code has to be repeated because this needs to be Java 7 compatible which does not have java.lang.reflect.Executable
                Set<AnnotatedParameter<T>> methodParameters = new HashSet<>();

                for (int i = 0; i < method.getParameters().length; i++) {
                    Set<Annotation> parameterAnnotations = new HashSet<>(Arrays.asList(method.getParameters()[i].getDeclaredAnnotations()));
                    methodParameters.add(new AnnotatedParameterImpl<>(method.getParameters()[i].getType(), type.getTypeClosure(), parameterAnnotations, i, annotated));
                }
                annotated.getParameters().addAll(methodParameters);

                methods.add(annotated);
            }
        }

        return methods;
    }


    /**
     * Introspects all fields to make Annotated objects for use in injection. All fields will be introspected
     * including superclass level regardless of annotation.
     *
     * @param type The type for which to introspect fields
     * @param <T>  The type of the base java class of the introspected class
     * @return A set of annotated fields
     */
    private static <T> Set<AnnotatedField<? super T>> introspectFields(AnnotatedType<T> type) {
        Set<AnnotatedField<? super T>> fields = new HashSet<>();

        for (Type t : type.getTypeClosure()) {
            Class<? super T> c = ReflectionUtil.cast(t);
            for (Field field : c.getDeclaredFields()) {
                field.setAccessible(true);
                Set<Annotation> annotations = new HashSet<>(Arrays.asList(field.getAnnotations()));
                fields.add(new AnnotatedFieldImpl<>(field.getType(), annotations, field, type));
            }
        }

        return fields;
    }

    /**
     * Introspects  a class and its children and turns i into an AnnotatedType<T>
     *
     * @param type The type to be introspected
     * @param <T>  The generic type of the class
     * @return A new introspected AnnotatedType<T>
     */
    public static <T> AnnotatedType<T> introspect(Class<T> type) {
        AnnotatedTypeImpl<T> annotated = new AnnotatedTypeImpl<>(type, ReflectionUtil.resolveTypeClosure(type),
                ReflectionUtil.resolveAnnotations(type));

        annotated.getConstructors().addAll(Objects.requireNonNull(introspectConstructors(annotated)));
        annotated.getMethods().addAll(introspectMethods(annotated));
        annotated.getFields().addAll(introspectFields(annotated));

        return annotated;
    }

}
