import { Platform } from "react-native";
import * as android from './android';
export class BackgroundExecution {
    /**
     * native ios functions. use with caution
     */
    static ios = undefined;
    /**
     * native android functions. use with caution
     */
    static android = android;
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
    static async runWithWakelock(callback) {
        if (Platform.OS === 'android') {
            const lockID = await android.Module.createWakeLock({});
            try {
                return await callback();
            }
            finally {
                await android.Module.releaseWakeLock(lockID);
            }
        }
        else {
            return await callback();
        }
    }
}
//# sourceMappingURL=index.js.map