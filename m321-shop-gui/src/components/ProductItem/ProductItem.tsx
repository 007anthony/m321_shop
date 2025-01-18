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
            <img src={`http://localhost:8080/pictures/${product.pictures[0].id}`}/>
            <span className="category">{product.category.category}</span>
            <h2>{product.product}</h2>
            <span className="price">CHF {product.price}.-</span>
        </Link>
    )
}