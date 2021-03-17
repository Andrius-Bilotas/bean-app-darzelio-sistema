import React, { Component, lazy } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';
import { Link } from 'react-router-dom';
import '../../Style/UsersLandings.css';
import UsersListService from '../Utilities/UsersListService';
import { Pagination } from '@material-ui/lab';
const ModalComponent = lazy(() => import('../Modal/ModalComponent'));

export default class UsersListTableContainer extends Component {
  constructor() {
    super();
    this.state = {
      users: [],
      pageNumber: 1,
      totalPages: 0,
      totalUsers: 0,
      searchEmail: '',
    };
  }

  componentDidMount = () => {
    this.retrieveUsersList();
  };
  retrieveUsersList = () => {
    const { pageNumber, searchEmail } = this.state;
    UsersListService.getAll(pageNumber, searchEmail)
      .then((res) => {
        this.setState({
          users: res.data.users,
          totalPages: res.data.totalPages,
          totalUsers: res.data.totalUsers,
        });
      })
      .catch((err) => console.log(err));
    // .catch((err) =>  {});
  };
  onChange = (event) => {
    const searchEmail = event.target.value;
    this.setState(
      {
        searchEmail: searchEmail,
      },
      () => {
        this.retrieveUsersList();
      }
    );
  };
  handlePageChange = (event, value) => {
    this.setState(
      {
        pageNumber: value,
      },
      () => {
        this.retrieveUsersList();
      }
    );
  };

  deleteUser = (event) => {
    event.preventDefault();
    axios
      .delete(`${API}/api/users/${event.target.value}`)
      .then(() => {
        this.retrieveUsersList();
      })
      // .catch((err) => console.log(err));
      .catch((err) => {});
  };

  render() {
    let rowNumber = 30 * this.state.pageNumber - 29;
    return (
      <div className="container mt-5">
        <Link to={`/admin/registracija`} className="btn btn-primary mb-5">
          Pridėti naują vartotoją
        </Link>
        <div className="mb-4">
          <h4> Vartotojų sąrašas</h4>
        </div>
        <div className="mb-4">
          <input
            className="form-control mt-3 col-4"
            placeholder="Paieška pagal el.paštą"
            type="text"
            name="searchTerm"
            value={this.state.searchEmail}
            onChange={this.onChange}
          />
        </div>

        <table className="table">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Vardas</th>
              <th scope="col">Pavardė</th>
              <th scope="col">El.paštas</th>
              <th scope="col">Rolė</th>
              <th scope="col">Atnaujinti vartotojo duomenis</th>
              <th scope="col">Ištrinti vartotoją ir jo duomenis</th>
              <th scope="col"></th>
            </tr>
          </thead>

          {this.state.users && this.state.users.length ? (
            this.state.users.map(({ id, firstname, lastname, email, role }) => {
              const roleLt =
                role === 'PARENT'
                  ? 'Tėvas/Globėjas'
                  : role === 'EDU'
                  ? 'Švietimo specialistas'
                  : role === 'ADMIN'
                  ? 'Sistemos administratorius'
                  : 'Nenurodyta';

              return (
                <tbody key={id}>
                  <tr key={id}>
                    <th scope="row">{rowNumber++}</th>
                    <td>{firstname}</td>
                    {role === 'PARENT' ? (
                      <td>
                        {' '}
                        <Link
                          className="text-decoration-none mr-3"
                          to={`/admin/duomenys/${id}`}
                        >
                          {lastname}{' '}
                        </Link>
                      </td>
                    ) : (
                      <td>{lastname}</td>
                    )}

                    <td> {email}</td>
                    <td>{roleLt}</td>

                    {role === 'ADMIN' ? (
                      <td> </td>
                    ) : (
                      <td>
                        <Link
                          className="text-decoration-none mr-3"
                          to={`/admin/vartotojai/${id}`}
                        >
                          Atnaujinti duomenis
                        </Link>
                      </td>
                    )}

                    {role === 'ADMIN' ? (
                      <td></td>
                    ) : (
                      <td>
                        <button
                          className=" btn btn-light"
                          data-toggle="modal"
                          data-target={`#staticBackdrop${id}`}
                          value={id}
                        >
                          Ištrinti
                        </button>
                      </td>
                    )}

                    <td>
                      <ModalComponent
                        userId={id}
                        email={email}
                        deleteUser={this.deleteUser}
                      />
                    </td>
                  </tr>
                </tbody>
              );
            })
          ) : (
            <tbody>
              <tr>
                <td>Loading...</td>
              </tr>
            </tbody>
          )}
        </table>
        <Pagination
          className="my-3"
          count={this.state.totalPages}
          page={this.state.pageNumber}
          siblingCount={1}
          boundaryCount={1}
          variant="outlined"
          shape="rounded"
          onChange={this.handlePageChange}
        />
      </div>
    );
  }
}
