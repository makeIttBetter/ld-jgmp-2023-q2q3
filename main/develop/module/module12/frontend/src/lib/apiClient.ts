// src/lib/apiClient.ts
import {API_ENDPOINTS} from '@/config';

/**
 * Makes a GET request to the specified API endpoint.
 * @param endpoint Key of the API endpoint from API_ENDPOINTS.
 * @param params
 * @returns The JSON response from the server.
 */
export async function apiGet(endpoint: keyof typeof API_ENDPOINTS, params?: string) {
    const url = API_ENDPOINTS[endpoint] + (params ? `?${params}` : '');
    const res = await fetch(url, {
        method: 'GET',
        credentials: 'include', // Important: includes cookies
        headers: {
            'Accept': 'application/json',
        },
    });
    if (!res.ok) {
        const errorData = await res.json().catch(() => ({}));
        throw new Error(errorData.error || `GET ${url} failed with status ${res.status}`);
    }
    return res.json();
}

/**
 * Makes a POST request to the specified API endpoint.
 * @param endpoint Key of the API endpoint from API_ENDPOINTS.
 * @param data The payload to send in the request body.
 * @returns The JSON response from the server.
 */
export async function apiPost(endpoint: keyof typeof API_ENDPOINTS, data: any) {
    const url = API_ENDPOINTS[endpoint];
    const res = await fetch(url, {
        method: 'POST',
        credentials: 'include', // includes cookies
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data),
    });
    if (!res.ok) {
        const errorData = await res.json().catch(() => ({}));
        throw new Error(errorData.error || `POST ${url} failed with status ${res.status}`);
    }
    return res.json().catch(() => ({}));
}


