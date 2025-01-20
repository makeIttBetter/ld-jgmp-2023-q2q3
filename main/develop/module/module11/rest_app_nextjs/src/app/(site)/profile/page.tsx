// src/app/(site)/profile/page.tsx
'use client';

import {useEffect, useState} from 'react';
import {useRouter} from 'next/navigation';
import {useUser} from '@/app/context/UserContext';
import {apiGet} from '@/lib/apiClient';

interface UserInfo {
    username: string;
    info: string;
}

export default function ProfilePage() {
    const [userInfo, setUserInfo] = useState<UserInfo | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);
    const router = useRouter();
    const {username} = useUser();

    useEffect(() => {
        const fetchUserInfo = async () => {
            try {
                if (!username) {
                    setError('No username found. Please log in.');
                    setLoading(false);
                    return;
                }

                const data = await apiGet('USERINFO', `username=${username}`);
                setUserInfo(data);
            } catch (err: any) {
                console.error('Error fetching user info:', err);
                if (err.message.includes('401')) {
                    setError('Not authorized. Please log in.');
                } else {
                    setError('Failed to fetch user info.');
                }
            } finally {
                setLoading(false);
            }
        };

        fetchUserInfo();
    }, [username, router]);

    if (loading) return <div>Loading...</div>;
    if (error) return <div><h1>Error: {error}</h1></div>;

    return (
        <div style={{maxWidth: '300px', margin: '0 auto', marginTop: '100px'}}>
            <h1>User Profile</h1>
            <p><strong>Username:</strong> {userInfo?.username}</p>
            <p><strong>Info:</strong> {userInfo?.info}</p>
        </div>
    );
}
