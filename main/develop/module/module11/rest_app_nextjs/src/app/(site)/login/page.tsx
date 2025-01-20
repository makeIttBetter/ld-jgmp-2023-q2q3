// src/app/(site)/login/page.tsx
'use client';

import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
import { useUser } from '@/app/context/UserContext';
import { apiPost } from '@/lib/apiClient';

export default function LoginPage() {
    const [usernameInput, setUsernameInput] = useState('');
    const [password, setPassword] = useState('');
    const router = useRouter();
    const { setUsername } = useUser();

    async function handleLogin(e: React.FormEvent) {
        e.preventDefault();
        try {
            const data = await apiPost('LOGIN', {
                username: usernameInput,
                password
            });
            console.log('Login successful:', data);
            // If successful, the cookie is now set in the browser.
            setUsername(usernameInput);
            router.push('/profile');
        } catch (error: any) {
            alert(error.message || 'Login failed');
        }
    }

    return (
        <div style={{ maxWidth: '300px', margin: '0 auto', marginTop: '100px' }}>
            <h1>Login</h1>
            <form onSubmit={handleLogin}>
                <input
                    type="text"
                    placeholder="Username"
                    value={usernameInput}
                    onChange={(e) => setUsernameInput(e.target.value)}
                    style={{ width: '100%', marginBottom: '10px' }}
                    required
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    style={{ width: '100%', marginBottom: '10px' }}
                    required
                />
                <button type="submit" style={{ width: '100%' }}>Login</button>
            </form>
        </div>
    );
}
