import React, { Component } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';
import '../../Style/style.css';

export default class UpdateUserDataFormContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: '',
      firstname: '',
      lastname: '',
      email: '',
      role: '',
      password: '',

      errors: {
        firstname: '',
        lastname: '',
        email: '',
      },
    };
  }
  componentDidMount() {
    axios
      .get(`${API}/api/users/loggeduserid`)
      .then((res) => {
        this.setState({
          id: res.data,
        });

        return axios.get(`${API}/api/users/${this.state.id}`);
      })
      .then((res) =>
        this.setState({
          id: res.data.id,
          firstname: res.data.firstname,
          lastname: res.data.lastname,
          email: res.data.email,
          role: res.data.role,
          password: res.data.password,
        })
      )
   // .catch((err) => console.log(err));
   .catch((err) =>  {});
  }

  handleChange = (event) => {
    const validEmailRegex = RegExp(
      /^(([^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i
    );
    const { name, value } = event.target;
    let errors = this.state.errors;
    let letters = /^[A-Za-ząčęėįšųūžĄČĘĖĮŠŲŪŽ]+$/;
    switch (name) {
      case 'firstname':
        errors.firstname =
          !value.match(letters) || value.length < 2
            ? 'Vardas turi būti iš raidžių ir ilgesnis nei 1 raidė!'
            : '';
        break;

      case 'email':
        errors.email = validEmailRegex.test(value)
          ? ''
          : 'El.paštas netinkamas!';
        break;
      case 'lastname':
        errors.lastname =
          !value.match(letters) || value.length < 2
            ? 'Pavardė turi būti iš raidžių ir ilgesnė nei 1 raidė!'
            : '';
        break;
      default:
        break;
    }

    if (event.target.type === 'checkbox') {
      this.setState({ [event.target.name]: event.target.checked });
    } else
      this.setState(
        {
          errors,
          [name]: value,
        },
        () => {
       
        }
      );
  };
  handleSubmit = (event) => {
    event.preventDefault();

    const validateForm = (errors) => {
      let valid = true;
      Object.values(errors).forEach(
        // if we have an error string set valid to false
        (val) => val.length > 0 && (valid = false)
      );
      return valid;
    };

    if (validateForm(this.state.errors)) {
      axios
        .put(
          `${API}/api/users/${this.state.id}`,

          {
            id: this.state.id,
            firstname: this.state.firstname,
            lastname: this.state.lastname,
            email: this.state.email,
            role: this.state.role,
            password: this.state.password,
          }
        )
        .then((response) => {
          alert('Duomenys atnaujinti sėkmingai!');
          if (this.state.role === 'PARENT') {
            this.props.history.push('/tevai/naudotojo-duomenys');
          } else if (this.state.role === 'EDU') {
            this.props.history.push('/admin/edu/naudotojo-duomenys');
          } else if (this.state.role === 'ADMIN') {
            this.props.history.push('/admin/naudotojo-duomenys');
          }
        })

        .catch((error) => {
          if (error.response.data.message === 'Item already exists') {
            alert('Toks el.paštas jau egzistuoja! ');
          } else if (error.response.data.message === 'Invalid field entry') {
            alert('Užpildykite visus laukus!');
          } else if (error.response.status === 400) {
            alert(
              'Nepavyko pakeisti! Pasitikrinkite ar pažymėjote bei užpildėte laukus teisingai!'
            );
          }
          // console.log(error);
        });
    } else {
      alert(
        'Nepavyko pakeisti! Pasitikrinkite ar pažymėjote bei užpildėte laukus teisingai. '
      );
    }
  };

  render() {
    const { errors } = this.state;
    return (
      <div className="container mt-5">
        <div className="col-lg-5 m-auto shadow p-3 mb-5 bg-white rounded">
          <div className="mb-4">
            <h3>Atnaujinti naudotojo duomenis</h3>
          </div>
          <form onSubmit={this.handleSubmit} noValidate className="form-group ">
            <div className="mb-3">
              <label htmlFor="firstname" className="control-label">
                Naudotojo vardas*:
              </label>
              <input
                type="text"
                className="form-control"
                name="firstname"
                onChange={this.handleChange}
                noValidate
                value={this.state.firstname}
              />
              {errors.firstname.length > 0 && (
                <span className="error">{errors.firstname}</span>
              )}
            </div>
            <div className="mb-3">
              <label htmlFor="lastname" className="control-label">
                Naudotojo pavardė*:
              </label>
              <input
                type="text"
                className="form-control"
                name="lastname"
                onChange={this.handleChange}
                noValidate
                value={this.state.lastname}
              />
              {errors.lastname.length > 0 && (
                <span className="error">{errors.lastname}</span>
              )}
            </div>

            <div> * - privalomi laukai</div>

            <div>
              <button type="submit" className="btn btn-success">
                Išsaugoti
              </button>
            </div>
          </form>
        </div>
      </div>
    );
  }
}
