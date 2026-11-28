package de.florianmichael.dietrichevents2;

public abstract class AbstractEvent<T> {
   public abstract void call(T obj);
}
