import React from 'react';
import { useAuth } from '../contexts/authenticationcontext/AuthenticationContext';
import './Navbar.css';
import {useNavigate} from 'react-router-dom';


const Navbar = () => {
    const { logout } = useAuth();
    const navigate = useNavigate(); // Hook initialisieren

    const handleLogout = async () => {
        await logout(); // Logout ausführen und warten, bis es abgeschlossen ist
        navigate('/login'); // Navigieren zur Login-Seite
    };

    return (
        <nav className="navbar">
            {/* Andere Navbar-Elemente */}
            <button onClick={handleLogout} className="logout-button">
                Logout
            </button>
        </nav>
    );
};

export default Navbar;