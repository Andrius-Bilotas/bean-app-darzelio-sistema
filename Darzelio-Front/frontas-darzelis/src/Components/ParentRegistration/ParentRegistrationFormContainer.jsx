import React, { Component } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';
import '../../Style/style.css';
import { NavLink } from 'react-router-dom';
import UserService from '../../Configuration/UserService';
import { Link } from 'react-router-dom';
axios.defaults.withCredentials = true; // leidžia dalintis cookies

export default class ParentRegistrationFormContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      parentId: '',
      id: '',
      firstname: '',
      lastname: '',
      email: '',
      phone: '',
      personalCode: 0,
      city: '',
      street: '',
      houseNumber: '',
      flatNumber: '',
      numberOfKids: 0,
      studying: false,
      studyingInstitution: '',
      hasDisability: false,
      declaredResidenceSameAsLiving: false,
      declaredCity: '',
      declaredStreet: '',
      declaredHouseNumber: '',
      declaredFlatNumber: '',

      agree: false,
      agreeWihRules: false,
      errors: {
        firstname: '',
        lastname: '',
        email: '',
        phone: '',
        personalCode: '',
        city: '',
        street: '',
        houseNumber: '',
        flatNumber: '',
        numberOfKids: '',
        declaredCity: '',
        declaredStreet: '',
        declaredHouseNumber: '',
        declaredFlatNumber: '',
        studyingInstitution: '',
      },
    };
  }
  componentDidMount() {
    axios
      .get(`${API}/api/users/getparentdetails`)
      .then((res) => {
        this.setState({
          parentId: res.data.id,
        });

        return axios.get(`${API}/api/users/loggeduserid`);
      })
      .then((res) => {
        UserService.setId(res.data);
        this.setState({
          id: res.data,
        });
      })
     // .catch((err) => console.log(err));
     .catch((err) =>  {});
  }

  handleChange = (event) => {
    const validEmailRegex = RegExp(
      /^(([^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i
    );
    const { name, value } = event.target;

    let errors = this.state.errors;
    let letters = /^[A-Za-ząčęėįšųūžĄČĘĖĮŠŲŪŽ ]+$/;
    let streetValidation = /^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ][ a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ0-9 ,.\- ]*$/;

    let houseNumberValidation = /^[1-9][a-zA-Z 0-9 ]*$/;

    let validPhone = /^[+][3][7][0][6|5]+[0-9]+$/;
    let validPersonalCode = /^[3|4|5|6]+[0-9]+$/;
    let numbers = /^[0-9]+$/;
    switch (name) {
      case 'firstname':
        errors.firstname =
          !value.match(letters) || value.length < 2 || value.length === 0
            ? 'Vardas turi būti iš raidžių ir ilgesnis nei 1 raidė! '
            : '';
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
      case 'phone':
        errors.phone =
          !value.match(validPhone) ||
          value.length < 12 ||
          value.length > 12 ||
          value.length === 0
            ? 'Telefono numerio formatas +37061234567 arba +37051234567 '
            : '';
        break;
      case 'personalCode':
        errors.personalCode =
          !value.match(validPersonalCode) ||
          value.length < 11 ||
          value.length > 11 ||
          value.length === 0
            ? 'Asmens kodo formatas: 39001011111, 49001011111, 59001011111 arba 69001011111 '
            : '';
        break;
      case 'street':
        errors.street =
          !value || !value.match(streetValidation) || value.length === 0
            ? 'Įrašykite gatvę!'
            : '';
        break;
      case 'city':
        errors.city =
          !value || !value.match(letters) || value.length === 0
            ? 'Įrašykite miestą'
            : '';
        break;
      case 'houseNumber':
        errors.houseNumber =
          !value || !value.match(houseNumberValidation) || value.length === 0
            ? 'Įrašykite namo numerį'
            : '';
        break;
      case 'numberOfKids':
        errors.numberOfKids =
          !value.match(numbers) || value.length < 0
            ? 'Įrašykite vaikų skaičių'
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

    const inputParent = {
      id: this.state.id,
      firstname: this.state.firstname,
      lastname: this.state.lastname,
      email: this.state.email,
      phone: this.state.phone,
      personalCode: this.state.personalCode,
      city: this.state.city,
      street: this.state.street,
      houseNumber: this.state.houseNumber,
      flatNumber: this.state.flatNumber,
      numberOfKids: this.state.numberOfKids,
      studying: this.state.studying,
      studyingInstitution: this.state.studyingInstitution,
      hasDisability: this.state.hasDisability,
      declaredResidenceSameAsLiving: this.state.declaredResidenceSameAsLiving,
      declaredCity: this.state.declaredCity,
      declaredStreet: this.state.declaredStreet,
      declaredHouseNumber: this.state.declaredHouseNumber,
      declaredFlatNumber: this.state.declaredFlatNumber,
    };
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
        .post(`${API}/api/users/${this.state.id}/parentdetails`, inputParent)
        .then((response) => {
          alert('Tėvo/Globėjo registracija sėkminga');
          this.props.history.push('/tevai/vaikoregistracija');
        })
        .catch((error) => {
          if (error.response.data.message === 'Item already exists') {
            alert('Toks asmens kodas jau egzistuoja!');
          } else if (error.response.data.message === 'Invalid field entry') {
            alert('Užpildykite visus privalomus laukus!');
          } else if (error.response.data === 'Toks asmens kodas jau užimtas') {
            alert('Pasitikrinkite asmens kodus.' + error.response.data);
          } else if (
            error.response.data === 'Šis asmens kodas jau egzistuoja sistemoje!'
          ) {
            alert(
              'Pasitikrinkite ar suvedėte teisingus asmens kodus.' +
                error.response.data
            );
          } else if (error.response.data === 'Asmens kodas jau užimtas') {
            alert(
              'Pasitikrinkite ar suvedėte teisingus asmens kodus. ' +
                error.response.data
            );
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

    if (this.state.parentId > 0) {
      return (
        <div>
          <h5>
            Jūs jau užpildėte duomenis, jei norite juos peržiūrėti ir/arba
            redaguoti
            <NavLink to="/tevai/registracija/redaguoti" className="nav-link ">
              spauskite čia
            </NavLink>
          </h5>
        </div>
      );
    } else {
      return (
        <div className="container mt-5 shadow p-3 mb-5 bg-white rounded">
          <div className="mb-4">
            <h3>Užpildykite tėvo/globėjo duomenis</h3>
          </div>
          <form onSubmit={this.handleSubmit} className="form-row ">
            <div className=" form-group mb-3 col-6">
              <label htmlFor="firstname" className="control-label">
                Vardas*:
              </label>
              <input
                type="text"
                placeholder="Vardas"
                className="form-control"
                name="firstname"
                onChange={this.handleChange}
                noValidate
              />
              {errors.firstname.length > 0 && (
                <span className="error">{errors.firstname}</span>
              )}
            </div>
            <div className=" form-group mb-3 col-6">
              <label htmlFor="lastname" className="control-label">
                Pavardė*:
              </label>
              <input
                type="text"
                placeholder="Pavardė"
                className="form-control"
                name="lastname"
                onChange={this.handleChange}
                noValidate
              />
              {errors.lastname.length > 0 && (
                <span className="error">{errors.lastname}</span>
              )}
            </div>
            <div className="form-group mb-3 col-6">
              <label htmlFor="email" className="control-label">
                El.paštas*:
              </label>
              <input
                type="email"
                placeholder="El.paštas"
                className="form-control"
                name="email"
                onChange={this.handleChange}
                noValidate
              />
              {errors.email.length > 0 && (
                <span className="error">{errors.email}</span>
              )}
            </div>
            <div className="form-group mb-3 col-6">
              <label htmlFor="phone" className="control-label">
                Tel.nr*:
              </label>
              <input
                type="tel"
                placeholder="Tel.nr"
                className="form-control"
                name="phone"
                onChange={this.handleChange}
                noValidate
              />
              {errors.phone.length > 0 && (
                <span className="error">{errors.phone}</span>
              )}
            </div>
            <div className="form-group mb-3 col-6">
              <label htmlFor="personalCode" className="control-label">
                Asmens kodas*:
              </label>
              <input
                type="text"
                placeholder="Asmens kodas"
                className="form-control"
                name="personalCode"
                onChange={this.handleChange}
                noValidate
              />
              {errors.personalCode.length > 0 && (
                <span className="error">{errors.personalCode}</span>
              )}
            </div>
            <div className="form-group mb-3 col-6">
              <label htmlFor="city" className="control-label">
                Miestas*:
              </label>
              <input
                type="text"
                placeholder="Miestas"
                className="form-control"
                name="city"
                onChange={this.handleChange}
                noValidate
              />
              {errors.city.length > 0 && (
                <span className="error">{errors.city}</span>
              )}
            </div>
            <div className="form-group mb-3 col-6">
              <label htmlFor="street" className="control-label">
                Gatvė*:
              </label>
              <input
                type="text"
                placeholder="Gatvė"
                className="form-control"
                name="street"
                onChange={this.handleChange}
                noValidate
              />
              {errors.street.length > 0 && (
                <span className="error">{errors.street}</span>
              )}
            </div>

            <div className="form-group mb-3 col-6">
              <label htmlFor="houseNumber" className="control-label">
                Namo numeris*:
              </label>
              <input
                type="text"
                placeholder="Namo numeris"
                className="form-control"
                name="houseNumber"
                onChange={this.handleChange}
                noValidate
              />
              {errors.houseNumber.length > 0 && (
                <span className="error">{errors.houseNumber}</span>
              )}
            </div>
            <div className="form-group mb-3 col-4">
              <label htmlFor="flatNumber" className="control-label">
                Butas:
              </label>
              <input
                type="number"
                min="0"
                placeholder="Butas"
                className="form-control"
                name="flatNumber"
                onChange={this.handleChange}
              />
            </div>
            <div className="form-group mb-3 col-8">
              <label htmlFor="numberOfKids" className="control-label">
                Kiek turite vaikų, kurie mokosi pagal bendrojo ugdymo lavinimo
                programas?*:
              </label>
              <input
                type="number"
                min="1"
                placeholder="Skaičius"
                className="form-control"
                name="numberOfKids"
                onChange={this.handleChange}
                noValidate
                onInvalid={(e) => {
                  e.target.setCustomValidity('Įveskite vaikų skaičių.');
                }}
                onInput={(e) => e.target.setCustomValidity('')}
                required
              />
              {errors.numberOfKids.length > 0 && (
                <span className="error">{errors.numberOfKids}</span>
              )}
            </div>

            <div className="ml-4 form-check mb-3 col-12">
              <input
                className="form-check-input"
                type="checkbox"
                name="studying"
                checked={this.state.studying}
                onChange={this.handleChange}
              />
              <label htmlFor="studying" className="form-check-label">
                Mokausi bendrojo lavinimo mokykloje
              </label>
            </div>

            {this.state.studying ? (
              <div className="form-group mb-3 col-6">
                <label htmlFor="studyingInstitution" className="control-label">
                  Mokymosi įstaigos pavadinimas*:
                </label>
                <input
                  type="text"
                  placeholder="Mokymosi įstaiga"
                  className="form-control"
                  name="studyingInstitution"
                  onChange={this.handleChange}
                  pattern="[a-zA-Z-ząčęėįšųūžĄČĘĖĮŠŲŪŽ . - 0-9-]+"
                  onInvalid={(e) => {
                    e.target.setCustomValidity(
                      'Įveskite mokymosi įstaigos pavadinimą.'
                    );
                  }}
                  onInput={(e) => e.target.setCustomValidity('')}
                  required
                />
              </div>
            ) : null}
            <div className=" ml-4 form-check form-group mb-3 col-10">
              <input
                type="checkbox"
                className="form-check-input"
                name="hasDisability"
                id="hasDisability"
                checked={this.state.hasDisability}
                onChange={this.handleChange}
                noValidate
              />
              <label htmlFor="hasDisability" className="form-check-label">
                Turiu mažesnį nei 40% darbingumo lygį
              </label>
            </div>

            <div className=" ml-4 form-check form-group mb-3 col-12">
              <input
                className="form-check-input"
                type="checkbox"
                checked={this.state.declaredResidenceSameAsLiving}
                name="declaredResidenceSameAsLiving"
                id="declaredResidenceSameAsLiving"
                onChange={this.handleChange}
              />
              <label
                htmlFor="declaredResidenceSameAsLiving"
                className="form-check-label"
              >
                Jei deklaruota gyvenamoji vieta sutampa, pažymėkite.
              </label>
            </div>
            {this.state.declaredResidenceSameAsLiving ? null : (
              <div className="form-row">
                <div className="form-group mb-3 col-12">
                  <label htmlFor="declaredCity" className="control-label">
                    Deklaruotas miestas*:
                  </label>
                  <input
                    type="text"
                    placeholder="Deklaruotas miestas"
                    className="form-control"
                    name="declaredCity"
                    onChange={this.handleChange}
                    pattern="[a-zA-Z-ząčęėįšųūžĄČĘĖĮŠŲŪŽ -]+"
                    onInvalid={(e) => {
                      e.target.setCustomValidity(
                        'Įveskite deklaruotą miestą tinkamu formatu.'
                      );
                    }}
                    onInput={(e) => e.target.setCustomValidity('')}
                    required
                  />
                </div>
                <div className="form-group mb-3 col-12">
                  <label htmlFor="declaredStreet" className="control-label">
                    Deklaruota gatvė*:
                  </label>
                  <input
                    type="text"
                    placeholder="Deklaruota gatvė"
                    className="form-control"
                    name="declaredStreet"
                    onChange={this.handleChange}
                    pattern="^[a-zA-ząčęėįšųūžĄČĘĖĮŠŲŪŽ ]+[- a-zA-ząčęėįšųūžĄČĘĖĮŠŲŪŽ0-9 . -  ]*"
                    onInvalid={(e) => {
                      e.target.setCustomValidity(
                        'Įveskite deklaruotą gatvę tinkamu formatu.'
                      );
                    }}
                    onInput={(e) => e.target.setCustomValidity('')}
                    required
                  />
                </div>

                <div className="form-group mb-3 col-12">
                  <label
                    htmlFor="declaredHouseNumber"
                    className="control-label"
                  >
                    Deklaruotas namo numeris*:
                  </label>
                  <input
                    type="text"
                    placeholder="Deklaruotas namo numeris"
                    className="form-control"
                    name="declaredHouseNumber"
                    onChange={this.handleChange}
                    pattern="^[1-9]+[ a-zA-Z 0-9 ]*"
                    onInvalid={(e) => {
                      e.target.setCustomValidity(
                        'Įveskite deklaruotą namo numerį tinkamu formatu, pvz.: 1A'
                      );
                    }}
                    onInput={(e) => e.target.setCustomValidity('')}
                    required
                  />
                </div>
                <div className="form-group mb-3 col-12">
                  <label htmlFor="declaredFlatNumber" className="control-label">
                    Deklaruotas butas:
                  </label>
                  <input
                    type="number"
                    min="1"
                    placeholder="Deklaruotas butas"
                    className="form-control"
                    name="declaredFlatNumber"
                    onChange={this.handleChange}
                  />
                </div>
              </div>
            )}
            <div className="ml-4 form-check mb-3 col-12">
              <input
                className="form-check-input"
                type="checkbox"
                name="agree"
                checked={this.state.agree}
                onChange={this.handleChange}
                required
                onInvalid={(e) => {
                  e.target.setCustomValidity('Su sąlygomis sutikti būtina!');
                }}
                onInput={(e) => e.target.setCustomValidity('')}
              />
              <label htmlFor="agree" className="form-check-label">
                <b>
                  Susipažinau ir sutinku su sąlygomis.{' '}
                  <Link to="/tevai/salygos" target="_blank">
                    {' '}
                    Sąlygos
                  </Link>
                </b>
                *
              </label>
            </div>
            <div className="ml-4 form-check mb-3 col-12">
              <input
                className="form-check-input"
                type="checkbox"
                name="agreeWihRules"
                checked={this.state.agreeWihRules}
                onChange={this.handleChange}
                required
                onInvalid={(e) => {
                  e.target.setCustomValidity(
                    'Su vaikų priėmimo tvarka sutikti būtina!'
                  );
                }}
                onInput={(e) => e.target.setCustomValidity('')}
              />
              <label htmlFor="agreeWihRules" className="form-check-label">
                <b>
                  Susipažinau ir sutinku su vaikų priėmimo tvarka bei duomenų
                  rinkimu.{' '}
                  <Link to="/tevai/tvarka" target="_blank">
                    {' '}
                    Vaikų priėmimo tvarka
                  </Link>
                </b>
                *
              </label>
            </div>
            <div className="form-group mb-3 col-6"> * - privalomi laukai</div>
            <div>
              <button type="submit" className="btn btn-lg mt-5 ">
                Tęsti
              </button>
            </div>
          </form>
        </div>
      );
    }
  }
}
