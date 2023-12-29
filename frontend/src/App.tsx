import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom'; // Import Navigate
import AuthenticationContextProvider, { useAuth } from './contexts/authenticationcontext/AuthenticationContext'; // Import useAuth
import Products from './components/Products';
import UserProfile from './components/UserProfile';
import Login from './components/Login';
import Register from './components/Register';
import Home from './pages/Home';

function App() {
    const { isAuthenticated } = useAuth(); // Get isAuthenticated from the AuthenticationContext
    console.log('Is Authenticated:', isAuthenticated);

    return (
        <Router>
            <AuthenticationContextProvider> {/* Wrap your entire app with the context provider */}
                <Routes>
                    {/* Public routes */}
                    <Route path="users/login" element={isAuthenticated ? <Navigate to="/" /> : <Login />} /> {/* Redirect to home if authenticated */}
                    <Route path="users/register" element={isAuthenticated ? <Navigate to="/" /> : <Register />} /> {/* Redirect to home if authenticated */}

                    {/* Protected routes */}
                    {isAuthenticated && (
                        <Route
                            path="/"
                            element={
                                <Routes>
                                    <Route index element={<Products />} />
                                    <Route path="/profile" element={<UserProfile />} />
                                    {/* Add more protected routes as needed */}
                                </Routes>
                            }
                        />
                    )}

                    {/* Default route (you can replace this with your Home component) */}
                    <Route path="/" element={<Home />} />
                </Routes>
            </AuthenticationContextProvider>
        </Router>
    );
}

export default App;
