import {createContext} from "react";

export const AuthenticationContext = createContext<{
    token: string | undefined,
    setToken: (token: string) => void,
    removeToken: () => void
}>({token: undefined, setToken: (token: string) => {}, removeToken: () => {}});