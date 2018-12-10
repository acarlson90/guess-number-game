package com.aaroncarlson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

/*
 * @ symbol (example: @interface) denotes an annotation type definition, which means it's not really an interface but
 * rather a new annotation type
 * @Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD}) - Indicates the context for which the annotation
 * type is applicable. For this particular annotation we are saying @MaximumNumber can be added to fields, parameters and methods
 * @Retention(RetentionPolicy.RUNTIME) - indicates how long annotations with the annotated type are to be retained
 * @Qualifier - used to annotate other custom annotations which can in turn be used as qualifiers
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface MinimumNumber {
}
