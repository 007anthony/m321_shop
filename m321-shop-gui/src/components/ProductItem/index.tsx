import './ProductItem.css';
import react from '../../assets/react.svg';
import {Link} from "react-router";
import Product from "../../models/Product";

interface IProps {
    product: Product
}

export default function ProductItem({product}: IProps) {
    return (
        <Link to={`/product/${product.id}`} className="ProductItem">
            <img src={react}/>
            <span className="category">category</span>
            <h2>Product 1</h2>
            <span className="price">CHF 12.-</span>
        </Link>
    )
}