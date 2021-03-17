import React, { Component } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';
import '../../Style/style.css';

axios.defaults.withCredentials = true; // leidžia dalintis cookies

export default class UpdateUserPasswordContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      // id: '',
      role: '',
      //currentPassword
      oldPassword: '',
      newPassword: '',
      confirmPassword: '',

      errors: {
        //slaptazodziu validacijos Didzioji Raide, mazoji raide, skaicius, 8 ilgis.
        //ar password = senam password
        //ar new = confirm
        oldPassword: '',
        newPassword: '',
        confirmPassword: '',
      },
    };
  }
  componentDidMount() {
    axios
      .get(`${API}/api/users/loggedrole`)
      .then((res) => {
        this.setState({
          role: res.data,
        });
      })
      // .catch((err) => console.log(err));
      .catch((err) => {});
  }

  handleChange = (event) => {
    event.preventDefault();
    const { name, value } = event.target;
    let errors = this.state.errors;

    //min 1 lowercase letter, min 1 uppercase letter,min 1 number, length min=8
    const newPasswordValidation = /^(?=.*[a-ząčęėįšųūž])(?=.*[A-ZĄČĘĖĮŠŲŪŽ])(?=.*\d)[a-ząčęėįšųūžA-ZĄČĘĖĮŠŲŪŽ\d]{8,}$/;
    switch (name) {
      case 'newPassword':
        errors.newPassword =
          !value.match(newPasswordValidation) || value.length < 8
            ? 'Naują slaptažodį turi sudaryti bent 1 mažoji raidė, bent 1 didžioji raidė, bent 1 skaičius, slaptažodžio ilgis ne trumpesnis nei 8 simboliai'
            : '';
        break;
      case 'confirmPassword':
        errors.confirmPassword =
          value !== this.state.newPassword || value.length < 8
            ? 'Slaptažodiai nesutampa'
            : '';
        break;
      case 'oldPassword':
        errors.oldPassword = value.length < 1 ? 'Slaptažodis per trumpas' : '';
        break;

      default:
        break;
    }
    this.setState({ errors, [name]: value }, () => {});
  };
  handleSubmit = (event) => {
    event.preventDefault();

    const validateForm = (errors) => {
      let valid = true;
      Object.values(errors).forEach((val) => val.length > 0 && (valid = false));
      return valid;
    };

    if (validateForm(this.state.errors)) {
      axios
        .post(
          `${API}/api/users/changePassword`,

          {
            // id: this.state.id,
            oldPassword: this.state.oldPassword,
            newPassword: this.state.newPassword,
            withCredentials: true,
          }
        )
        .then((response) => {
          alert('Duomenys atnaujinti sėkmingai!');
          if (this.state.role === 'PARENT') {
            this.props.history.push('/tevai');
          } else if (this.state.role === 'EDU') {
            this.props.history.push('/admin/edu/');
          } else if (this.state.role === 'ADMIN') {
            this.props.history.push('/admin/pradzia');
          }
        })
        .catch((error) => {
          if (error.response.data === 'Neteisingai įvestas senas slaptažodis') {
            alert(error.response.data);
          } else if (
            error.response.data === 'Senas ir naujas slaptažodis negali sutapti'
          ) {
            alert(error.response.data);
          } else if (error.response.status === 400) {
            alert('Nepavyko pakeisti slaptažodžio!');
          }
          // console.log(error.data);
        });
    } else {
      alert('Nepavyko pakeisti slaptažodžio! ');
    }
  };
  render() {
    const { errors } = this.state;
    return (
      <div className="container mt-5">
        <div className="col-lg-5 m-auto shadow p-3 mb-5 bg-white rounded">
          <div className="mb-4">
            <h3>Keisti slaptažodį</h3>
          </div>
          <form onSubmit={this.handleSubmit} noValidate className="form-group ">
            <div>
              <div className="mb-3">
                <label htmlFor="oldPassword" className="control-label">
                  Senas slaptažodis*:
                </label>
                <input
                  type="password"
                  className="form-control"
                  name="oldPassword"
                  onChange={this.handleChange}
                  noValidate
                  value={this.state.oldPassword}
                />
                {errors.oldPassword.length > 0 && (
                  <span className="error">{errors.oldPassword}</span>
                )}
              </div>
              <div className="mb-3">
                <label htmlFor="newPassword" className="control-label">
                  Naujas slaptažodis*:
                </label>
                <input
                  type="password"
                  className="form-control"
                  name="newPassword"
                  onChange={this.handleChange}
                  noValidate
                  value={this.state.newPassword}
                />
                {errors.newPassword.length > 0 && (
                  <span className="error">{errors.newPassword}</span>
                )}
              </div>
              <div className="mb-3">
                <label htmlFor="confirmPassword" className="control-label">
                  Patvirtinkite naują slaptažodį*:
                </label>
                <input
                  type="password"
                  className="form-control"
                  name="confirmPassword"
                  onChange={this.handleChange}
                  noValidate
                  value={this.state.confirmPassword}
                />
                {errors.confirmPassword.length > 0 && (
                  <span className="error">{errors.confirmPassword}</span>
                )}
              </div>
            </div>

            <div> * - privalomi laukai</div>

            <div>
              <button type="submit" className="btn btn-success">
                Išsaugoti naują slaptažodį
              </button>
            </div>
          </form>
        </div>
      </div>
    );
  }
}
