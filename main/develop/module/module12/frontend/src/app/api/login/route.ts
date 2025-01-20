// src/app/api/login/route.ts

import { NextResponse } from 'next/server';
import axios from 'axios';
import https from 'https';

export const config = {
    runtime: 'nodejs',
};

export async function POST(request: Request) {
    try {
        const { username, password } = await request.json();
        console.log(`Login attempt with username: ${username}`);

        // Create an HTTPS agent that ignores self-signed certificates (Development Only)
        const agent = new https.Agent({
            rejectUnauthorized: false,
        });

        const backendRes = await axios.post('http://localhost:8085/appi/auth/login', {
            username,
            password
        }, {
            httpsAgent: agent,
            headers: { 'Content-Type': 'application/json' },
            withCredentials: true, // Ensure cookies are included
        });

        console.log(`Backend response status: ${backendRes.status}`);

        if (backendRes.status === 200) {
            // Extract 'set-cookie' headers from backend response
            const setCookies = backendRes.headers['set-cookie'];

            if (setCookies) {
                // Forward 'Set-Cookie' headers to the client
                return NextResponse.json({ success: true }, {
                    status: 200,
                    headers: {
                        'Set-Cookie': setCookies.map(cookie => cookie.split(';')[0]).join('; ')
                        // Alternatively, you can forward the entire cookie string:
                        // 'Set-Cookie': setCookies,
                    },
                });
            }

            return NextResponse.json({ success: true }, { status: 200 });
        } else {
            return NextResponse.json({ error: 'Login failed' }, { status: backendRes.status });
        }
    } catch (error: any) {
        console.error('Error during login:', error);
        return NextResponse.json({ error: 'Internal Server Error' }, { status: 500 });
    }
}
