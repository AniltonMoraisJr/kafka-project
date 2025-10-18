package com.aniltonmoraisjr;

public interface NotifyStrategy<T> {
  void notify(T object);
  void notify(T object, String from, String to);
}
