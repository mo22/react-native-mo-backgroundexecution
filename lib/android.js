import { NativeEventEmitter, NativeModules, Platform } from 'react-native';
export const Module = (Platform.OS === 'android') ? NativeModules.ReactNativeMoBackgroundExecution : undefined;
export const Events = Module ? new NativeEventEmitter(NativeModules.ReactNativeMoPushNotification) : undefined;
//# sourceMappingURL=android.js.map