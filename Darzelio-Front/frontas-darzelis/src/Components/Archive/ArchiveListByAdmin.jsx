import React, { Component } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';
import '../../Style/UsersLandings.css';

export default class ArchiveListByAdmin extends Component {
  constructor() {
    super();
    this.state = {
      archives: [],
      searchTerm: '',
    };
  }

  componentDidMount = () => {
    let isMounted = true;
    axios
      .get(API + '/api/users/archive')
      .then((response) => this.setState({ archives: response.data }))
      .catch((error) => {
        if (error.response.status === 403) {
          alert(
            'Jūs neturite prieigos teisių į šitą puslapį. jei manote, kad tai klaida - prisijunkite iš naujo'
          );}
      });
    return () => {isMounted = false};
  };

  handleSearch = (event) => {
    event.preventDefault();
    const searchTerm = event.target.value;
    this.setState({
      searchTerm: searchTerm,
    });
  };
  render() {
    let filteredArchives = this.state.archives.filter((archive) => {
      return archive.email.toLowerCase().indexOf(this.state.searchTerm) !== -1;
    });
    return (
      <div className="container mt-5">
        <div className="mb-4">
          <h4>
            {' '}
            Ištrintų tėvų/globėjų ir švietimo specialistų paskyrų archyvai
          </h4>
        </div>
        <div className="mb-4">
          <input
            className="form-control mt-3 col-4"
            placeholder="Paieška pagal el.paštą"
            type="text"
            name="searchTerm"
            value={this.state.searchTerm}
            onChange={this.handleSearch}
          />
          <table className="table mt-4">
            <thead>
              <tr>
                <th scope="col">#</th>

                <th scope="col">El.paštas</th>
                <th scope="col">Ištrynimo data</th>
                <th scope="col">Failo pavadinimas</th>
                <th scope="col">Atsisųsti</th>
              </tr>
            </thead>
            {this.state.archives && this.state.archives.length > 0 ? (
              filteredArchives.map(
                ({ id, deletionDate, email, filename }, index) => {
                  return (
                    <tbody key={id}>
                      <tr key={id}>
                        <th scope="row">{index + 1}</th>
                        <td>{email}</td>
                        <td> {deletionDate}</td>
                        <td> {filename}</td>
                        <td>
                          <a
                            href={`${API}/api/users/archive/${id}/download`}
                            rel="noopener noreferrer"
                            target="_blank"
                          >
                            Atsisųsti
                          </a>
                        </td>
                      </tr>
                    </tbody>
                  );
                }
              )
            ) : (
              <tbody>
                <tr>
                  <td>Loading...</td>
                </tr>
              </tbody>
            )}
          </table>
        </div>
      </div>
    );
  }
}
