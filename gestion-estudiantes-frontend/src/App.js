import logo from './logo.svg';
import './App.css';
import ListEstudiantesComponent from './components/ListEstudiantesComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AddEstudianteComponent from './components/AddEstudianteComponent';
import HomePageComponent from './components/HomePageComponent';

// ESTE COMPONENTE ES EL PRINCIPAL DE REACT, AQUI SE MANEJAN LAS RUTAS DE LOS COMPONENTES
//AQUI LLAMAMOS A LOS COMPONENTES PARA QUE SE MUESTREN EN PANTALLA DE FORMA ORDENADA
function App() {
  return (
    <div>
      <BrowserRouter>
        <HeaderComponent />
        <div className='container'>
          <Routes>
            <Route exact path='/' element={<HomePageComponent />}></Route>
            <Route path='/estudiantes' element={<ListEstudiantesComponent />}></Route>
            <Route path='/add-estudiante' element={<AddEstudianteComponent />}></Route>
            <Route path='/edit-estudiante/:id' element={<AddEstudianteComponent />}></Route>
          </Routes>
        </div>
        <FooterComponent />
      </BrowserRouter>
    </div>
  );
}

export default App;
