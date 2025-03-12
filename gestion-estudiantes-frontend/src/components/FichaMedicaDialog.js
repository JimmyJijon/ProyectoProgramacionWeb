import React, { useState } from "react";
import { Dialog } from "primereact/dialog";
import { Button } from "primereact/button";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import axios from "axios";

const FichaMedicaDialog = ({ estudianteId }) => {
    const [dialogVisible, setDialogVisible] = useState(false);
    const [fichaMedica, setFichaMedica] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchFichaMedica = async () => {
        if (!estudianteId) {
            setError("No se encontró el ID del estudiante.");
            return;
        }

        setLoading(true);
        setError(null);

        try {
            const response = await axios.get(`http://localhost:8080/api/v1/ficha-medica/${estudianteId}`);
            setFichaMedica(response.data);
            setDialogVisible(true);
        } catch (err) {
            console.error("Error obteniendo la ficha médica:", err);
            setError("No se pudo cargar la ficha médica.");
        }

        setLoading(false);
    };

    return (
        <div>
            {/*boton para que aparezca el dialogo*/}
            <Button 
                label="Datos personales y familiares médicos" 
                icon="pi pi-user" 
                className="p-button-danger"  
                style={{ 
                    backgroundColor: "red", 
                    border: "none",         
                    borderRadius: "10px",   
                    color: "white",         
                    padding: "10px 15px",  
                    fontWeight: "bold",     
                    fontSize: "14px"      
                }} 
                onClick={fetchFichaMedica} 
            />
    
            {/*dialogo de ficha medica*/}
            <Dialog 
                header={
                    <div style={{ 
                        backgroundColor: "red", 
                        color: "white", 
                        padding: "10px", 
                        textAlign: "center", 
                        borderRadius: "5px 5px 0 0" 
                    }}>
                         <strong>Ficha Médica</strong>
                    </div>
                } 
                visible={dialogVisible} 
                style={{ width: "60vw", borderRadius: "10px" }} 
                modal 
                onHide={() => setDialogVisible(false)}
            >
                {loading && <p style={{ color: "red", textAlign: "center" }}>Cargando...</p>}
                {error && <p className="text-danger">{error}</p>}
                {fichaMedica && (
                    <div style={{ padding: "20px", borderRadius: "10px", backgroundColor: "#fff" }}>
                        
                        {/*info del estudiante*/}
                        <h4 style={{ color: "red", borderBottom: "2px solid red", paddingBottom: "5px" }}>
                             Información del Estudiante
                        </h4>
                        <p><strong>Nombre:</strong> {fichaMedica.estudiante.nombre} {fichaMedica.estudiante.apellido}</p>
                        <p><strong>Correo Institucional:</strong> {fichaMedica.estudiante.emailInstitucional}</p>
                        <p><strong>Edad:</strong> {fichaMedica.estudiante.edad} años</p>
                        <p><strong>Estado Civil:</strong> {fichaMedica.estudiante.estadoCivil}</p>
                        <p><strong>País de Nacimiento:</strong> {fichaMedica.estudiante.paisNacimiento}</p>
    
                        {}
                        <h4 style={{ color: "red", borderBottom: "2px solid red", paddingBottom: "5px", marginTop: "20px" }}>
                             Antecedentes Patológicos
                        </h4>
                        <DataTable value={fichaMedica.antecedentesPatologicos} tableStyle={{ minWidth: "50rem", borderRadius: "10px" }}>
                            
                            <Column field="descripcion" header="Descripción"></Column>
                        </DataTable>
    
                        {}
                        <h4 style={{ color: "red", borderBottom: "2px solid red", paddingBottom: "5px", marginTop: "20px" }}>
                             Hábitos Personales
                        </h4>
                        <DataTable value={fichaMedica.habitosPersonales} tableStyle={{ minWidth: "50rem", borderRadius: "10px" }}>
                            
                            <Column field="descripcion" header="Descripción"></Column>
                        </DataTable>
                    </div>
                )}
            </Dialog>
        </div>
    );
};


export default FichaMedicaDialog;