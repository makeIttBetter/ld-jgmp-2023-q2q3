// src/config/index.ts

export const BACKEND_URL: string = process.env.NEXT_PUBLIC_BACKEND_URL || 'http://localhost:8080';

// Define API endpoints
export const API_ENDPOINTS = {
    LOGIN: `${BACKEND_URL}/auth/login`,
    USERINFO: `${BACKEND_URL}/users/userinfo`,
    // Add more endpoints as needed
};

// Define other constants
export const APP_NAME = 'YourAppName';
export const MAX_LOGIN_ATTEMPTS = 5;
// ... other constants
