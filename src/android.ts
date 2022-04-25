import { NativeModules, Platform } from 'react-native';

export interface Module {
  start(): void;
  stop(): void;
  setStartOnBoot(enabled: boolean): void;
}

export const Module = (Platform.OS === 'android') ? NativeModules.ReactNativeMoBackgroundExecution as Module : undefined;
