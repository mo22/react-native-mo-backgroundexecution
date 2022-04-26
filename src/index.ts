import { Platform } from "react-native";
import * as android from './android';

export class ReactNativeMoBackgroundExecution {
  public static startBackgroundExecution() {
    if (Platform.OS === 'android') {
      android.Module!.start();
    }
  }

  public static stopBackgroundExecution() {
    if (Platform.OS === 'android') {
      android.Module!.stop();
    }
  }

  public static enableStartOnBoot(enabled: boolean) {
    if (Platform.OS === 'android') {
      android.Module!.setStartOnBoot(enabled);
    }
  }

  public static onBootCompleted(callback: () => void) {
    android.Events!.addListener('ReactNativeMoBackgroundExecution', (event) => {
      if (event.type === 'bootCompleted') {
        callback();
      }
    });
  }

  public static async delay(ms: number): Promise<void> {
    if (Platform.OS === 'android') {
      await android.Module!.delay(ms);
    } else {
      await new Promise((resolve) => setTimeout(resolve, ms));
    }
  }

}
