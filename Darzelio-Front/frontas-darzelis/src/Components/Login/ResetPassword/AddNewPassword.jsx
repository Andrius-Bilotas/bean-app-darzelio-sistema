import React, { Component } from 'react';

import { API } from '../../../Configuration/AppConfig';
import axios from 'axios';
import '../../../Style/style.css';


axios.defaults.withCredentials = true; // leidžia dalintis cookies

export default class AddNewPassword extends Component {
    constructor(props) {
        super(props);
        this.state = {
            token: '',
            newPassword: '',
            confirmNewPassword: '',

            errors: {
                newPassword: '',
                confirmNewPassword: '',
            },
        };
    }
     async componentDidMount() {
         const query = new URLSearchParams(this.props.location.search);
         const token = await query.get('token');

         this.setState({
             token: token,
         })
        //  console.log(token);
        //  console.log("token " + this.state.token);
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
            case 'confirmNewPassword':
                errors.confirmNewPassword =
                    value !== this.state.newPassword || value.length < 8
                        ? 'Slaptažodiai nesutampa'
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
        const validateForm = (errors) => {
            let valid = true;
            Object.values(errors).forEach((val) => val.length > 0 && (valid = false));
            return valid;
        };

        if (validateForm(this.state.errors)) {
            const payload = {
                newPassword: this.state.newPassword,
                confirmNewPassword: this.state.confirmNewPassword,
                token: this.state.token
            }
            // console.log(payload);
            axios
                .post(
                    `${API}/api/users/resetpasswordchange`, payload
                )
                .then((response) => {
                 
                    alert('Slaptažodis pakeistas sėkmingai!');
                        this.props.history.push('/login');
                })
                .catch((error) => {
                 
                    if (error.request.status === 400) {
                        alert('Slaptažodžio pakeisti nepavyko, bandykite iš naujo');
                    } else if (
                        error.request.data === 'Baigėsi slaptažodžio žymės (token) galiojimo laikas. Sukurkite naują prašymą atstatyti slaptažodį'
                    ) {
                        alert('Baigėsi slaptažodžio žymės galiojimo laikas. Sukurkite naują prašymą atstatyti slaptažodį');
                    } else if (error.request.data === 'Įvesti slaptažodžiai nesutapo. Patikrinkite ar teisingai patvirtinote naują slaptažodį') {
                        alert(error);
                    }
                    // console.log(error.request.status);
                });
        } else {
            console.error('Invalid Form');
            alert('Slaptažodžio pakeiti nepavyko, bandykite iš naujo');
        }
    };

    handleClick = () => {
        this.props.history.push('/login')
    }

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
                                    name="confirmNewPassword"
                                    onChange={this.handleChange}
                                    noValidate
                                    value={this.state.confirmNewPassword}
                                />
                                {errors.confirmNewPassword.length > 0 && (
                                    <span className="error">{errors.confirmNewPassword}</span>
                                )}
                            </div>
                        </div>

                        <div> * - privalomi laukai</div>

                        <div className="d-flex justify-content-between">
                            <button type="submit" className="btn">
                                Išsaugoti naują slaptažodį
                            </button>
                            <button className="btn btn-light" onClick={this.handleClick}>
                               Atšaukti
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

