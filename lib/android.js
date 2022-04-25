import { NativeModules, Platform } from 'react-native';
export const Module = (Platform.OS === 'android') ? NativeModules.ReactNativeMoBackgroundExecution : undefined;
//# sourceMappingURL=android.js.map