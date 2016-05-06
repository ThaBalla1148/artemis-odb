package com.artemis.annotations;

import com.artemis.EntitySubscription.SubscriptionListener;
import com.artemis.utils.IntBag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * WHen present on component, guarantees that the component
 * is still present during {@link SubscriptionListener#removed(IntBag)} -
 * regardless of how it was removed.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface  DelayedComponentDeletion {}
