// src/constants/interfaces.ts

export interface AppConfig {
    appName: string;
    maxLoginAttempts: number;
    // Add more configurations as needed
}

export const CONFIG: AppConfig = {
    appName: 'YourAppName',
    maxLoginAttempts: 5,
    // ... other configurations
};
