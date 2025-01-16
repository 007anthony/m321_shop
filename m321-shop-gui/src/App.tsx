import {useEffect, useState} from 'react'
import './App.css'
import Home from './views/Home/Home'
import {BrowserRouter, RedirectFunction, Route, Routes} from "react-router";
import Login from "./views/Login/Login";
import Signup from "./views/Signup/Signup";
import UserService from "./services/UserService";
import User from "./models/User";
import {useSessionStorage} from "../hooks/SessionStoragehook";

function App() {

    const [user, setUser] = useState<User>();

    const [token, setToken] = useSessionStorage('token');



    useEffect(() => {
        setToken(sessionStorage.getItem("token"))
    }, [])

    useEffect(() => {
        if(token) {
            UserService.getUserDetails(token)
                .then(user => setUser(user))
                .catch(e => console.log(e))
        }
    }, [token]);


  return (
    <>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home />}/>
              {!user? (<>
                    <Route path="/signup" element={<Signup/>}/>
                    <Route path="/login" element={<Login/>}/>
                  </>
                  ): ''}
          </Routes>
        </BrowserRouter>
    </>
  )
}

export default App
