import { NativeModules, Platform } from 'react-native';
export const Module = (Platform.OS === 'android') ? NativeModules.ReactNativeMoPushNotification : undefined;
//# sourceMappingURL=android.js.map