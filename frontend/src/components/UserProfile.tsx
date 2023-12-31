import React, { useEffect, useState } from 'react';
import { useAuth } from '../contexts/authenticationcontext/AuthenticationContext'; // Import useAuth
import axios from 'axios';
import { Navigate } from 'react-router-dom';

const UserProfile = () => {
    const { principal, hasAnyAuthority } = useAuth();
    const [userProfile, setUserProfile] = useState({
        firstName: '',
        lastName: '',
        birthDate: '',
        address: '',
        zipCode: '',
        rank: '',
        email: '',
        isActive: false,
        // Weitere Eigenschaften können hier hinzugefügt werden
    });

    useEffect(() => {
        if (principal && hasAnyAuthority(['CAN_RETRIEVE_PROFILE'])) {
            axios.get('/users/profile')
                .then(response => {
                    // Angenommen, die Antwortdaten enthalten die Benutzerprofildaten
                    const userData = response.data;
                    setUserProfile({
                        firstName: userData.firstName,
                        lastName: userData.lastName,
                        birthDate: userData.birthDate,
                        address: userData.address,
                        zipCode: userData.zipCode?.name, // Geprüft auf Existenz der Eigenschaft
                        rank: userData.rank?.name, // Geprüft auf Existenz der Eigenschaft
                        email: userData.email,
                        isActive: userData.isActive,
                    });
                })
                .catch(error => {
                    console.error('Error fetching user profile:', error);
                });
        }
    }, [principal, hasAnyAuthority]);

    if (!principal) {
        return <Navigate to="/login" />;
    }

    return (
        <div>
            <h1>User Profile</h1>
            <div>
                <p>First Name: {userProfile.firstName}</p>
                <p>Last Name: {userProfile.lastName}</p>
                <p>Birth Date: {userProfile.birthDate}</p>
                <p>Address: {userProfile.address}</p>
                <p>Zip Code: {userProfile.zipCode}</p>
                <p>Rank: {userProfile.rank}</p>
                <p>Email: {userProfile.email}</p>
                <p>Active: {userProfile.isActive ? 'Yes' : 'No'}</p>
            </div>
        </div>
    );
}

export default UserProfile;
