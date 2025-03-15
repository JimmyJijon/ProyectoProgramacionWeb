import React from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../App.css';

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      error: ''
    };
  }

  handleSubmit = async (event) => {
    event.preventDefault();
    console.log("Attempting login with username:", this.state.username, "and password:", this.state.password);

    try {
      const response = await axios.post('http://localhost:8080/api/v2/login', {
        username: this.state.username,
        password: this.state.password,
      });

      console.log("Login response:", response.data);

      const token = response.data.token;
      console.log("Token extracted from login response:", token);
      localStorage.setItem('token', token);

      let studentId = response.data.estudiante_id;
      console.log("StudentId from login response:", studentId);

      if (studentId === null || studentId === undefined) {
        console.log("StudentId not found in login response, fetching profile...");
        try {
          const profileResponse = await axios.get('http://localhost:8080/api/v2/perfil', {
            headers: {
              'Authorization': `Bearer ${token}`
            }
          });
          console.log("Perfil response:", profileResponse.data);
          const { estudiante, estudiante_id } = profileResponse.data;
          const extractedId = estudiante?.id ?? estudiante_id;

          if (extractedId) {
            studentId = extractedId;
            console.log("StudentId extracted using destructuring and optional chaining:", studentId);
          } else {
            console.log("No estudiante or estudiante_id field found in profile response. StudentId remains undefined.");
          }
        } catch (profileError) {
          console.error("Error fetching user profile:", profileError);
        }
      }
      
      if (studentId !== null && studentId !== undefined) {
        console.log("Final studentId to be stored:", studentId);
        localStorage.setItem('estudianteId', String(studentId));
        this.props.navigate('/home');
      } else {
        console.error("El usuario no tiene un perfil de estudiante asociado.");
        this.setState({ error: "El usuario no tiene un perfil de estudiante asociado." });
      }
    } catch (err) {
      console.error("Login error:", err);
      this.setState({ error: 'Credenciales incorrectas' });
    }
  }

  render() {
    return (
      <div
        className="containerlog"
        style={{
          backgroundImage: "url('/fondoLogin.png')",
          backgroundSize: "cover",
          backgroundRepeat: "no-repeat",
          backgroundPosition: "center center",
          width: "100vw",
          height: "100vh",
          position: "fixed",
          top: 0,
          left: 0,
        }}
      >
        <div className="login-container">
          <h2>Ingresa al SAT</h2>
          {this.state.error && <p className="error-message">{this.state.error}</p>}
          <form onSubmit={this.handleSubmit}>
            <div className="form-group">
              <label htmlFor="username">Usuario:</label>
              <input
                id="username"
                type="text"
                value={this.state.username}
                onChange={(e) => this.setState({ username: e.target.value })}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="password">Contraseña:</label>
              <input
                id="password"
                type="password"
                value={this.state.password}
                onChange={(e) => this.setState({ password: e.target.value })}
                required
              />
            </div>
            <button type="submit">Acceder</button>
          </form>
        </div>
      </div>
    );
  }
}

export default function LoginWrapper() {
  const navigate = useNavigate();
  return <Login navigate={navigate} />;
}
