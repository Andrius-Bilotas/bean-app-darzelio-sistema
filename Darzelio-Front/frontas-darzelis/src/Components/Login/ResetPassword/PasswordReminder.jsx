import React, {useState} from "react";
import {API} from "../../../Configuration/AppConfig";
import axios from "axios";
import {useHistory} from "react-router";

const PasswordReminder = () => {

const [email, setEmail] = useState();
    let history = useHistory();

    const handleChange = (event) => {
        setEmail(event.target.value);
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await axios
                .post(`${API}/api/users/resetpassword?email=${email}`, {email},
                    {
                        headers: { 'Content-type': 'application/x-www-form-urlencoded' },
                    });
            // console.log(response.data);
            if (response.data === 'Nuoroda slaptažodžio keitimui išsiųsta į nurodytą el. paštą'){
                alert('Jums išsiųstas el. laiškas su nuoroda slaptažodžiui atkurti.')
                history.push('/login');
            }
        } catch (error) {
            // console.log(error.response.data);
            if (error.response.data === 'Įvestas pašto adresas nerastas sistemoje') {
                alert('Tokio el. pašto adreso sistemoje nėra.')
            }
        }
    }

    return (
        <div className="container ">
            <form className="col-lg-5 mt-5 ml-auto mr-auto shadow p-3 mb-5 bg-white rounded" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="InputEmail">Elektroninio pašto adresas</label>
                    <input
                        type="email"
                        className="form-control"
                        id="InputEmail"
                        aria-describedby="emailHelp"
                        placeholder="vardas@mail.lt"
                        onChange={handleChange}
                        value={email}
                        pattern="^(([-\w\d]+)(\.[-\w\d]+)*@([-\w\d]+)(\.[-\w\d]+)*(\.([a-zA-Z]{2,5}|[\d]{1,3})){1,2})$"
                        onInvalid={(e) => {
                            e.target.setCustomValidity('Įvestas netinkamas el. pašto formatas.');
                        }}
                        onInput={e => e.target.setCustomValidity('')}
                        required
                    />
                    <small id="emailHelp" className="form-text text-muted">Įveskite el. paštą, kuriuo esate
                        prisiregistravęs/usi prie sistemos.</small>
                </div>
                <button type="submit" className="btn">Siųsti</button>
            </form>
        </div>
    )
}
export default PasswordReminder