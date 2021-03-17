import React, { Component } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';
import '../../Style/style.css';

export default class RegistrationFormContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      firstname: '',
      lastname: '',
      email: '',
      role: '',
      password: '',
    
      errors: {
        firstname: '',
        lastname: '',
        email: '',
        role: '',
      },
    };
  }

  handleChange = (event) => {
    event.preventDefault();

    const validEmailRegex = RegExp(
      /^(([^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i
    );
    const { name, value } = event.target;
    const errors = this.state.errors;
    const letters = /^[A-Za-ząčęėįšųūžĄČĘĖĮŠŲŪŽ -]+$/;

    switch (name) {
      case 'firstname':
        errors.firstname =
          !value.match(letters) || value.length < 2 || value.length === 0
            ? 'Vardas turi būti iš raidžių ir ilgesnis nei 1 raidė! '
            : '';
        break;
      case 'role':
        errors.role = !value || value.length === 0 ? 'Pasirinkite rolę!' : '';
        break;

      case 'email':
        errors.email =
          validEmailRegex.test(value) || value.length === 0
            ? ''
            : 'El.paštas netinkamas! Formato pvz.: vardas@mail.com';
        break;
      case 'lastname':
        errors.lastname =
          !value.match(letters) || value.length < 2 || value.length === 0
            ? 'Pavardė turi būti iš raidžių ir ilgesnė nei 1 raidė! '
            : '';
        break;
      default:
        break;
    }

    this.setState({ errors, [name]: value }, () => {
     
    });
  };
  handleSubmit = (event) => {
    event.preventDefault();
    const outputUser = {
      email: this.state.email,
      firstname: this.state.firstname,
      id: this.state.id,
      lastname: this.state.lastname,
      role: this.state.role,
      password: this.state.firstname,
     
    };
    const validateForm = (errors) => {
      let valid = true;
      Object.values(errors).forEach(
      
        (val) => val.length > 0 && (valid = false)
      );
      return valid;
    };

    if (validateForm(this.state.errors)) {
      axios
        .post(API + '/api/users', outputUser)
        .then((response) => {
          alert('Vartotojo registracija sėkminga!');
          this.props.history.push('/admin/vartotojai');
        })

        .catch((error) => {
          if (error.response.data.message === 'Item already exists') {
            alert('Toks el.paštas jau egzistuoja! ');
          } else if (error.response.data.message === 'Invalid field entry') {
            alert('Užpildykite visus laukus!');
          } else if (error.response.status === 400) {
            alert(
              'Registracija nesėkminga! Pasitikrinkite ar pažymėjote bei užpildėte laukus teisingai!'
            );
          }

       
        });
    } else {
      alert(
        'Registracija nesėkminga! Pasitikrinkite ar pažymėjote bei užpildėte laukus teisingai. '
      );
    }
  };
  render() {
    const { errors } = this.state;
    return (
      <div className="container mt-5">
        <div className="col-lg-5 m-auto shadow p-3 mb-5 bg-white rounded">
          <div className="mb-4">
            <h3>Užregistruoti naują vartotoją</h3>
          </div>
          <form onSubmit={this.handleSubmit} noValidate className="form-group ">
            <div className="mb-3">
              <label htmlFor="firstname" className="control-label">
                Vartotojo vardas*:
              </label>
              <input
                type="text"
                className="form-control"
                name="firstname"
                onChange={this.handleChange}
                noValidate
              />
              {errors.firstname.length > 0 && (
                <span className="error">{errors.firstname}</span>
              )}
            </div>
            <div className="mb-3">
              <label htmlFor="lastname" className="control-label">
                Vartotojo pavardė*:
              </label>
              <input
                type="text"
                className="form-control"
                name="lastname"
                onChange={this.handleChange}
                noValidate
              />
              {errors.lastname.length > 0 && (
                <span className="error">{errors.lastname}</span>
              )}
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="control-label">
                Vartotojo el.paštas*:
              </label>
              <input
                type="email"
                className="form-control"
                name="email"
                onChange={this.handleChange}
                noValidate
              />
              {errors.email.length > 0 && (
                <span className="error">{errors.email}</span>
              )}
            </div>
            <div className="mb-3">
              <label htmlFor="role" className="control-label">
                Parinkite rolę*:
              </label>
              <select
                type="role"
                className="form-control"
                name="role"
                onChange={this.handleChange}
                noValidate
              >
                <option value=""></option>
                <option value="PARENT">Tėvas/globėjas</option>
                <option value="EDU">Švietimo specialistas</option>
              </select>
              {errors.role.length > 0 && (
                <span className="error">{errors.role}</span>
              )}
            </div>
            <div> * - privalomi laukai</div>
            <div>
              <button type="submit" className="btn btn-success">
                Registruoti
              </button>
            </div>
          </form>
        </div>
      </div>
    );
  }
}
