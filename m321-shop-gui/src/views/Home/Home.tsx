import './Home.css';
import ProductItem from '../../components/ProductItem/ProductItem'
import Product from "../../models/Product";
import {useEffect, useState} from "react";
import ProductService from "../../services/ProductService";

export default function Home() {
    const [products, setProducts] = useState<Product[]>();

    useEffect(() => {
        ProductService.getProducts()
            .then(products => setProducts(products));
    }, [])
    return (
        <div className="ProductCatalog">
            {products?.map(product => <ProductItem key={product.id} product={product}/>)}
        </div>
    )
}