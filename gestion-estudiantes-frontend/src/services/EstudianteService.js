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

   getEstudianteMaterias(){
    return axios.get("http://localhost:8080/api/v1/estudiante-materias");
   }

   getMateriaEstudiante(estudianteId){
    return axios.get("http://localhost:8080/api/v1/materia-estudiante/" + estudianteId);
   }

   getEstudianteMateria(materiaId){
    return axios.get("http://localhost:8080/api/v1/estudiante-materia/" + materiaId);
   }

   getEstudianteMateriaNota(estudianteId, materiaId){
    return axios.get("http://localhost:8080/api/v1/estudiante-materia/nota", {
        params: {
            estudianteId: estudianteId,
            materiaId: materiaId
        }
    });
   }

   actualizarEstadoEstudianteMateria(estudianteId, materiaId, nuevoEstado){
    return axios.put("http://localhost:8080/api/v1/estudiante-materia/actualizar-estado", null, {
        params: {
            estudianteId: estudianteId,
            materiaId: materiaId,
            nuevoEstado: nuevoEstado
        }
    });
   }
}

export default new EstudianteService();