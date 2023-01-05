import { EmitterSubscription } from 'react-native';
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
export declare const Module: Module | undefined;
export declare const Events: {
    addListener(eventType: 'ReactNativeMoBackgroundExecution', listener: (event: Event) => void): EmitterSubscription;
} | undefined;
