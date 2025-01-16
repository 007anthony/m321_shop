import {useEffect, useState} from "react";

export function useSessionStorage(key: string) {
    const [state, setState] = useState<string>();

    useEffect(() => {
        const value = sessionStorage.getItem(key);
        setState(value);
    }, []);

    const setEntry = (value: string) => {
        setState(value);

        sessionStorage.setItem(key, value);
    }

    const removeEntry = () =>  {
        setState(undefined);

        sessionStorage.removeItem(key);
    }

    return [state, setEntry, removeEntry];
}