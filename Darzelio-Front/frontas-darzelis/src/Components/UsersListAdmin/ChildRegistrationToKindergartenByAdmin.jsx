import React, { Component } from 'react';
import axios from 'axios';
import { API } from '../../Configuration/AppConfig';
import '../../Style/UsersLandings.css';

export default class ChildRegistrationToKindergartenByAdmin extends Component {
  constructor(props) {
    super(props);
    this.state = {
      childId: '',
      firstname: '',
      lastname: '',
      kindergartens: [],
      kindergarten_name: '',
      firstPriority: '',
      secondPriority: '',
      thirdPriority: '',
      fourthPriority: '',
      fifthPriority: '',
    };
  }
  getKindergartens = async () => {
    try {
      const { data } = await axios.get(`${API}/api/kindergartens`, {
        headers: { 'Content-type': 'application/x-www-form-urlencoded' },
      });
      return data;
    } catch (error) {
      // console.log(error.message);
    }
  };

  getChildren = async () => {
    try {
      const { data } = await axios.get(
        `${API}/api/users/${this.props.match.params.id}/parentdetails/children/${this.props.match.params.type}`
      );
      return data;
    } catch (error) {
      // console.log(error.message);
    }
  };

  async componentDidMount() {
    const kindergartensData = await this.getKindergartens();
    const childrenData = await this.getChildren();

    let kindergartens = kindergartensData.map((kindergarten) => ({
      name: kindergarten.name,
      id: kindergarten.id,
    }));

    this.setState({
      kindergartens: kindergartens,
      firstname: childrenData.firstname,
      lastname: childrenData.lastname,
      childId: childrenData.id,
    });
  }
  handleChange = (event) => {
    this.setState({
      ...this.state,
      [event.target.name]:
        event.target.name === 'childId'
          ? parseInt(event.target.value, 10)
          : event.target.value === '-'
          ? ''
          : event.target.value,
    });
  };

  handleSubmit = (e) => {
    e.preventDefault();
    let state = this.state;

    const dataLoad = {
      childId: state.childId,
      firstPriority: state.firstPriority,
      secondPriority: state.secondPriority,
      thirdPriority: state.thirdPriority,
      fourthPriority: state.fourthPriority,
      fifthPriority: state.fifthPriority,
      id: 0,
    };

    if (dataLoad.childId && dataLoad.firstPriority) {
      axios
        .post(`${API}/api/kindergartens/register`, dataLoad)
        .then((response) => {
          alert('Registracija sėkminga!');
          this.props.history.push(
            `/admin/duomenys/vaikai/${this.props.match.params.id}`
          );
        })
        .catch((error) => {
          // console.log(error.response.data);
          if (error.response.data === 'vaikas nerastas sistemoje') {
            alert('Tokio vaiko nėra, pasitikrinkite ar teisingi duomenys!');
          } else if (
            error.response.data === 'Šio vaiko registracija jau užpildyta!'
          ) {
            alert('Šio vaiko registracija jau užpildyta!');
          } else if (
            error.response.data === 'Registracijos šiuo metu užrakintos'
          ) {
            alert('Registracija šiuo metu nevyksta. Bandykite vėliau.');
          }
        });
    } else {
      alert('Patikrinkite ar visi duomenys teisingi!');
    }
  };

  render() {
    const { kindergartens } = this.state;

    return (
      <div className="container">
        {kindergartens && kindergartens.length > 0 ? (
          <form
            className="shadow p-3 mt-5 bg-white rounded"
            onSubmit={this.handleSubmit}
          >
            <div className="mb-3">
              <h4>Vaiko registracijos į darželį forma</h4>
            </div>
            <h5>
              {' '}
              Vaikas: {this.state.firstname} {this.state.lastname}
            </h5>

            <div className="input-group mb-3">
              <label
                className="input-group-text form-control"
                htmlFor="selectKindergarten1"
              >
                I prioriteto darželis
              </label>
              <select
                className="form-control"
                id="selectKindergarten1"
                onChange={this.handleChange}
                name="firstPriority"
              >
                <option defaultValue>-</option>
                {kindergartens.map((kindergarten, index) => {
                  return (
                    <option key={index} value={kindergarten.name}>
                      {kindergarten.name}
                    </option>
                  );
                })}
              </select>
            </div>
            <div className="input-group mb-3">
              <label
                className="input-group-text form-control"
                htmlFor="selectKindergarten2"
              >
                II prioriteto darželis
              </label>
              <select
                className="form-control"
                id="selectKindergarten2"
                name="secondPriority"
                onChange={this.handleChange}
              >
                <option defaultValue>-</option>
                {kindergartens.map((kindergarten, index) => {
                  return (
                    <option key={index} value={kindergarten.name}>
                      {kindergarten.name}
                    </option>
                  );
                })}
              </select>
            </div>
            <div className="input-group mb-3">
              <label
                className="input-group-text form-control"
                htmlFor="selectKindergarten3"
              >
                III prioriteto darželis
              </label>
              <select
                className="form-control"
                id="selectKindergarten3"
                name="thirdPriority"
                onChange={this.handleChange}
              >
                <option defaultValue>-</option>
                {kindergartens.map((kindergarten, index) => {
                  return (
                    <option key={index} value={kindergarten.name}>
                      {kindergarten.name}
                    </option>
                  );
                })}
              </select>
            </div>
            <div className="input-group mb-3">
              <label
                className="input-group-text form-control"
                htmlFor="selectKindergarten4"
              >
                IV prioriteto darželis
              </label>
              <select
                className="form-control"
                id="selectKindergarten4"
                name="fourthPriority"
                onChange={this.handleChange}
              >
                <option defaultValue>-</option>
                {kindergartens.map((kindergarten, index) => {
                  return (
                    <option key={index} value={kindergarten.name}>
                      {kindergarten.name}
                    </option>
                  );
                })}
              </select>
            </div>
            <div className="input-group mb-3">
              <label
                className="input-group-text form-control"
                htmlFor="selectKindergarten5"
              >
                V prioriteto darželis
              </label>
              <select
                className="form-control"
                id="selectKindergarten5"
                name="fifthPriority"
                onChange={this.handleChange}
              >
                <option defaultValue>-</option>
                {kindergartens.map((kindergarten, index) => {
                  return (
                    <option key={index} value={kindergarten.name}>
                      {kindergarten.name}
                    </option>
                  );
                })}
              </select>
            </div>
            <button type="submit" className="mr-4 btn">
              Pateikti
            </button>
          </form>
        ) : (
          <span>Loading...</span>
        )}
      </div>
    );
  }
}
