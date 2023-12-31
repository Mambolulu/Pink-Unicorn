import React, { useState } from 'react';
import axios from 'axios';
import { Navigate } from 'react-router-dom';
import './Register.css';
import AxiosUtility from '../utility/AxiosUtility';
import {AxiosInstance} from 'axios';

const Register = () => {
    const api: AxiosInstance = AxiosUtility.getApi();
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        birthDate: '',
        address: '',
        zipCodeId: '',
        email: '',
        password: '',
    });
    const [isRegistered, setIsRegistered] = useState(false);

    const handleChange = (e: { target: { name: any; value: any; }; }) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e: { preventDefault: () => void; }) => {
        e.preventDefault();
        // Anpassen der Daten für den Request
        const requestData = {
            ...formData,
            zipCode: { id: formData.zipCodeId }
        };
        // Senden der Registrierungsdaten an das Backend
        api.post('/users/register', requestData)
            .then(response => {
                setIsRegistered(true);
            })
            .catch(error => {
                console.error('Error during registration:', error);
            });
    };

    // if (isRegistered) {
    //     return <Navigate to="/login" />;
    // }

    return (
        <div className="register-container">
            <h1>Register</h1>
            <form onSubmit={handleSubmit} className="register-form">
                <input
                    type="text"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleChange}
                    placeholder="First Name"
                />
                <input
                    type="text"
                    name="lastName"
                    value={formData.lastName}
                    onChange={handleChange}
                    placeholder="Last Name"
                />
                <input
                    type="date"
                    name="birthDate"
                    value={formData.birthDate}
                    onChange={handleChange}
                    placeholder="Birth Date"
                />
                <input
                    type="text"
                    name="address"
                    value={formData.address}
                    onChange={handleChange}
                    placeholder="Address"
                />
                <input
                    type="text"
                    name="zipCodeId"
                    value={formData.zipCodeId}
                    onChange={handleChange}
                    placeholder="Zip Code ID"
                />
                <input
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    placeholder="Email"
                />
                <input
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    placeholder="Password"
                />
                <button type="submit">Register</button>
            </form>
        </div>
    );
};

export default Register;
