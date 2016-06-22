package edu.nyu.pqs.stopwatch.impl;

import java.lang.StringBuilder;
import java.util.List;
import edu.nyu.pqs.stopwatch.api.Stopwatch;
/**
 * @author maryeileenfagan
 *Creates a class that implements the stopwatch interface.
 *Stores the state of the stopwatch as an enum type of of reset, stopped, or running.
 *Able to start, lap, reset, and stop stopwatch, as well as return a list of laptimes.
 */
public class StopwatchImp implements Stopwatch{
  private final String id;
  private List<Long> lapTimes;
  /**
 * startTime is initiated to zero and changest to current time in milliseconds once watch is started.
 */
  private long startTime = 0;
  private Object lock = new Object();
  /**
 * States of stopwatch are stored as enum types and have getter and setter functions so that they can remain private.
 *Reset state indicates that the watch does not have any times and is not running. It has either been reset or not been started yet.
 *Stopped state indicates that watch was running, but is now paused.
 *Running indicates that watch is running and accumulating time for a lap.
 */
private enum State{
    RESET, STOPPED, RUNNING
  }
  private State state;
  /**
   * Sets object id to parameter id and calls the set state function to reset.
   * @param String id of stopwatch
 */
protected StopwatchImp(String id) {
    this.id = id;
    setState(State.RESET);
  }
  private State getState() {
	return this.state;
  }
  private void setState(State state) {
	this.state = state;
  }
  private long getTime() {
    return System.currentTimeMillis();
  }
  public String getId() {
    return this.id;
  }
  public void start() throws IllegalStateException {
    synchronized (lock) {
	  if (getState() == State.RUNNING) {
        throw new IllegalStateException("Error: Stopwatch is already running.");
      } else if (getState() == State.RESET) {
        startTime = getTime();
      } else {
        lapTimes.remove(lapTimes.size()-1);
      }
      setState(State.RUNNING);	
    }
  }
  public void lap() throws IllegalStateException {
    synchronized(lock) {
      if (getState() != State.RUNNING) {
	    throw new IllegalStateException("Error: Cannot record lap because stopwatch is not running.");
      } else {
    	long currentTime = getTime();
        lapTimes.add(currentTime - startTime);
        startTime = currentTime;
      }
    }
  }
    
  public void stop() throws IllegalStateException {	
    synchronized (lock) {
	  if (getState() != State.RUNNING) {
        throw new IllegalStateException("Error: Cannot record lap because stopwatch is not running.");
      } else {
        lapTimes.add(getTime() - startTime);
        setState(State.STOPPED);
      }
    }
  }
  public void reset() {
	synchronized(lock) {
      if (getState() != State.RUNNING) {	
        stop();
      }
      startTime = 0;
      lapTimes.clear();
      setState(State.RESET);
	}
  }
  public List<Long> getLapTimes() {
    synchronized (lock) {
      return lapTimes;
    }
  }
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("ID: ");
    result.append(id);
    result.append("\n");
    if (!lapTimes.isEmpty()) {
      result.append("lap times");
      for (int i=0; i<lapTimes.size(); i++) {
    	result.append("\n");
        result.append(lapTimes.get(i));
      }
    }
    return result.toString();
  }
  @Override 
  public boolean equals(Object obj) {
    if (obj==this) {
      return true;
    }
    if (!(obj instanceof Stopwatch)) {
      return false;
    }
    Stopwatch watch = (StopwatchImp) obj;
    return this.getId().equals(watch.getId());
  }
  @Override
  public int hashCode() {
    int hash = 5;
    hash = 7 * hash + this.id.hashCode();
    return hash; 
  }
}