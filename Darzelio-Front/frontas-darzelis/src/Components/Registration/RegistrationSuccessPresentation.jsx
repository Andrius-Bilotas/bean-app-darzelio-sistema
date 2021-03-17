import React, { Component } from 'react';
import NavigationForAllPages from '../Utilities/NavigationForAllPages';

export default class RegistrationSuccessPresentation extends Component {
  render() {
    return (
      <div className="container mt-5">
        <NavigationForAllPages />
   
        <div className=" justify-content-center">
          <div className="col-10">
            <h5>Registracija sėkminga.</h5>
          </div>
        </div>
      </div>
    );
  }
}
