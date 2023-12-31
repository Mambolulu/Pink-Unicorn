import React from 'react';
import { useAuth } from '../contexts/authenticationcontext/AuthenticationContext';
import './Navbar.css';
import { useNavigate } from 'react-router-dom';

const Navbar = () => {
    const { logout } = useAuth();
    const navigate = useNavigate(); // Hook initialisieren

    const handleLogout = async () => {
        await logout(); // Logout ausführen und warten, bis es abgeschlossen ist
        navigate('/login'); // Navigieren zur Login-Seite
    };

    const handleRegister = () => {
        navigate('/register'); // Navigieren zur Registrierungsseite
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
