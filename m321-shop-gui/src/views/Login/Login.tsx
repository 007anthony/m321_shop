import {FormEvent} from "react";
import UserService from "../../services/UserService";

interface IProps {
    setToken: (token: string) => void
}

export default function Login({setToken}) {

    function submit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();

        const form = event.target as HTMLFormElement;

        const formData = new FormData(form);

        UserService.login(formData.get("username").toString(), formData.get("password").toString())
            .then(token => setToken(token));
    }

    return(
        <form onSubmit={submit}>
            <label>
                Username: <input type="text" name="username"/>
            </label>
            <label>
                Password: <input type="password" name="password"/>
            </label>
            <input type="submit" value="Login"/>
        </form>
    );
}