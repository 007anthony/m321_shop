interface Picture {
    id: number
    picture: string
}

interface Category {
    id: number
    category: string
}

export default interface Product {
    id: number
    product: string
    price: number
    category: Category
    pictures: Picture[]
}