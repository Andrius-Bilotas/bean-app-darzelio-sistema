import React, { Component } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';
import { NavLink } from 'react-router-dom';

export default class UpdateParentDetailsByAdmn extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userId: '',
      id: '',
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
      studying: '',
      studyingInstitution: '',
      hasDisability: '',
      declaredResidenceSameAsLiving: '',
      declaredCity: '',
      declaredStreet: '',
      declaredHouseNumber: '',
      declaredFlatNumber: '',

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
      .get(`${API}/api/users/${this.props.match.params.id}/parentdetails`)
      .then((res) => {
        this.setState({
          userId: this.props.match.params.id,
          id: res.data.id,
          email: res.data.email,
          firstname: res.data.firstname,
          lastname: res.data.lastname,
          phone: res.data.phone,
          personalCode: res.data.personalCode,
          city: res.data.city,
          street: res.data.street,
          houseNumber: res.data.houseNumber,
          flatNumber: res.data.flatNumber,
          numberOfKids: res.data.numberOfKids,
          studying: res.data.studying,
          studyingInstitution: res.data.studyingInstitution,
          hasDisability: res.data.hasDisability,
          declaredResidenceSameAsLiving: res.data.declaredResidenceSameAsLiving,
          declaredCity: res.data.declaredCity,
          declaredStreet: res.data.declaredStreet,
          declaredHouseNumber: res.data.declaredHouseNumber,
          declaredFlatNumber: res.data.declaredFlatNumber,
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
    let letters = /^[A-Za-z???????????????????????????????????? ]+$/;
    let streetValidation = /^[a-zA-Z????????????????????????????????????][ a-zA-Z????????????????????????????????????0-9 ,.\- ]*$/;
    let houseNumberValidation = /^[1-9][a-zA-Z 0-9 ]*$/;
    let validPhone = /^[+][3][7][0][6|5]+[0-9]+$/;
    let validPersonalCode = /^[3|4|5|6]+[0-9]+$/;
    let numbers = /^[0-9]+$/;
    switch (name) {
      case 'firstname':
        errors.firstname =
          !value.match(letters) || value.length < 2 || value.length === 0
            ? 'Vardas turi b??ti i?? raid??i?? ir ilgesnis nei 1 raid??! '
            : '';
        break;
      case 'email':
        errors.email =
          validEmailRegex.test(value) || value.length === 0
            ? ''
            : 'El.pa??tas netinkamas! Formato pvz.: vardas@mail.com';
        break;
      case 'lastname':
        errors.lastname =
          !value.match(letters) || value.length < 2 || value.length === 0
            ? 'Pavard?? turi b??ti i?? raid??i?? ir ilgesn?? nei 1 raid??! '
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
            ? 'Asmens kodo formatas: 49001011111 arba 39001011111 '
            : '';
        break;
      case 'street':
        errors.street =
          !value || !value.match(streetValidation) || value.length === 0
            ? '??ra??ykite gatv??!'
            : '';
        break;
      case 'city':
        errors.city =
          !value || !value.match(letters) || value.length === 0
            ? '??ra??ykite miest??'
            : '';
        break;
      case 'houseNumber':
        errors.houseNumber =
          !value || !value.match(houseNumberValidation) || value.length === 0
            ? '??ra??ykite namo numer??'
            : '';
        break;

      case 'numberOfKids':
        errors.numberOfKids =
          !value.match(numbers) || value.length < 0
            ? '??ra??ykite vaik?? skai??i??'
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
          // console.log(errors);
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
        .put(`${API}/api/users/${this.state.userId}/parentdetails/`, {
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
          declaredResidenceSameAsLiving: this.state
            .declaredResidenceSameAsLiving,
          declaredCity: this.state.declaredCity,
          declaredStreet: this.state.declaredStreet,
          declaredHouseNumber: this.state.declaredHouseNumber,
          declaredFlatNumber: this.state.declaredFlatNumber,
        })
        .then((response) => {
          // console.log(response);

          alert('T??vo/Glob??jo duomenys atnaujinti s??kmingai');
          this.props.history.push(`/admin/duomenys/${this.state.userId}`);
        })

        .catch((error) => {
          if (error.response.data === 'This personal code already exists') {
            alert(
              'Pasitikrinkite ar suved??te teisingus asmens kodus. Toks vaiko asmens kodas jau egzstuoja! '
            );
          } else if (error.response.data.message === 'Item already exists') {
            alert(
              'Pasitikrinkite ar suved??te teisingus asmens kodus. Toks asmens kodas jau egzistuoja'
            );
          } else if (
            error.response.data === 'T??vo/glob??jo duomenys neu??pildyti'
          ) {
            alert(
              error.response.data +
                ' Negalite atnaujinti duomen?? kol neu??pild??te T??vo/Glob??jo registracijos'
            );
          } else if (error.response.data === 'Toks asmens kodas jau u??imtas') {
            alert('Pasitikrinkite asmens kod??. ' + error.response.data);
          } else if (
            error.response.data === '??is asmens kodas jau egzistuoja sistemoje!'
          ) {
            alert(
              'Pasitikrinkite ar suved??te teisingus asmens kodus. ??is asmens kodas jau egzistuoja sistemoje!(toks tevo asmens kodas jau egzistuoja vaiku sistemoje)'
            );
          } else if (error.response.data.message === 'Invalid field entry') {
            alert('U??pildykite visus privalomus laukus!');
          } else if (
            error.response.data === `The birthdate can't be from the future`
          ) {
            alert('Gimimo data negali b??ti i?? ateities!');
          } else if (error.response.data.message === `Bad birthdate format`) {
            alert('Netinkamas datos formatas!');
          } else if (error.response.data === 'Parent registration not filled') {
            alert(
              'Atnaujinti duomen?? nepavyko. Pirmin?? t??vo registracijos forma neu??pildyta'
            );
          } else if (
            error.response.data.message === 'Parent registration not filled'
          ) {
            alert(
              'Atnaujinti duomen?? nepavyko. Pirmin?? t??vo registracijos forma neu??pildyta'
            );
          } else if (error.response.status === 400) {
            alert(
              'Atnaujinti duomen?? nepavyko! Pasitikrinkite ar pa??ym??jote bei u??pild??te laukus teisingai!'
            );
          }
          //console.log(error.response);
        });
    } else {
      alert(
        'Registracija nes??kminga! Pasitikrinkite ar pa??ym??jote bei u??pild??te laukus teisingai. '
      );
    }
  };
  render() {
    const { errors } = this.state;
    if (this.state.id > 0) {
      return (
        <div>
          <div className="container mt-5 shadow p-3 mb-5 bg-white rounded">
            <div className="mb-4">
              <h3>Anaujinti t??vo/glob??jo duomenis</h3>
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
                  value={this.state.firstname}
                />
                {errors.firstname.length > 0 && (
                  <span className="error">{errors.firstname}</span>
                )}
              </div>
              <div className=" form-group mb-3 col-6">
                <label htmlFor="lastname" className="control-label">
                  Pavard??*:
                </label>
                <input
                  type="text"
                  placeholder="Pavard??"
                  className="form-control"
                  name="lastname"
                  onChange={this.handleChange}
                  value={this.state.lastname}
                  noValidate
                />
                {errors.lastname.length > 0 && (
                  <span className="error">{errors.lastname}</span>
                )}
              </div>
              <div className="form-group mb-3 col-6">
                <label htmlFor="email" className="control-label">
                  El.pa??tas*:
                </label>
                <input
                  type="email"
                  placeholder="El.pa??tas"
                  className="form-control"
                  name="email"
                  onChange={this.handleChange}
                  value={this.state.email}
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
                  value={this.state.phone}
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
                  value={this.state.personalCode}
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
                  value={this.state.city}
                  noValidate
                />
                {errors.city.length > 0 && (
                  <span className="error">{errors.city}</span>
                )}
              </div>
              <div className="form-group mb-3 col-6">
                <label htmlFor="street" className="control-label">
                  Gatv??*:
                </label>
                <input
                  type="text"
                  placeholder="Gatv??"
                  className="form-control"
                  name="street"
                  onChange={this.handleChange}
                  value={this.state.street}
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
                  value={this.state.houseNumber}
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
                  min="1"
                  placeholder="Butas"
                  className="form-control"
                  name="flatNumber"
                  onChange={this.handleChange}
                  value={this.state.flatNumber}
                />
              </div>
              <div className="form-group mb-3 col-8">
                <label htmlFor="numberOfKids" className="control-label">
                  Kiek turite vaik??, kurie mokosi pagal bendrojo ugdymo lavinimo
                  programas?*:
                </label>
                <input
                  type="number"
                  min="1"
                  placeholder="Skai??ius"
                  className="form-control"
                  name="numberOfKids"
                  onChange={this.handleChange}
                  value={this.state.numberOfKids}
                  noValidate
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
                  value={this.state.studying}
                />
                <label htmlFor="studying" className="form-check-label">
                  Mokausi bendrojo lavinimo mokykloje
                </label>
              </div>

              {this.state.studying ? (
                <div className="form-group mb-3 col-6">
                  <label
                    htmlFor="studyingInstitution"
                    className="control-label"
                  >
                    Mokymosi ??staigos pavadinimas*:
                  </label>
                  <input
                    type="text"
                    placeholder="Mokymosi ??staiga"
                    className="form-control"
                    name="studyingInstitution"
                    onChange={this.handleChange}
                    value={this.state.studyingInstitution}
                    pattern="[a-zA-Z-z???????????????????????????????????? . - 0-9-]+"
                    onInvalid={(e) => {
                      e.target.setCustomValidity(
                        '??veskite mokymosi ??staigos pavadinim??.'
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
                  value={this.state.hasDisability}
                  noValidate
                />
                <label htmlFor="hasDisability" className="form-check-label">
                  Turiu ma??esn?? nei 40% darbingumo lyg??
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
                  value={this.state.declaredResidenceSameAsLiving}
                />
                <label
                  htmlFor="declaredResidenceSameAsLiving"
                  className="form-check-label"
                >
                  Jei deklaruota gyvenamoji vieta sutampa, pa??ym??kite.
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
                      value={this.state.declaredCity}
                      pattern="[a-zA-Z-z???????????????????????????????????? - ]+"
                      onInvalid={(e) => {
                        e.target.setCustomValidity(
                          '??veskite deklaruot?? miest?? tinkamu formatu.'
                        );
                      }}
                      onInput={(e) => e.target.setCustomValidity('')}
                      required
                    />
                  </div>
                  <div className="form-group mb-3 col-12">
                    <label htmlFor="declaredStreet" className="control-label">
                      Deklaruota gatv??*:
                    </label>
                    <input
                      type="text"
                      placeholder="Deklaruota gatv??"
                      className="form-control"
                      name="declaredStreet"
                      onChange={this.handleChange}
                      value={this.state.declaredStreet}
                      pattern="^[a-zA-z???????????????????????????????????? ]+[- a-zA-z????????????????????????????????????0-9 . -  ]*"
                      onInvalid={(e) => {
                        e.target.setCustomValidity(
                          '??veskite deklaruot?? gatv?? tinkamu formatu.'
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
                      value={this.state.declaredHouseNumber}
                      pattern="^[1-9]+[ a-zA-Z 0-9 ]*"
                      onInvalid={(e) => {
                        e.target.setCustomValidity(
                          '??veskite deklaruot?? namo numer?? tinkamu formatu.'
                        );
                      }}
                      onInput={(e) => e.target.setCustomValidity('')}
                      required
                    />
                  </div>
                  <div className="form-group mb-3 col-12">
                    <label
                      htmlFor="declaredFlatNumber"
                      className="control-label"
                    >
                      Deklaruotas butas:
                    </label>
                    <input
                      type="number"
                      min="1"
                      placeholder="Deklaruotas butas"
                      className="form-control"
                      name="declaredFlatNumber"
                      onChange={this.handleChange}
                      value={this.state.declaredFlatNumber}
                    />
                  </div>
                </div>
              )}
              <div className="form-group mb-3 col-6"> * - privalomi laukai</div>
              <div>
                <button
                  type="submit"
                  className="btn mt-5
                    "
                >
                  Atnaujinti
                </button>
              </div>
            </form>
          </div>
        </div>
      );
    } else {
      return (
        <div>
          <h5>
            ??is t??vas dar neu??pild?? t??vo/glob??jo duomen??, jei norite u??pildyti
            t??vo/glob??jo duomenis:
            <NavLink
              to={`/admin/tevo-registracija/${this.props.match.params.id}`}
              className="nav-link"
            >
              spauskite ??ia
            </NavLink>
          </h5>
        </div>
      );
    }
  }
}
