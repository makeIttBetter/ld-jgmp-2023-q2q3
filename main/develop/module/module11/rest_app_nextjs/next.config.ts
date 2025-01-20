// next.config.ts

import type { NextConfig } from "next";

const nextConfig: NextConfig = {
    /* Other Next.js config options */
    // async headers() {
    //     return [
    //         {
    //             source: "/(.*)", // Apply to all routes
    //             headers: [
    //                 {
    //                     key: "Content-Security-Policy",
    //                     value: `
    //           default-src 'self';
    //           script-src 'self';
    //           style-src 'self' 'unsafe-inline';
    //           img-src 'self' data:;
    //           connect-src 'self' http://localhost:8085;
    //           font-src 'self';
    //           object-src 'none';
    //           frame-ancestors 'none';
    //           base-uri 'self';
    //         `.replace(/\n/g, " "), // Remove newlines for header compliance
    //                 },
    //                 {
    //                     key: "X-Content-Type-Options",
    //                     value: "nosniff",
    //                 },
    //                 {
    //                     key: "X-Frame-Options",
    //                     value: "DENY",
    //                 },
    //                 {
    //                     key: "Referrer-Policy",
    //                     value: "strict-origin-when-cross-origin",
    //                 },
    //                 {
    //                     key: "X-XSS-Protection",
    //                     value: "1; mode=block",
    //                 },
    //             ],
    //         },
    //     ];
    // },
};

export default nextConfig;
