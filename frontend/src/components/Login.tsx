import React, { useState } from 'react';
import axios from 'axios';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../contexts/authenticationcontext/AuthenticationContext';

const Login = () => {
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false);
    const [loginError, setLoginError] = useState<string>('');

    const { setPrincipal } = useAuth(); // Annahme: Die useAuth-Funktion bietet eine setPrincipal-Methode

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            // Hier wird eine Anfrage an das Backend gesendet, um den Benutzer zu authentifizieren
            const response = await axios.post('/api/login', { email, password });
            // Setzt den principal im Kontext und aktualisiert den Zustand
            setPrincipal(response.data);
            setIsLoggedIn(true);
        } catch (error) {
            if (axios.isAxiosError(error) && error.response) {
                setLoginError(error.response.data.message || 'Login fehlgeschlagen');
            } else {
                setLoginError('Ein unerwarteter Fehler ist aufgetreten.');
            }
        }
    };

    // Umleitung zur Profilseite bei erfolgreichem Login
    if (isLoggedIn) {
        return <Navigate to="/profile" />;
    }

    return (
        <div>
            <h1>Login</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="email">E-Mail:</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="password">Passwort:</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Einloggen</button>
                {loginError && <p className="error">{loginError}</p>}
            </form>
        </div>
    );
};

export default Login;
