import React, { useContext, useEffect, useState } from 'react';
import { useAuth } from '../contexts/authenticationcontext/AuthenticationContext'; // Import useAuth
import axios from 'axios';

const UserProfile = () => {
    const { isAuthenticated, hasAnyAuthority } = useAuth(); // Use useAuth to access context properties
    const [userProfile, setUserProfile] = useState({
        firstName: '',
        lastName: '',
        birthDate: '',
        address: '',
        zipCode: '',
        rank: '',
        email: '',
        isActive: false,
        // Add other properties from your user object as needed
    });

    useEffect(() => {
        // When this component mounts, check if the user is authenticated and has the required authority
        if (!isAuthenticated) {
            // Handle authentication or redirect to the login page if needed
        } else {
            // Assuming you have an API endpoint to fetch the user's profile
            axios.get('/users/profile')
                .then(response => {
                    const userData = response.data;
                    setUserProfile({
                        firstName: userData.firstName,
                        lastName: userData.lastName,
                        birthDate: userData.birthDate,
                        address: userData.address,
                        zipCode: userData.zipCode.name, // Assuming ZipCode has a "name" property
                        rank: userData.rank.name, // Assuming Rank has a "name" property
                        email: userData.email,
                        isActive: userData.isActive,
                        // Set other properties from userData as needed
                    });
                })
                .catch(error => {
                    console.error('Error fetching user profile:', error);
                });
        }
    }, [isAuthenticated]);

    return (
        <div>
            {isAuthenticated && hasAnyAuthority(['CAN_RETRIEVE_PROFILE']) ? (
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
                        {/* Display other user profile information */}
                    </div>
                </div>
            ) : (
                <p>Loading...</p> // You can also redirect to the login page here if needed
            )}
        </div>
    );
}

export default UserProfile;
