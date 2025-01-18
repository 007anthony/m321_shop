import './ProductDetail.css';
import {useContext, useEffect, useState} from "react";
import ProductService from "../../services/ProductService";
import {useParams} from "react-router";
import Product from "../../models/Product";
import {useSessionStorage} from "../../hooks/SessionStoragehook";
import CartService from "../../services/CartService";
import {AuthenticationContext} from "../../context/AuthenticationContext";
import {CartContext} from "../../context/CartContext";
import Cart from "../../models/Cart";

export default function ProductDetail() {
    const {id} = useParams();
    const [product, setProduct] = useState<Product>();
    const {token} = useContext(AuthenticationContext);
    const {cart, setCart} = useContext(CartContext);

    useEffect(() => {
        ProductService.getProduct(id as number)
            .then(product => setProduct(product));
    }, [])

    function addItem() {
        if(token && product) {
            CartService.addItem(token, product.id)
                .then((product: Product) => {
                    if(cart) {
                        const products = cart?.products;

                        products?.push(product);
                        setCart({...cart, products} as Cart)
                    }
                });
        }

    }

    return product?(<div className="ProductDetails">
            <div className="info-container">
                <h1>{product?.product}</h1>
                <p className="category">{product?.category.category}</p>
                <p>CHF {product?.price}.-</p>
                {token? <button type="button" onClick={() => addItem()}>Add to shopping cart</button>: ''}
            </div>
            <div className="picture-container">
                {product?.pictures.map(picture => <img key={picture.id} width={200} src={`http://localhost:8080/pictures/${picture.id}`}/>)}
            </div>

        </div>): (<div>Product not found</div>);
}