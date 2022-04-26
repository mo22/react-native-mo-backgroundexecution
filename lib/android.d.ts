import { EmitterSubscription } from 'react-native';
export interface Module {
    start(): void;
    stop(): void;
    setStartOnBoot(enabled: boolean): void;
}
export declare type Event = {
    type: 'bootCompleted';
};
export declare const Module: Module | undefined;
export declare const Events: {
    addListener(eventType: 'ReactNativeMoBackgroundExecution', listener: (event: Event) => void): EmitterSubscription;
} | undefined;
