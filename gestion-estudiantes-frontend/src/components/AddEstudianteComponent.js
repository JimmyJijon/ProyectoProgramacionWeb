import React, { useState } from 'react';
import EstudianteService from '../services/EstudianteService';
import { Navigate, useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import { useEffect } from 'react';


export const AddEstudianteComponent = () => {

    const [nombre, setNombre] = useState('');
    const [apellido, setApellido] = useState('');
    const [email, setEmail] = useState('');
    const navigate = useNavigate();
    const { id } = useParams();

    const saveOrUpdateEstudiante = (e) => {
        e.preventDefault();
        const estudiante = { nombre, apellido, email };

        if (id) {// Si hay un id significa que estamos actualizando
            EstudianteService.updateEstudiante(id, estudiante).then((response) => {
                console.log(response.data);
                navigate('/estudiantes');
            }).catch(error => {
                console.log(error)
            })
        }
        else {//Si no hay id se está creando un nuevo estudiante
            EstudianteService.createEstudiante(estudiante).then((response) => {
                console.log(response.data);
                //Despues de guardar, redirige a la vista ('/estudiantes')
                navigate('/estudiantes');
            }).catch(error => {
                console.log(error)
            })
        }


    }

    useEffect(() => {
        EstudianteService.getEstudianteById(id).then((response) => {
            setNombre(response.data.nombre);
            setApellido(response.data.apellido);
            setEmail(response.data.email);
        }).catch(error => {
            console.log(error);
        })
    },[])

    const title = () => {
        if (id) {//Si id tiene un valor diferente a nulo significa que estamos editando un estudiante, por lo que la función devuelve ACTUALIZAR 
            return <h2 className='text-center'>Actualizar Estudiante</h2>
        }
        else {//Si id no tiene un valor, significa que estamos agregando un nuevo estudiante, por lo que devuelve AGREGAR
            return <h2 className='text-center'>Agregar Estudiante</h2>
        }
    }
    return (
        <div>
            <div className='container'>
                <div className='row'>
                    <div className='card col-md-6 offset-md-3 offset-md-3'>
                        <h2 className='text-center'> {
                            title()//Llamamos a la funcion que cambiara dependiendo si hay o no un ID
                        } </h2>
                        <div className='card-body'></div>
                        <form>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Nombre</label>
                                <input
                                    type='text'
                                    placeholder='Digite su nombre'
                                    name='nombre'
                                    className='form-control'
                                    value={nombre}
                                    onChange={(e) => setNombre(e.target.value)}
                                />
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Apellido</label>
                                <input
                                    type='text'
                                    placeholder='Digite su apellido'
                                    name='apellido'
                                    className='form-control'
                                    value={apellido}
                                    onChange={(e) => setApellido(e.target.value)}
                                />
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Email</label>
                                <input
                                    type='email'
                                    placeholder='Digite su email'
                                    name='email'
                                    className='form-control'
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                />
                            </div>
                            <button className='btn btn-success' onClick={(e) => saveOrUpdateEstudiante(e)}>Guardar</button>
                            &nbsp;&nbsp;
                            <Link to='/estudiantes' className='btn btn-danger'>Cancelar</Link>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddEstudianteComponent;