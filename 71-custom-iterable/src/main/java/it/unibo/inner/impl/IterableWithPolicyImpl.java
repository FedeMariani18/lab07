package it.unibo.inner.impl;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private T[] arr; 

    public IterableWithPolicyImpl(T[] arr) {
        this.arr = arr.clone();
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder();
        sb.append("[");
        for (var elem : this.arr) {
            if(elem == arr[arr.length - 1]){
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

    public class Iter implements Iterator<T>{

        private int index = 0;

        @Override
        public boolean hasNext() {
            return arr.length >= index + 1;
        }

        @Override
        public T next() {
            return arr[index++];
        }

    }

}
