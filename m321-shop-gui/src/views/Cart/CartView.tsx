import {useContext, useEffect, useState} from "react";
import CartService from "../../services/CartService";
import CartItem from "../../components/CartItem/CartItem";
import {AuthenticationContext} from "../../context/AuthenticationContext";
import {CartContext} from "../../context/CartContext";

export default function CartView() {
    const {cart, setCart} = useContext(CartContext);
    const {token} = useContext(AuthenticationContext);

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
                {cart? cart.products.map(product => <CartItem key={product.id} removeItem={removeItem} product={product}/>): ''}
            </div>
        </>
    )
}