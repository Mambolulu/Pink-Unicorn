import React, { useEffect, useState } from 'react';
import AxiosUtility from '../utility/AxiosUtility';
import {AxiosInstance} from 'axios';
import './Products.css'

interface Category {
    id: string;
    name: string;
}

interface Origin {
    id: string;
    country: string;
}

interface TeaOffering {
    id: string;
    variety: string;
    category: Category;
    origin: Origin;
    purchasePricePer100g: number;
    sellingPricePer100g: number;
    harvestDate: string;
    stockInGram: number;
}

const Products = () => {
    const [teaOfferings, setTeaOfferings] = useState<TeaOffering[]>([]);
    const api: AxiosInstance = AxiosUtility.getApi();

    useEffect(() => {
        const fetchTeaOfferings = async () => {
            try {
                const response = await api.get('/products');
                setTeaOfferings(response.data);
            } catch (error) {
                console.error('Error fetching tea offerings:', error);
            }
        };

        fetchTeaOfferings();
    }, []);

    return (
        <div className="container products-container">
            <h1 className="text-center my-4">Tea Offerings</h1>
            <div className="row">
                {teaOfferings.map((tea) => (
                    <div className="col-md-4 mb-4" key={tea.id}>
                        <div className="card tea-card">
                            {/* Ein Bild hier, falls verfügbar */}
                            <div className="card-body">
                                <h5 className="card-title">{tea.variety}</h5>
                                <p className="card-text">Category: {tea.category.name}</p>
                                <p className="card-text">Origin: {tea.origin.country}</p>
                                <p className="card-text">Price: ${tea.sellingPricePer100g} per 100g</p>
                                <p className="card-text">Harvest: {tea.harvestDate}</p>
                                <p className="card-text">Stock: {tea.stockInGram}g</p>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Products;
