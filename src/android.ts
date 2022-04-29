import { EmitterSubscription, NativeEventEmitter, NativeModules, Platform } from 'react-native';

export interface Module {
  start(): void;
  stop(): void;
  setStartOnBoot(enabled: boolean): void;
  delay(ms: number): Promise<void>;
  isIgnoringBatteryOptimizations(): Promise<boolean>;
  requestIgnoringBatteryOptimizations(): Promise<boolean>;
  createWakeLock(args: {}): Promise<string>;
  releaseWakeLock(id: string): Promise<void>;
}

export type Event = {
  type: 'bootCompleted';
};

export const Module = (Platform.OS === 'android') ? NativeModules.ReactNativeMoBackgroundExecution as Module : undefined;

export const Events = Module ? new NativeEventEmitter(NativeModules.ReactNativeMoPushNotification) as {
  addListener(eventType: 'ReactNativeMoBackgroundExecution', listener: (event: Event) => void): EmitterSubscription;
} : undefined;
