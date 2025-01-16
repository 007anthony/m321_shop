import {FormEvent, useState} from "react";
import User from "../../models/User";
import {useSessionStorage} from "../../../hooks/SessionStoragehook";
import UserService from "../../services/UserService";

export default function Signup() {

    const [token, setToken] = useSessionStorage("token");
    const [error, setError] = useState<string>();

    function submit(event: FormEvent) {
        event.preventDefault();
        const form = event.target as HTMLFormElement;

        const formData = new FormData(form);

        const password = formData.get("password") as string;
        const retypePassword = formData.get("retypePassword") as string;

        if (password === retypePassword) {
            const user = {
                username: formData.get("username") as string,
                email: formData.get("email") as string,
                password: password
            } as User;

            UserService.createUser(user)
                .then(user =>
                    UserService.login(user.username, password)
                        .then(token => setToken(token))
                )
                .catch(e => setError("There was an error when trying creating the user: " + e.message))

        }
        else {
            setError("Passwords don't match")
        }

    }

    return (
        <form onSubmit={submit}>
            <h1>Sign up</h1>
            <p className="error">{error}</p>
            <label>
                Username
                <br/>
                <input type="text" name="username" />
            </label>
            <label>
                E-mail
                <br/>
                <input type="email" name="email"/>
            </label>
            <label>
                Password
                <br/>
                <input type="password" name="password" />
            </label>
            <label>
                Retype password
                <br/>
                <input type="password" name="retypePassword"/>
            </label>
            <input type="submit" value="Sign up"/>
        </form>
    )
}