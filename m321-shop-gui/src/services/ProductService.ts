export default class ProductService {
    static async getProduct(id: number) {
        const response = await fetch(`http://localhost:8080/products/${id}`);

        if(response.ok) {
            return response.json();
        }

        return Promise.resolve(null);
    }
}