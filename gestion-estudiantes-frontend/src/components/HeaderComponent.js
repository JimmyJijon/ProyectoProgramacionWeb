import React from 'react'
import { Link } from 'react-router-dom';

//ESTE COMPONENTE MUESTRA LA CABECERA PRINCIPAL EN DONDE TENEMOS EL LOGO Y EL BOTON DE SALIR
export const HeaderComponent = () => {
  return (
    <div className="container-HeadPadre">
      <header>
        <nav className="navbar bg-danger text-white">
          <div className="container-headerHijo">
            <Link to="/">
              <img src="/satLogo.png" alt="Logo" className="logo" />
            </Link>
            <span>
              <h4>Pagina de Inicio</h4>
            </span>
            <Link to="/" className='Linksalir'>
              <img src="/salir.png" alt="salir" className='DimSalir'/>
            </Link>
            <span className="navbar-brand mb-0 h1"></span>
          </div>
        </nav>
      </header>
    </div>
  )
}

export default HeaderComponent;
