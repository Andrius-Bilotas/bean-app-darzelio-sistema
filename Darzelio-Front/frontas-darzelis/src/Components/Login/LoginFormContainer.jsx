import React, { Component } from 'react';
import axios from 'axios';

import LoginFormPresentation from './LoginFormPresentation';
import { API } from '../../Configuration/AppConfig';
import { withRouter } from 'react-router';
import UserService from '../../Configuration/UserService';

axios.defaults.withCredentials = true; // leidžia dalintis cookies

class LoginFormContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: '',
      password: '',
      role: '',
      expirationDate: ''
    }
  };

  onEmailChange = (event) => {
    this.setState({ email: event.target.value });
  };

  onPasswdChange = (event) => {
    this.setState({ password: event.target.value });
  };

  onSubmit = (event) => {
    let userData = new URLSearchParams();
    userData.append('username', this.state.email);
    userData.append('password', this.state.password);

    axios
        .post(`${API}/login`, userData,
        {
          headers: { 'Content-type': 'application/x-www-form-urlencoded' },
        }
        )
        .then((response) => {
          UserService.setRole(response.data.role);
          this.setState({ role: response.data.role });
          // console.log(response.data);
          const currentRole = this.state.role;
          switch (currentRole) {
            case "[PARENT]":
              return (this.props.history.push('/tevai'))
              break;
            case "[EDU]":
              return (this.props.history.push('/admin/edu'))
              break;
            case "[ADMIN]":
              return (this.props.history.push('/admin/pradzia'))
              break;
            default:
              return (alert("Neturite prisijungimo teisių."))
              break;
          }
        })
        .catch((error) => {
          if (error.response.status === 401) { console.log(error);
            alert('Slaptažodis ir/arba el.paštas neteisingi!');
          }
        });
    event.preventDefault();
  };

  render() {
    return (
        <div className="container mt-5">
          <LoginFormPresentation
              email={this.state.email}
              password={this.state.password}
              onEmailChange={this.onEmailChange}
              onPasswdChange={this.onPasswdChange}
              onSubmit={this.onSubmit}
          />
        </div>
    );
  }
}
export default withRouter(LoginFormContainer);