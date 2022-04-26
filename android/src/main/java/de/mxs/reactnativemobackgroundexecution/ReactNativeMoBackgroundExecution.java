package de.mxs.reactnativemobackgroundexecution;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import javax.annotation.Nonnull;

public class ReactNativeMoBackgroundExecution extends ReactContextBaseJavaModule {

  ReactNativeMoBackgroundExecution(@Nonnull ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public @Nonnull
  String getName() {
    return "ReactNativeMoBackgroundExecution";
  }

  @SuppressWarnings("unused")
  @ReactMethod
  public void delay(int ms, Promise promise) {
    new Handler().postDelayed(() -> promise.resolve(null), ms);
  }

  @SuppressWarnings("unused")
  @ReactMethod
  public void start() {
    Log.i("XXX", "ReactNativeMoBackgroundExecution.start");
    BackgroundExecutionService.start(getReactApplicationContext());
  }

  @SuppressWarnings("unused")
  @ReactMethod
  public void stop() {
    Log.i("XXX", "ReactNativeMoBackgroundExecution.stop");
    BackgroundExecutionService.stop(getReactApplicationContext());
  }

  @SuppressWarnings("unused")
  @ReactMethod
  public void setStartOnBoot(boolean enabled) {
    SharedPreferences sharedPreferences = getReactApplicationContext().getSharedPreferences("de.mxs.reactnativemobackgroundexecution", Context.MODE_PRIVATE);
    sharedPreferences.edit().putBoolean("startOnBoot", enabled).apply();
    Log.i("XXX", "ReactNativeMoBackgroundExecution.setStartOnBoot enabled=" + enabled);
  }

}
