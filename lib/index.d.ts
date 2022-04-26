export declare class ReactNativeMoBackgroundExecution {
    static startBackgroundExecution(): void;
    static stopBackgroundExecution(): void;
    static enableStartOnBoot(enabled: boolean): void;
    static onBootCompleted(callback: () => void): void;
    static delay(ms: number): Promise<void>;
}
