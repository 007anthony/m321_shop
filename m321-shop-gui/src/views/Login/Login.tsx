import './Login.css';
import {FormEvent, useState} from "react";
import UserService from "../../services/UserService";

interface IProps {
    setToken: (token: string) => void
}

export default function Login({setToken}) {

    const [error, setError] = useState<string>();

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
                <input type="submit" value="Login"/>
            </form>
    );
}