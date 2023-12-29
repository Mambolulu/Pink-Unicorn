import React, { useEffect, useState } from 'react';
import axios from 'axios';

// Define an interface for your tea offering
interface TeaOffering {
    id: string;
    date: string;
    minPrice: number;
    maxPrice: number;
    quantity: number;
    name: string;
    image: string;
}

const Products = () => {
    const [teaOfferings, setTeaOfferings] = useState<TeaOffering[]>([]); // Provide the type here

    useEffect(() => {
        // Define an async function to fetch tea offerings
        const fetchTeaOfferings = async () => {
            try {
                const response = await axios.get('/products');
                setTeaOfferings(response.data); // Assuming the response is an array of tea offerings
            } catch (error) {
                console.error('Error fetching tea offerings:', error);
            }
        };

        // Call the async function to fetch tea offerings when the component mounts
        fetchTeaOfferings();
    }, []);

    return (
        <div>
            <h1>Tea Offerings</h1>
            <div className="row">
                {teaOfferings.map((tea) => (
                    <div className="col-md-4" key={tea.id}>
                        {/* Use Bootstrap Card component to display tea offering */}
                        <div className="card">
                            <img src={tea.image} className="card-img-top" alt={tea.name} />
                            <div className="card-body">
                                <h5 className="card-title">{tea.name}</h5>
                                <p className="card-text">Date: {tea.date}</p>
                                <p className="card-text">Price Range: ${tea.minPrice} - ${tea.maxPrice}</p>
                                <p className="card-text">Quantity: {tea.quantity}</p>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Products;
