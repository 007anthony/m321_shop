import {useSessionStorage} from "../hooks/SessionStoragehook";
import Product from "../models/Product";

export default class CartService {


    static async createCart(token: string) {
        const response = await fetch("http://localhost:8080/cart", {
            method: "POST",
            headers: {
                Authorization: "Bearer " + token,
                'content-type': "application/json",
            }
        })
    }
    static async getCart(token: string) {
        const response = await fetch("http://localhost:8080/cart", {
            method: "GET",
            headers: {
                Authorization: "Bearer " + token
            }
        });

        if(response.ok) {

            return response.json();
        }
        throw new Error("There was an error");
    }

    static async addItem(token: string, productId: number) {
        const response = await fetch("http://localhost:8080/cart/items", {
            method: "POST",
            headers: {
                Authorization: "Bearer " + token,
                "content-type": "application/json"
            },
            body: JSON.stringify({
                productId
            })
        });

        if(response.ok) {
            return response.json();
        }

        throw new Error("Couldn't add product to the shopping cart")
    }

    static async removeItem(token: string, productId: number) {
        const response = await fetch(`http://localhost:8080/cart/items/${productId}`, {
            method: "DELETE",
            headers: {
                Authorization: "Bearer " + token
            }
        })

        if(!response.ok) {
            throw new Error("Couldn't delete")
        }
    }
}