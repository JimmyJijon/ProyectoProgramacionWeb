import React from 'react';

//ESTE ES EL COMPONENTE QUE MOSTRARA LA PANTALLA PRINCIPAL "HOME"
function HomePageComponent() {
  return (
    <div className="homePageComponent">
      <h1>Bienvenido a la página principal</h1>
      <div className='perfilComponent'>
      </div>
      <p>En esta página puedes ver información sobre la aplicación y navegar a otras secciones.</p>
      <div className='EstContainer'>
        <div className='FotoDelEst'>

        </div>
      </div>
    </div>
  );
}

export default HomePageComponent;