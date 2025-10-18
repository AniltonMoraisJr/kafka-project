package com.aniltonmoraisjr;

public class NotifyContext<T> {
  private final NotifyStrategy<T> strategy;

  public NotifyContext(NotifyStrategy<T> strategy) {
    this.strategy = strategy;
  }

  public void handleNotification(T object) {
    this.strategy.notify(object);
  }

  public void handleNotification(T object, String from, String to){
    this.strategy.notify(object, from, to);
  }
}
