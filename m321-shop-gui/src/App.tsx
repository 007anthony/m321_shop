import {useEffect, useState} from 'react'
import './App.css'
import Home from './views/Home/Home'
import {BrowserRouter, RedirectFunction, Route, Routes} from "react-router";
import Login from "./views/Login/Login";
import Signup from "./views/Signup/Signup";
import UserService from "./services/UserService";
import User from "./models/User";
import {useSessionStorage} from "../hooks/SessionStoragehook";
import ProductDetail from "./views/ProductDetail/ProductDetail";
import CartView from "./views/Cart/CartView";

function App() {

    const [user, setUser] = useState<User>();

    const [token, setToken, removeToken] = useSessionStorage('token');

    useEffect(() => {
        if(token) {
            UserService.getUserDetails(token)
                .then(user => setUser(user))
                .catch(e => removeToken())
        }
    }, [token]);


  return (
    <>
        <main>
            <BrowserRouter>
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
            </BrowserRouter>
        </main>
    </>
  )
}

export default App
