package edu.nyu.pqs.graph;

public class Predicates{
 
  private static class andPredicate<T> implements Predicate<T> {
    private final Predicate<T> predicate1;
    private final Predicate<T> predicate2;
    private andPredicate(Predicate<T> predicate1, Predicate<T> predicate2) {
      this.predicate1 = predicate1;
      this.predicate2 = predicate2;
    }
    @Override
    public boolean accept(T item) {
      return (predicate1.accept(item) && predicate2.accept(item) );
    }
  }
  
  private static class notPredicate<T> implements Predicate<T> {
    private final Predicate<T> predicate;
    private notPredicate(Predicate<T> predicate) {
      this.predicate = predicate;
    }
    @Override
    public boolean accept(T item) {
      return !predicate.accept(item);
    }
  }
  
  private static class orPredicate<T> implements Predicate<T> {
    private final Predicate<T> predicate1;
    private final Predicate<T> predicate2;
    private orPredicate(Predicate<T> predicate1, Predicate<T> predicate2) {
      this.predicate1 = predicate1;
      this.predicate2 = predicate2;
    }
    @Override
    public boolean accept(T item) {
      return (predicate1.accept(item) || predicate2.accept(item) );
    }
  }
}
