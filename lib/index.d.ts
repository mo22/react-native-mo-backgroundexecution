import * as android from './android';
export declare class BackgroundExecution {
    /**
     * native ios functions. use with caution
     */
    static readonly ios: undefined;
    /**
     * native android functions. use with caution
     */
    static readonly android: typeof android;
    static startBackgroundExecution(): void;
    static stopBackgroundExecution(): void;
    static enableStartOnBoot(enabled: boolean): void;
    static onBootCompleted(callback: () => void): void;
    static delay(ms: number): Promise<void>;
    static runWithWakelock<T>(callback: () => Promise<T>): Promise<T>;
}
