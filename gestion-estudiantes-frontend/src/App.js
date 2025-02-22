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
import CalendarioEstudiante from './components/CalendarioEstudiante';


// ESTE COMPONENTE ES EL PRINCIPAL DE REACT, AQUI SE MANEJAN LAS RUTAS DE LOS COMPONENTES
//AQUI LLAMAMOS A LOS COMPONENTES PARA QUE SE MUESTREN EN PANTALLA DE FORMA ORDENADA
function App() {
  return (
    <div>
      <BrowserRouter>
        <HeaderComponent />
        <div className='container'>
          <Routes>
          <Route path="/" element={<Login />} /> {/* Ahora el Login es la pantalla de inicio */}
           <Route path='/register' element={<Register />}></Route>
           <Route exact path='/home' element={< HomePageComponent />}></Route>
           <Route path='/estudiantes' element={<ListEstudiantesComponent />}></Route>
           <Route path='/add-estudiante' element={<AddEstudianteComponent />}></Route>
           <Route path='/edit-estudiante/:id' element={<AddEstudianteComponent />}></Route>
           <Route path='/calendario' element={<CalendarioEstudiante />} />
          </Routes>
        </div>
        <FooterComponent />
      </BrowserRouter>
    </div>
  );
}

export default App;
