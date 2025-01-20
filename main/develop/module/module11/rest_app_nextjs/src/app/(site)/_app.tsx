// src/app/(site)/_app.tsx
import { AppProps } from 'next/app';
import Head from 'next/head';

function MyApp({ Component, pageProps }: AppProps) {
    return (
        <>
            <Head>
                {/* Add a strict CSP header (in a real setup, configure via server headers) */}
                <meta httpEquiv="Content-Security-Policy" content="default-src 'self'; script-src 'self'; object-src 'none';" />
            </Head>
            <Component {...pageProps} />
        </>
    );
}

export default MyApp;
