import React from 'react';
import { Link } from 'react-router-dom';
import { withRouter } from 'react-router';

const LoginFormPresentation = (
  { email, password, onPasswdChange, onEmailChange, onSubmit },
  context
) => {
  return (
    <div
      className="col-lg-5 m-auto shadow p-3 mb-5 bg-white rounded"
      onSubmit={onSubmit}
    >
      <div className="mb-4">
        <h3>Prisijungti</h3>
      </div>
      <form className="form-group">
        <div className="mb-3">
          <label htmlFor="exampleInputEmail1" className="control-label">
            Elektroninio pašto adresas
          </label>
          <input
            type="email"
            className="form-control"
            id="InputEmail"
            autoComplete="email"
            placeholder="vardas@mail.com"
            aria-describedby="emailHelp"
            onChange={onEmailChange}
            value={email}
            pattern="^(([-\w\d]+)(\.[-\w\d]+)*@([-\w\d]+)(\.[-\w\d]+)*(\.([a-zA-Z]{2,5}|[\d]{1,3})){1,2})$"
            onInvalid={(e) => {
              e.target.setCustomValidity(
                'Įvestas netinkamas el. pašto formatas.'
              );
            }}
            onInput={(e) => e.target.setCustomValidity('')}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="exampleInputPassword1" className="control-label">
            Slaptažodis
          </label>
          <input
            type="password"
            className="form-control"
            id="InputPassword"
            autoComplete="current-password"
            placeholder="********"
            onChange={onPasswdChange}
            value={password}
            onInvalid={(e) => {
              e.target.setCustomValidity('Pamiršote įvesti slaptažodį.');
            }}
            required
          />
        </div>

        <div className="align-items-center">
          <button type="submit" className="mr-4 btn">
            Prisijungti
          </button>
          <Link className="btn btn-link" to="/registracija">
            Naujo vartotojo registracija
          </Link>
        </div>
        <div className="mt-3">
          <Link to="/atstatytiSlaptazodi">Pamiršote slaptažodį?</Link>
        </div>
      </form>
      <div>
        <Link
          to="/instrukcija"
          rel="noopener noreferrer"
          target="_blank"
        >
          Naudojimosi instrukcija (tėvams/globėjams){' '}
        </Link>
      </div>
    </div>
  );
};

export default withRouter(LoginFormPresentation);
