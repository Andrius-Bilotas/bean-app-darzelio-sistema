import React, { Component } from 'react';


export default class UserInformation extends Component {
  handleClickParent = () => {
    this.props.history.push(
      `/admin/duomenys/tevo/${this.props.match.params.id}`
    );
  };
  handleClickChild = () => {
    this.props.history.push(
      `/admin/duomenys/vaikai/${this.props.match.params.id}`
    );
  };
  render() {
    return (
      <div className="container mt-5">
        <div className="">
          <button className="btn next" onClick={this.handleClickParent}>
            {' '}
            Tėvo/Globėjo duomenys
          </button>
        </div>
        <div className="">
          <button className="btn next" onClick={this.handleClickChild}>
            {' '}
            Vaikų duomenys
          </button>
        </div>
      </div>
    );
  }
}
