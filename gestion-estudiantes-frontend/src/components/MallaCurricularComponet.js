import React, { useState, useEffect } from "react";
import axios from "axios";

function MallaCurricular() {
  const [malla, setMalla] = useState(null); // Estado inicial es null hasta que se llame la funcion setMalla.
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const endpoint = "http://localhost:8080/api/v1/mallas/carrera/101";

  // useEffect(() => {
  //   axios
  //     .get(endpoint)
  //     .then((response) => {
  //       setMalla(response.data); // Axios ya parsea JSON automáticamente
  //       setLoading(false);
  //     })
  //     .catch((error) => {
  //       setError(error.message);
  //       setLoading(false);
  //     });
  // }, []);

  // useEffect(() => {
  //   fetch(endpoint)
  //     .then((response) => response.json())
  //     .then((data) => console.log(data));
  // }, []);

  useEffect(() => {
    fetch(endpoint)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Error al obtener los datos");
        }
        return response.json();
      })
      .then((data) => {
        setMalla(data);
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  }, []);

  //return malla.id;
  //if (malla.materias.length > 0) return <p>{malla.materias["nombre"]}</p>;
  //return <p>Se encontraron {malla.materias.length} registros</p>;
  //return malla.materias[1].nombre;

  if (loading) return <p>Cargando...</p>;
  if (error) return <p>Error: {error}</p>;
  if (!malla || !malla.materias) return <p>No hay datos disponibles</p>; // ✅ Validación antes de renderizar

  return (
    <div class="card">
      <div class="card-header">Malla Curricular - {malla.id}</div>
      <div class="card-body">
        <h5 class="card-title">
          Carrera: Tecnologia en Desarrollo de Software
        </h5>
        <p class="card-text">
          <table class="table table-bordered">
            <thead class="table-dark">
              <tr>
                <th>ID Materia</th>
                <th>Nombre</th>
              </tr>
            </thead>
            <tbody>
              {malla.materias.map((materia) => (
                <tr key={materia.id}>
                  <td>{materia.id}</td>
                  <td>{materia.nombre}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </p>
      </div>
    </div>
  );
}

export default MallaCurricular;

/*
  return (
    <>
      <div class="card">
        <div class="card-header">Malla Curricular</div>
        <div class="card-body">
          <h5 class="card-title">
            Carrera: Tecnologia en Desarrollo de Software
          </h5>
          <p class="card-text">
            <table class="table table-bordered">
              <thead class="table-dark">
                <tr>
                  <th scope="col">1er Nivel</th>
                  <th scope="col">2do Nivel</th>
                  <th scope="col">3er Nivel</th>
                  <th scope="col">4er Nivel</th>
                  <th scope="col">5to Nivel</th>
                  <th scope="col">6to Nivel</th>
                  <th scope="col">7mo Nivel</th>
                  <th scope="col">8vo Nivel</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Materia Y1N1</td>
                  <td>Materia Y1N2</td>
                  <td>Materia Y1N3</td>
                  <td>Materia Y1N4</td>
                  <td>Materia Y1N5</td>
                  <td>Materia Y1N6</td>
                  <td>Materia Y1N7</td>
                  <td>Materia Y1N8</td>
                </tr>
                <tr>
                  <td>Materia Y2N1</td>
                  <td>Materia Y2N2</td>
                  <td>Materia Y2N3</td>
                  <td>Materia Y2N4</td>
                  <td>Materia Y2N5</td>
                  <td>Materia Y2N6</td>
                  <td>Materia Y2N7</td>
                  <td>Materia Y2N8</td>
                </tr>
                <tr>
                  <td>Materia Y3N1</td>
                  <td>Materia Y3N2</td>
                  <td>Materia Y3N3</td>
                  <td>Materia Y3N4</td>
                  <td>Materia Y3N5</td>
                  <td>Materia Y3N6</td>
                  <td>Materia Y3N7</td>
                  <td>Materia Y3N8</td>
                </tr>
              </tbody>
            </table>
          </p>
        </div>
      </div>
    </>
  );
}

export default MallaCurricular;
*/
