import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { PrimeReactProvider } from 'primereact/api';

import 'primereact/resources/themes/lara-light-amber/theme.css'; // Tema de PrimeReact
import 'primereact/resources/primereact.min.css'; // Estilos de componentes
import 'primeicons/primeicons.css'; // Íconos de PrimeReact
import 'primeflex/primeflex.css'; // Utilidades CSS como grids y flexbox
import './index.css';



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <PrimeReactProvider value={{ cssTransition: false }}>
      <App />
    </PrimeReactProvider>
  </React.StrictMode>
);

reportWebVitals();
