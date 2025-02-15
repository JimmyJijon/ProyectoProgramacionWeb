import React, { useEffect, useState } from 'react';
import EstudianteService from '../services/EstudianteService';
import { Link } from 'react-router-dom';

//Este componente podra ser llamado desde otras partes del proyecto (App.js)
export const ListEstudiantesComponent = () => {

    //Este array se utilizará para almacenar la lista de estudiantes que se obtendrá del EstudianteService
    const [estudiantes, setEstudiantes] = useState([]);
    
    //sirve para ejecutar el codigo cuando se monta el componente ListEstudiantes
    useEffect(() => {
        // listarEstudiantes se usa dentro de useEffect para ejecutarse automáticamente cuando el componente se monta
        listarEstudiantes()
    }, [])

    //Llama al servicio deleteEstudiante para eliminarlo en la API
    const deleteEstudiante = (estudianteId) => {
        EstudianteService.deleteEstudiante(estudianteId).then((response) => {
            //Si la operación es exitosa, vuelve a llamar a listarEstudiantes() para actualizar la lista.
            listarEstudiantes();

        }).catch(error => {
            console.log(error);
        })
    }
    
    //llamo a esta funcion dentro de UseEffect
    // = () => { ... } → Esto es una función flecha en JavaScript. +
    // equivale a escribir function agregarEstudiante() { ... }, pero con una sintaxis más moderna.
    const listarEstudiantes = () => {

        EstudianteService.getAllEstudiantes().then(response => {
            setEstudiantes(response.data);
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })
    }

    return (
        //retorna el JSX 
        //en react el return dentro de un componente funcional define que se mostrara en la interfaz de usuario
        <div className='containerList'>
            
            <h2 className='text-center'> Lista de estudiantes</h2>
            <Link to='/add-estudiante' className='btn btn-primary mb-2'>Agregar nuevo estudiante</Link>
            
            <table className='table table-bordered table-striped'>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Email</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {   //recorre la lista de estudiantes y crea una fila (<tr>) para cada estudiante.
                        estudiantes.map(
                            estudiante =>
                                <tr key={estudiante.id}>
                                    <td>{estudiante.id}</td>
                                    <td>{estudiante.nombre}</td>
                                    <td>{estudiante.apellido}</td>
                                    <td>{estudiante.email}</td>
                                    <td>
                                        <Link className='btn btn-info' to={`/edit-estudiante/${estudiante.id}`}>Actualizar</Link>
                                        <button style={{ marginLeft: "10px" }} className='btn btn-danger' onClick={() => deleteEstudiante(estudiante.id)}>Eliminar</button>
                                    </td>
                                </tr>
                        )
                    }
                </tbody>
            </table>
            
        </div>
    )
}

export default ListEstudiantesComponent;