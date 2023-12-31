import React from 'react';
import { useAuth } from '../contexts/authenticationcontext/AuthenticationContext';
import './Navbar.css';
import { useNavigate } from 'react-router-dom';

const Navbar = () => {
    const { logout } = useAuth();
    const navigate = useNavigate();

    const handleLogout = async () => {
        await logout();
        navigate('/login');
    };

    const handleRegister = () => {
        navigate('/register');
    };

    return (
        <nav className="navbar">
            {/* Andere Navbar-Elemente */}
            <button onClick={handleLogout} className="navbar-button">
                Logout
            </button>
            <button onClick={handleRegister} className="navbar-button">
                Register
            </button>
        </nav>
    );
};

export default Navbar;
