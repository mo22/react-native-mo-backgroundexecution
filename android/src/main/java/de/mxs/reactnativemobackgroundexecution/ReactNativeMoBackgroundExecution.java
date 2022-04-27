package de.mxs.reactnativemobackgroundexecution;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Objects;

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
    BackgroundExecutionService.start(getReactApplicationContext());
  }

  @SuppressWarnings("unused")
  @ReactMethod
  public void stop() {
    BackgroundExecutionService.stop(getReactApplicationContext());
  }

  @SuppressWarnings("unused")
  @ReactMethod
  public void setStartOnBoot(boolean enabled) {
    SharedPreferences sharedPreferences = getReactApplicationContext().getSharedPreferences("de.mxs.reactnativemobackgroundexecution", Context.MODE_PRIVATE);
    sharedPreferences.edit().putBoolean("startOnBoot", enabled).apply();
  }

  @SuppressWarnings("unused")
  @ReactMethod
  public void test(Promise promise) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
      PowerManager powerManager = getReactApplicationContext().getSystemService(PowerManager.class);
      // PARTIAL_WAKE_LOCK ?
      PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "app:test");
      wakeLock.acquire(1000 * 60 * 60); // one hour?
    }
  }

  @SuppressWarnings("unused")
  @ReactMethod
  public void isIgnoringBatteryOptimizations(Promise promise) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
      PowerManager powerManager = getReactApplicationContext().getSystemService(PowerManager.class);
      promise.resolve(powerManager.isIgnoringBatteryOptimizations(getReactApplicationContext().getPackageName()));
    } else {
      promise.resolve(true);
    }
  }

  @SuppressLint("BatteryLife")
  @SuppressWarnings("unused")
  @ReactMethod
  public void requestIgnoringBatteryOptimizations(Promise promise) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
      Intent intent = new Intent();
      intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
      intent.setData(Uri.parse("package:" + getReactApplicationContext().getPackageName()));
      getReactApplicationContext().addActivityEventListener(new ActivityEventListener() {
        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
          if (requestCode == 1) {
            promise.resolve(true);
          }
        }
        @Override
        public void onNewIntent(Intent intent) {
        }
      });
      Activity activity = Objects.requireNonNull(getReactApplicationContext().getCurrentActivity());
      activity.startActivityForResult(intent, 1);
    } else {
      promise.resolve(true);
    }
  }

}
