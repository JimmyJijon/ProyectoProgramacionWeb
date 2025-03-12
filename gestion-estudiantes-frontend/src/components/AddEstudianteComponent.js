import React, { useState, useEffect } from 'react';
import EstudianteService from '../services/EstudianteService';
import { useNavigate, Link, useParams } from 'react-router-dom';

export const AddEstudianteComponent = () => {

    const [nombre, setNombre] = useState('');
    const [apellido, setApellido] = useState('');
    const [email, setEmail] = useState('');
    const navigate = useNavigate();
    const { id } = useParams();

    const saveOrUpdateEstudiante = (e) => {
        e.preventDefault();
        const estudiante = { nombre, apellido, email };

        if (id) {
            EstudianteService.updateEstudiante(id, estudiante)
                .then((response) => {
                    console.log(response.data);
                    navigate('/estudiantes');
                })
                .catch(error => console.log(error));
        } else {
            EstudianteService.createEstudiante(estudiante)
                .then((response) => {
                    console.log(response.data);
                    navigate('/estudiantes');
                })
                .catch(error => console.log(error));
        }
    };

    useEffect(() => {
        if (id) {
            EstudianteService.getEstudianteById(id)
                .then((response) => {
                    setNombre(response.data.nombre);
                    setApellido(response.data.apellido);
                    setEmail(response.data.email);
                })
                .catch(error => console.log(error));
        }
    }, [id]);

    return (
        <div>
            <div className='container'>
                <div className='row'>
                    <div className='card col-md-6 offset-md-3 offset-md-3'>
                        <h2 className='text-center'>
                            {id ? 'Actualizar Estudiante' : 'Agregar Estudiante'}
                        </h2>
                        <div className='card-body'>
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
        </div>
    );
}

export default AddEstudianteComponent;
