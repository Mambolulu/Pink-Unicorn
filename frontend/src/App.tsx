import React from 'react';
import { HashRouter as Router, Routes, Route } from 'react-router-dom';
import AuthenticationContextProvider from './contexts/authenticationcontext/AuthenticationContext'; // Import des Context Providers
import { Protected } from './routes/Protected';
import Products from './components/Products';
import Login from './components/Login';     // Import der Login-Komponente
import Register from './components/Register'; // Import der Register-Komponente
import UserProfile from './components/UserProfile'; // Import der UserProfile-Komponente
import Home from './pages/Home'; // Import der Home-Komponente

function App() {
    return (
        <Router>
            <AuthenticationContextProvider> {/* Verwendung des Context Providers */}
                <Routes>
                    {/* Geschützte Routen */}
                    <Route element={<Protected authoritiesToGrantAccess={["CAN_RETRIEVE_PRODUCTS"]}/>}>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/products" element={<Products/>}/>
                        <Route path="/profile" element={<UserProfile/>}/>
                    </Route>

                    {/* Öffentliche Routen */}
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/register" element={<Register/>}/>

                    {/* Fallback für nicht gefundene Routen */}
                    <Route path="*" element={<h1>Page Not Found</h1>}/>
                </Routes>
            </AuthenticationContextProvider>
        </Router>
    );
}

export default App;
