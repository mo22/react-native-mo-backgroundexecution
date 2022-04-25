export interface Module {
    start(): void;
    stop(): void;
    setStartOnBoot(enabled: boolean): void;
}
export declare const Module: Module | undefined;
