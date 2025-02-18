import axios from "axios";

//ESTOS METODOS NOS PERMITEN HACER LAS PETICIONES GET, POST, PUT, DELETE, DEBERAN SER LLAMADOS DESDE UN COMPONENTE
const ESTUDIANTE_BASE_REST_API_URL = "http://localhost:8080/api/v1/estudiantes";

class EstudianteService{

   getAllEstudiantes(){
    return axios.get(ESTUDIANTE_BASE_REST_API_URL);
   }

   createEstudiante(estudiante){
     return axios.post(ESTUDIANTE_BASE_REST_API_URL,estudiante);
   }
   
   getEstudianteById(estudianteId){
    return axios.get(ESTUDIANTE_BASE_REST_API_URL + '/' + estudianteId);
   }

   updateEstudiante(estudianteId,estudiante){
    return axios.put(ESTUDIANTE_BASE_REST_API_URL + '/' + estudianteId,estudiante);
   }

   deleteEstudiante(estudianteId){
    return axios.delete(ESTUDIANTE_BASE_REST_API_URL + '/' + estudianteId)
   }
}

export default new EstudianteService();