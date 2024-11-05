package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private T[] arr;
    private Predicate<T> predicate;

    public IterableWithPolicyImpl(T[] arr) {
        this(arr, new Predicate<T>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        });
    }

    public IterableWithPolicyImpl(T[] arr, Predicate<T> predicate) {
        this.arr = arr.clone();
        this.predicate = predicate;
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder();
        sb.append("[");
        for (var elem : this.arr) {
            if (elem == arr[arr.length - 1]) {
                sb.append(elem);
            } else {
                sb.append(elem + ", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.predicate = filter; 
    }

    public class Iter implements Iterator<T>{

        private int index = 0;

        @Override
        public boolean hasNext() {
            while (index + 1 <= arr.length && !predicate.test(arr[index])) {
                index++;
            }
            return index + 1 <= arr.length;
        }

        @Override
        public T next() {
            return arr[index++];
        }

    }

}
