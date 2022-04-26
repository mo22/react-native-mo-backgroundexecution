package de.mxs.reactnativemobackgroundexecution;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class StartReactOnBootReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    Log.i("XXX", "StartReactOnBootReceiver");
    if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
      Log.i("XXX", "StartReactOnBootReceiver ACTION_BOOT_COMPLETED");

      SharedPreferences sharedPreferences = context.getSharedPreferences("de.mxs.reactnativemobackgroundexecution", Context.MODE_PRIVATE);
      if (!sharedPreferences.getBoolean("startOnBoot", false)) {
        return;
      }
      Log.i("XXX", "StartReactOnBootReceiver go start");

//      ReactInstanceEventListener sender = reactContext -> {
//        Log.i("XXX", "StartReactOnBootReceiver send event");
//        WritableMap args = Arguments.createMap();
//        args.putString("type", "onBoot");
//        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("ReactNativeMoBackgroundExecution", args);
//      };

      Handler handler = new Handler(Looper.getMainLooper());
      handler.post(() -> {
        ReactInstanceManager reactInstanceManager = ((ReactApplication) context.getApplicationContext()).getReactNativeHost().getReactInstanceManager();
        ReactContext reactContext = reactInstanceManager.getCurrentReactContext();
        if (reactContext == null) {
//          reactInstanceManager.addReactInstanceEventListener(sender);
          if (!reactInstanceManager.hasStartedCreatingInitialContext()) {
            Log.i("XXX", "StartReactOnBootReceiver createReactContextInBackground");
            reactInstanceManager.createReactContextInBackground();
          }
//        } else {
//          sender.onReactContextInitialized(reactContext);
        }
      });

    }
  }
}
