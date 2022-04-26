import { Platform } from "react-native";
import * as android from './android';
export class ReactNativeMoBackgroundExecution {
    static startBackgroundExecution() {
        if (Platform.OS === 'android') {
            android.Module.start();
        }
    }
    static stopBackgroundExecution() {
        if (Platform.OS === 'android') {
            android.Module.stop();
        }
    }
    static enableStartOnBoot(enabled) {
        if (Platform.OS === 'android') {
            android.Module.setStartOnBoot(enabled);
        }
    }
    static onBootCompleted(callback) {
        android.Events.addListener('ReactNativeMoBackgroundExecution', (event) => {
            if (event.type === 'bootCompleted') {
                callback();
            }
        });
    }
    static async delay(ms) {
        if (Platform.OS === 'android') {
            await android.Module.delay(ms);
        }
        else {
            await new Promise((resolve) => setTimeout(resolve, ms));
        }
    }
}
//# sourceMappingURL=index.js.map