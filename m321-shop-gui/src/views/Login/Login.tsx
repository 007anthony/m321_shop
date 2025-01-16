import './Login.css';
import {FormEvent, useState} from "react";
import UserService from "../../services/UserService";
import {Link, useNavigate} from "react-router";
import {useSessionStorage} from "../../../hooks/SessionStoragehook";

interface IProps {
    setToken: (token: string) => void
}

export default function Login() {

    const [token, setToken] = useSessionStorage('token');

    const [error, setError] = useState<string>();
    const navigate = useNavigate();

    function submit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();

        const form = event.target as HTMLFormElement;

        const formData = new FormData(form);

        UserService.login(formData.get("username").toString(), formData.get("password").toString())
            .then(token => setToken(token))
            .catch(e => {
                console.log(e);
                setError("Username or password is incorrect");
                }
            );

        navigate("/");
    }

    return(

            <form onSubmit={submit}>
                <h1>Login</h1>
                {error? <p class="error">{error}</p>: ''}
                <label>
                    Username
                    <br/>
                    <input type="text" name="username"/>
                </label>
                <label>
                    Password
                    <br/>
                    <input type="password" name="password"/>
                </label>
                <Link to="/signup">Signup</Link>
                <input type="submit" value="Login"/>
            </form>
    );
}