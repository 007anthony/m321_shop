import {useContext, useEffect, useState} from 'react'
import './App.css'
import cartImg from './assets/cart.svg';
import Home from './views/Home/Home'
import {BrowserRouter, Link, Route, Routes} from "react-router";
import Login from "./views/Login/Login";
import Signup from "./views/Signup/Signup";
import UserService from "./services/UserService";
import User from "./models/User";
import {useSessionStorage} from "./hooks/SessionStoragehook";
import ProductDetail from "./views/ProductDetail/ProductDetail";
import CartView from "./views/Cart/CartView";
import CartService from "./services/CartService";
import Cart from "./models/Cart";
import {AuthenticationContext} from "./context/AuthenticationContext";
import {CartContext} from "./context/CartContext";

function App() {

    const [user, setUser] = useState<User>();

    const [token, setToken, removeToken] = useSessionStorage('token');
    const [cart, setCart] = useState<Cart>();

    useEffect(() => {
        if(token) {
            UserService.getUserDetails(token)
                .then(user => setUser(user))
                .catch(e => removeToken())

            CartService.getCart(token)
                .then(cart => setCart(cart))
                .catch(e => CartService.createCart(token));
        }
        else {
            setUser(undefined);
        }
    }, [token]);

    function logout() {
        removeToken();
    }


  return (
    <>
        <AuthenticationContext.Provider value={{token, setToken, removeToken}}>
            <CartContext.Provider value={{cart, setCart}}>
                <BrowserRouter>
                    <header>
                        <a className="title" href="/">M321 Shop</a>
                        {token?(
                            <>
                            <Link to="/cart" className="cart">
                                <img width={30} src={cartImg} />
                                <div className="cart-number">{cart? cart.products.length: 0}</div>
                            </Link>
                            <a href="javascript:void(0)" onClick={logout}>Log out</a>
                            </>
                        ): <Link to="/login">Sign in</Link>}
                    </header>
                    <main>
                      <Routes>
                            <Route path="/" element={<Home />}/>
                            <Route path="/product/:id" element={<ProductDetail/>}/>
                              <Route path="/cart" element={<CartView/>}/>
                              {!user? (<>
                                    <Route path="/signup" element={<Signup/>}/>
                                    <Route path="/login" element={<Login/>}/>
                                  </>
                                  ):
                                  ''
                              }
                      </Routes>
                    </main>
                </BrowserRouter>
            </CartContext.Provider>
        </AuthenticationContext.Provider>
    </>
  )
}

export default App
