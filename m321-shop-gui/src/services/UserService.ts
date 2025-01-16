import User from "../models/User";

export default class UserService {
    static async login(username: string, password: string) {
        const response = await fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                'content-type': 'application/json'
            },

            body: JSON.stringify({
                username,
                password
            })
        });

        if(response.ok) {
            return response.text();
        }

        throw new Error("test");
    }

    static async getUserDetails(token: string): Promise<User> {
        try {
            const response = await fetch("http://localhost:8080/auth/me", {
                headers: {
                    'Authorization': token
                }
            });

            if(response.ok) {
                return response.json();
            }

            throw new Error("Couldn't get the user");

        }
        catch (e) {
            throw e;
        }
    }
}