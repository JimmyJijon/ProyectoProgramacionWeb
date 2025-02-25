import logo from './logo.svg';
import './App.css';
import ListEstudiantesComponent from './components/ListEstudiantesComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AddEstudianteComponent from './components/AddEstudianteComponent';
import HomePageComponent from './components/HomePageComponent';
import Login from './components/Login';
import Register from './components/Register';
import CronogramaComponent from './components/CronogramaComponent';
import "primereact/resources/themes/lara-light-blue/theme.css";  // 🎨 Tema (puedes cambiarlo)
import "primereact/resources/primereact.min.css";  // 📦 Estilos base de PrimeReact
import "primeicons/primeicons.css";  // 🔥 Íconos de PrimeReact

// ESTE COMPONENTE ES EL PRINCIPAL DE REACT, AQUI SE MANEJAN LAS RUTAS DE LOS COMPONENTES
//AQUI LLAMAMOS A LOS COMPONENTES PARA QUE SE MUESTREN EN PANTALLA DE FORMA ORDENADA
function App() {
  return (
    <div>
      <BrowserRouter>
        <HeaderComponent />
        <div className='container'>
          <Routes>
          <Route exact path='/login' element={< Login />}></Route>
           <Route exact path='/home' element={< HomePageComponent />}></Route>
           <Route path='/estudiantes' element={<ListEstudiantesComponent />}></Route>
           <Route path='/add-estudiante' element={<AddEstudianteComponent />}></Route>
           <Route path='/edit-estudiante/:id' element={<AddEstudianteComponent />}></Route>
           <Route path='/register' element={<Register />}></Route> {/* Ruta para el registro */}
           <Route path='/cronograma' element={<CronogramaComponent/>}></Route>
          </Routes>
        </div>
        <FooterComponent />
      </BrowserRouter>
    </div>
  );
}

export default App;
