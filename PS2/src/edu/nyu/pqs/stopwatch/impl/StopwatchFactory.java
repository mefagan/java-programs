package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.Stopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for Stopwatch objects.
 * It maintains references to all created Stopwatch objects and provides a
 * convenient method for getting a list of those objects.
 *
 */
public class StopwatchFactory {
  /**
 * Creates new array list of stop watches 
 */
  private static List<Stopwatch> stopWatchList = new ArrayList<Stopwatch>();
  private static Object lock = new Object();
  
  /**
   * Creates and returns a new Stopwatch object
   * @param id The identifier of the new object
   * @return The new Stopwatch object
   * @throws IllegalArgumentException if <code>id</code> is empty, null, or
   *     already taken.
   */
  public static Stopwatch getStopwatch(String id) throws IllegalArgumentException{
    if (id == null) {
      throw new IllegalArgumentException("Error: ID is null.");
    }
    if (id == "") {
      throw new IllegalArgumentException("Error: ID is empty.");
    } 
    for (Stopwatch entry: stopWatchList) {
      if (id.equals(entry.getId())) {
        throw new IllegalArgumentException("Error: ID already exists.");    	  
      }
    }
    synchronized(lock) {
      StopwatchImp watch = new StopwatchImp(id);
      stopWatchList.add(watch);
      return watch;
    }
  }
  
  /**
   * Returns a list of all created stopwatches
   * @return a List of al creates Stopwatch objects.  Returns an empty
   * list if no Stopwatches have been created.
   */
  public static List<Stopwatch> getStopwatches() {
    return stopWatchList;
  }
}