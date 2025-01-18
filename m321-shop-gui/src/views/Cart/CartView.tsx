import {useEffect, useState} from "react";
import CartService from "../../services/CartService";
import {useSessionStorage} from "../../../hooks/SessionStoragehook";
import Cart from "../../models/Cart";
import CartItem from "../../components/CartItem/CartItem";

export default function CartView() {
    const [cart, setCart] = useState<Cart>();
    const [token] = useSessionStorage("token");

    useEffect(() => {
        if(token) {
            CartService.getCart(token)
                .then(cart => setCart(cart));
        }
    }, [token]);

    function removeItem(productId: number) {
        if(token) {
            CartService.removeItem(token, productId)
                .then(() => {
                    if(cart) {
                        const products = cart.products.filter(product => productId !== product.id)
                        setCart({...cart, products});
                    }
                })
        }
    }

    return (
        <>
            <h1>Your Cart</h1>
            <div>
                {cart? cart.products.map(product => <CartItem removeItem={removeItem} product={product}/>): ''}
            </div>
        </>
    )
}