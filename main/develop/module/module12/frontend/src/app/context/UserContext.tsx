// src/context/UserContext.tsx

'use client'; // Ensure this is a client component

import React, { createContext, useState, ReactNode, useContext } from 'react';

interface UserContextType {
    username: string | null;
    setUsername: (username: string | null) => void;
}

const UserContext = createContext<UserContextType>({
    username: null,
    setUsername: () => {},
});

export const UserProvider = ({ children }: { children: ReactNode }) => {
    const [username, setUsername] = useState<string | null>(null);

    return (
        <UserContext.Provider value={{ username, setUsername }}>
            {children}
        </UserContext.Provider>
    );
};

export const useUser = () => useContext(UserContext);
