import {createContext} from "react";
import Cart from "../models/Cart";

export const CartContext = createContext<{
    cart: Cart | undefined,
    setCart: (cart: Cart) => void
}>({cart: undefined, setCart: () => {}});