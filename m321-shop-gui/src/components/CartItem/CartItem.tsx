import './CartItem.css';
import Product from "../../models/Product";

interface IProps {
    product: Product
    removeItem: (productId: number) => void
}

export default function CartItem({product, removeItem}: IProps) {

    return (
        <div className="CartItem">
            <div className="picture">
                <img width={50} src={`http://localhost:8080/pictures/${product.pictures[0].id}`} />
            </div>
            <span>{product.product}</span>
            <span>CHF {product.price}</span>
            <button onClick={() => removeItem(product.id)}>X</button>
        </div>
    )
}