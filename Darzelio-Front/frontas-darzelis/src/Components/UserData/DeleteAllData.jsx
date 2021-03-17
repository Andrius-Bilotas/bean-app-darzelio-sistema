import { API } from '../../Configuration/AppConfig';
import axios from 'axios';
import ModalEverything from '../Modal/ModalEverything';
import React, { Component } from 'react';

export default class DeleteAllData extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  deleteEverything = () => {
    axios
      .delete(`${API}/api/users/delete`, {
        params: {
          eraseData: true,
        },
      })
      .then((res) => {
        alert('Jūsų paskyra ir duomenys ištrinti visam laikui!');
        localStorage.clear();
        this.props.history.push('/login');
      })
      // .catch((err) => console.log(err));
      .catch((err) => {});
  };
  render() {
    return (
      <div>
        <button
          className="btnData btn btn-light "
          data-toggle="modal"
          data-target={`#staticBackdropp`}
          //  value={this.state.userId}
        >
          Ištrinti mano paskyrą ir duomenis
        </button>
        <ModalEverything
          deleteEverything={this.deleteEverything}
          // userId={this.state.userId}
        />
      </div>
    );
  }
}

