import React, { useEffect, useState } from 'react';
import AxiosUtility from '../utility/AxiosUtility';
import {AxiosInstance} from 'axios';

// Define an interface for your tea offering
interface TeaOffering {
    id: string;
    variety: string;
}

const Products = () => {
    const [teaOfferings, setTeaOfferings] = useState<TeaOffering[]>([]); // Provide the type here
    const api: AxiosInstance = AxiosUtility.getApi();

    useEffect(() => {
        // Define an async function to fetch tea offerings
        const fetchTeaOfferings = async () => {
            try {
                const response = await api.get('/products');
                console.log(response);
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
                            {/*<img src={tea.image} className="card-img-top" alt={tea.variety} />*/}
                            <div className="card-body">
                                <h5 className="card-title">{tea.variety}</h5>
                                {/*<p className="card-text">Date: {tea.date}</p>*/}
                                {/*<p className="card-text">Price Range: ${tea.minPrice} - ${tea.maxPrice}</p>*/}
                                {/*<p className="card-text">Quantity: {tea.quantity}</p>*/}
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Products;
