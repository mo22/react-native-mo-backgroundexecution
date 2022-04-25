package de.mxs.reactnativemobackgroundexecution;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

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

}
