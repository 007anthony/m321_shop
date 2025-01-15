import './Home.css';
import ProductItem from '../../components/ProductItem'
import Product from "../../models/Product";

export default function Home() {
    const products: Product[] = [
        {
            id: 1,
            product: "React",
            category: "Category",
            price: 12
        },
        {
            id: 1,
            product: "React",
            category: "Category",
            price: 12
        },
        {
            id: 1,
            product: "React",
            category: "Category",
            price: 12
        }
    ]
    return (
        <div className="ProductCatalog">
            {products.map(product => <ProductItem product={product}/>)}
        </div>
    )
}