import React from 'react';
import { useHistory } from 'react-router';

const LogoutPresentation = () => {
  const history = useHistory();

  const logout = (e) => {
    e.preventDefault();
    localStorage.clear();
    history.push('/');
  };

  return (
    <div className="d-flex justify-content-end">
      <button className="btn" onClick={logout}>
        Atsijungti
      </button>
    </div>
  );
};
export default LogoutPresentation;
