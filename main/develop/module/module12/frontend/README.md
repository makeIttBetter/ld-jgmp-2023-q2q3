
# Frontend for Microservice Application

This project is the frontend part of a microservice-based application. It is built using **Next.js** and styled with **Tailwind CSS**. The project integrates with backend services to provide user authentication and profile management.

## Features

- **User Authentication**: Login functionality with secure cookie management.
- **Profile Management**: Display user information retrieved from the backend.
- **Dynamic Styling**: Utilizes Tailwind CSS for theming and responsiveness.
- **Context API**: Manages global user state with a reusable context.
- **API Integration**: Communicates with backend APIs for login and user information.
- **Environment Configuration**: Supports dynamic API configuration based on environment variables.

## Folder Structure

```
src/
├── app/
│   ├── api/
│   │   ├── login/          # Handles user login API
│   │   ├── userinfo/       # Handles user information retrieval API
│   ├── (site)/
│   │   ├── login/          # Login page
│   │   ├── profile/        # User profile page
│   │   ├── page.tsx        # Home page
│   │   ├── layout.tsx      # Application layout
│   ├── context/
│   │   ├── UserContext.tsx # Manages user state
│   ├── globals.css         # Global CSS configuration
├── config/
│   ├── index.ts            # API endpoint and app configuration
├── constants/
│   ├── enums.ts            # Enums for roles and endpoints
│   ├── interfaces.ts       # Interfaces for configurations
├── lib/
│   ├── apiClient.ts        # API helper methods
```

## Getting Started

### Prerequisites

- Node.js (version 16 or higher)
- npm or yarn

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <project-directory>
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Create a `.env.local` file in the root directory with the following variables:
   ```
   NEXT_PUBLIC_BACKEND_URL=http://localhost:8080
   ```

4. Run the development server:
   ```bash
   npm run dev
   ```

5. Open your browser and navigate to:
   ```
   http://localhost:3000
   ```

## Scripts

- `npm run dev`: Start the development server.
- `npm run build`: Build the application for production.
- `npm start`: Start the production server.

## API Endpoints

- **Login**: `/api/login` - Handles user login.
- **User Info**: `/api/userinfo` - Retrieves user information.

## Technologies Used

- **Next.js**: Framework for server-rendered React applications.
- **Tailwind CSS**: Utility-first CSS framework.
- **Axios**: HTTP client for API calls.
- **TypeScript**: Strongly-typed JavaScript.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

