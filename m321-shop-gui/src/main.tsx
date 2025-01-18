import {createContext, StrictMode} from 'react'
import { createRoot } from 'react-dom/client'
import {AuthenticationContext} from "./context/AuthenticationContext";
import './index.css'
import App from './App.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <App />
  </StrictMode>,
)
