// src/app/api/userinfo/route.ts

import { NextResponse } from 'next/server';
import axios from 'axios';
import https from 'https';

export const config = {
    runtime: 'nodejs',
};

export async function GET(request: Request) {
    try {
        const agent = new https.Agent({
            rejectUnauthorized: false,
        });

        // Extract 'Cookie' header from incoming request
        const cookies = request.headers.get('cookie');

        const backendRes = await axios.get('https://localhost:8085/appi/auth/userinfo', {
            httpsAgent: agent,
            headers: {
                'Accept': 'application/json',
                'Cookie': cookies || '',
            },
            withCredentials: true,
        });

        if (backendRes.status === 200) {
            return NextResponse.json(backendRes.data, { status: 200 });
        } else {
            return NextResponse.json({ error: 'Failed to fetch user info' }, { status: backendRes.status });
        }
    } catch (error: any) {
        console.error('Error fetching user info:', error);
        return NextResponse.json({ error: 'Internal Server Error' }, { status: 500 });
    }
}
