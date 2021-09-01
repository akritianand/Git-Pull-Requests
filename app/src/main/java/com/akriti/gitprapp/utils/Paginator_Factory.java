// Generated by Dagger (https://dagger.dev).
package com.akriti.gitprapp.utils;

import dagger.internal.Factory;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class Paginator_Factory implements Factory<Paginator> {
  private final Provider<Integer> loadingTriggerThresholdProvider;

  public Paginator_Factory(Provider<Integer> loadingTriggerThresholdProvider) {
    this.loadingTriggerThresholdProvider = loadingTriggerThresholdProvider;
  }

  @Override
  public Paginator get() {
    return newInstance(loadingTriggerThresholdProvider.get());
  }

  public static Paginator_Factory create(Provider<Integer> loadingTriggerThresholdProvider) {
    return new Paginator_Factory(loadingTriggerThresholdProvider);
  }

  public static Paginator newInstance(int loadingTriggerThreshold) {
    return new Paginator(loadingTriggerThreshold);
  }
}