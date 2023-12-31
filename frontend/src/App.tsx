import React from 'react';
import {HashRouter as Router, Route, Routes} from 'react-router-dom';
import AuthenticationContext from "./contexts/authenticationcontext/AuthenticationContext";
import Navbar from './components/Navbar';
import {Protected} from "./routes/Protected";
import Products from "./components/Products";
import Login from "./components/Login";

function App() {
    return (
        <Router>
            <AuthenticationContext>
                <Navbar />
                <Routes>
                    <Route element={<Protected authoritiesToGrantAccess={["CAN_RETRIEVE_PRODUCTS"]}/>}>
                        <Route path="/" element={<Products/>}/>
                    </Route>
                    <Route path="/login" element={<Login/>}/>
                </Routes>
            </AuthenticationContext>
        </Router>
    );
}

export default App;