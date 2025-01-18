import User from "./User";
import Product from "./Product";

export default interface Cart {
    id: number
    user: User
    active: boolean
    products: Product[]
}