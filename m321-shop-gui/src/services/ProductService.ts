export default class ProductService {

    static async getProducts() {
        const response = await fetch("http://localhost:8080/products");

        if(response.ok) {
            return response.json();
        }

        throw new Error("Couldn't retrieve products");
    }

    static async getProduct(id: number) {
        const response = await fetch(`http://localhost:8080/products/${id}`);

        if(response.ok) {
            return response.json();
        }

        return Promise.resolve(null);
    }
}