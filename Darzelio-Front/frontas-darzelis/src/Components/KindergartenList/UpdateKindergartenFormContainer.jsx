import React, { Component } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';

export default class UpdateKindergartenFormContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: '',
      name: '',
      address: '',
      spotsInFirstAgeGroup: '',
      spotsInSecondAgeGroup: '',
      role: '',
      errors: {
        name: '',
        address: '',
        spotsInFirstAgeGroup: '',
        spotsInSecondAgeGroup: '',
      },
    };
  }
  componentDidMount() {
    axios
      .get(`${API}/api/kindergartens/${this.props.match.params.id}`)
      .then((res) => {
        this.setState({
          id: res.data.id,
          name: res.data.name,
          address: res.data.address,
          spotsInFirstAgeGroup: res.data.spotsInFirstAgeGroup,
          spotsInSecondAgeGroup: res.data.spotsInSecondAgeGroup,
        });
        return axios.get(`${API}/api/users/loggedrole`);
      })
      .then((res) => {
        this.setState({
          role: res.data,
        });
      })
      // .catch((err) => console.log(err));
      .catch((err) =>  {
        if (err.response.status === 403) {
          alert(
            'Jūs neturite prieigos teisių į šitą puslapį. jei manote, kad tai klaida - prisijunkite iš naujo'
          );}
      });
     
  }
  handleChange = (event) => {
    event.preventDefault();
    const { name, value } = event.target;
    const errors = this.state.errors;
    const lettersAndNumbers = /^[A-Za-ząčęėįšųūžĄČĘĖĮŠŲŪŽ 0-9 -/./,/]+$/;
    const numbers = /^[0-9]+$/;

    switch (name) {
      case 'name':
        errors.name =
          !value.match(lettersAndNumbers) ||
          value.length < 2 ||
          value.length === 0
            ? 'Pavadinimas turi būti ilgesnis nei 1 raidė! '
            : '';
        break;
      case 'address':
        errors.address =
          !value.match(lettersAndNumbers) ||
          value.length < 2 ||
          value.length === 0
            ? 'Adresas turi būti ilgesnis nei 1 raidė!'
            : '';
        break;

      case 'spotsInFirstAgeGroup':
        errors.spotsInFirstAgeGroup =
          !value.match(numbers) || value.length < 0
            ? 'Pasirinkite laisvų vietų skaičių grupėje!'
            : '';
        break;
      case 'spotsInSecondAgeGroup':
        errors.spotsInSecondAgeGroup =
          !value.match(numbers) || value.length < 0
            ? 'Pasirinkite laisvų vietų skaičių grupėje!'
            : '';
        break;
      default:
        break;
    }

    this.setState({ errors, [name]: value }, () => {
      // console.log(errors);
    });
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
        .put(`${API}/api/kindergartens/${this.state.id}`, {
          id: this.state.id,
          name: this.state.name,
          address: this.state.address,
          spotsInFirstAgeGroup: this.state.spotsInFirstAgeGroup,
          spotsInSecondAgeGroup: this.state.spotsInSecondAgeGroup,
        })
        .then((response) => {
          if (this.state.role === 'EDU') {
            alert('Atnaujinta!');
            this.props.history.push('/admin/edu/darzeliai');
          } else if (this.state.role === 'ADMIN') {
            alert('Atnaujinta!');
            this.props.history.push('/admin/darzeliai');
          }
        })

        .catch((error) => {
          if (error.response.data.message === 'Item already exists') {
            alert('Toks pavadinimas jau egzistuoja! ');
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
            <h3>Atnaujinti darželio duomenis</h3>
          </div>
          <form onSubmit={this.handleSubmit} noValidate className="form-group ">
            <div className="mb-3">
              <label htmlFor="name" className="control-label">
                Darželio pavadinimas*:
              </label>
              <input
                type="text"
                className="form-control"
                name="name"
                onChange={this.handleChange}
                noValidate
                value={this.state.name}
              />
              {errors.name.length > 0 && (
                <span className="error">{errors.name}</span>
              )}
            </div>
            <div className="mb-3">
              <label htmlFor="address" className="control-label">
                Darželio adresas*:
              </label>
              <input
                type="text"
                className="form-control"
                name="address"
                onChange={this.handleChange}
                noValidate
                value={this.state.address}
              />
              {errors.address.length > 0 && (
                <span className="error">{errors.address}</span>
              )}
            </div>
            <div className="mb-3">
              <label htmlFor="spotsInFirstAgeGroup" className="control-label">
                Darželio laisvos vietos 2-3m. amžiaus grupėje:
              </label>
              <input
                type="number"
                min="0"
                className="form-control"
                name="spotsInFirstAgeGroup"
                onChange={this.handleChange}
                noValidate
                value={this.state.spotsInFirstAgeGroup}
              />
              {errors.spotsInFirstAgeGroup.length > 0 && (
                <span className="error">{errors.spotsInFirstAgeGroup}</span>
              )}
            </div>
            <div className="mb-3">
              <label htmlFor="spotsInSecondAgeGroup" className="control-label">
                Darželio laisvos vietos 3-6m. amžiaus grupėje:
              </label>
              <input
                type="number"
                min="0"
                className="form-control"
                name="spotsInSecondAgeGroup"
                onChange={this.handleChange}
                noValidate
                value={this.state.spotsInSecondAgeGroup}
              />
              {errors.spotsInSecondAgeGroup.length > 0 && (
                <span className="error">{errors.spotsInSecondAgeGroup}</span>
              )}
            </div>

            <div> * - privalomi laukai</div>
            <div>
              <button type="submit" className="btn btn-success">
                Atnaujinti
              </button>
            </div>
          </form>
        </div>
      </div>
    );
  }
}
