package edu.nyu.pqs.graph;

public interface Predicate<T> {
  boolean accept(T item);
}
