package edu.nyu.pqs.graph;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class FilterIterator<T> implements Iterator<T> {

  private final Iterator<T> iterator;
  private Predicate<T> predicate;
  private T nextItem;
  private boolean nextIsValid;
  
  public FilterIterator(Iterator<T> iterator, Predicate<T> predicate) {
    this.iterator = iterator;
    this.predicate = predicate;
  }
  
  @Override
  public boolean hasNext() {
    if (nextIsValid) {
      return true;
    }
    while (iterator.hasNext()) {
      nextItem = iterator.next();
      if (predicate.accept(nextItem)) {
        nextIsValid = true;
        return true;
      }
    }
    return false;
  }
  
  @Override 
  public T next() {
    if (nextIsValid) {
      nextIsValid = false;
      T value = nextItem;
      nextItem = null;
      return value;
    }
    if (hasNext()) {
      return next();
    }
    throw new NoSuchElementException("Element does not exist.");
  }

}
