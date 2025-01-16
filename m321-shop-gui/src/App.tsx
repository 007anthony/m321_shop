import {useEffect, useState} from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Home from './views/Home/Home'
import {BrowserRouter, Route, Routes} from "react-router";
import Login from "./views/Login/Login";
import UserService from "./services/UserService";
import User from "./models/User";

function App() {

    const [user, setUser] = useState<User>();
    const [token, setToken] = useState<string>();

    useEffect(() => {
        setToken(sessionStorage.getItem("token"))
    })

    useEffect(() => {
        if(token) {
            sessionStorage.setItem("token", token);
            UserService.getUserDetails(sessionStorage.getItem("token"))
                .then(user => setUser(user))
        }
    }, [token]);


    if(!user) {
        return <Login setToken={setToken}/>
    }


  return (
    <>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home />}/>
          </Routes>
        </BrowserRouter>
    </>
  )
}

export default App
