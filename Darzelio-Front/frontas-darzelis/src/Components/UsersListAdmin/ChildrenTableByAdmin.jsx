import React, { Component, lazy } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';
import { NavLink } from 'react-router-dom';
import '../../Style/UsersLandings.css';
const ChildrenTablePresentation = lazy(() =>
  import('./ChildrenTablePresentationByAdmin')
);

export default class ChildrenTable extends Component {
  constructor() {
    super();
    this.state = {
      children: [],
      //(user id)
      userId: '',
      parentDetailsId: '',
    };
  }
  componentDidMount=()=> {
    let isMounted = true;
    axios
      .get(
        `${API}/api/users/${this.props.match.params.id}/parentdetails/children`
      )
      .then((res) => {
        this.setState({
          children: res.data,
          userId: this.props.match.params.id,
        });
        return axios.get(
          `${API}/api/users/${this.props.match.params.id}/parentdetails`
        );
      })
      .then((res) => {
        this.setState({
          parentDetailsId: res.data.id,
        });
      })
  // .catch((err) => console.log(err));
  .catch((err) =>  {});
  return () => {isMounted = false};
  }
  render() {
    return (
      <div className="mt-5">
        <div className="mb-4">
          <h4>Vaikai</h4>
        </div>
        <NavLink
          to={`/admin/vaiko-registracija/${this.props.match.params.id}`}
          className="btn btn-primary mb-5"
        >
          Pridėti šiam tėvui/globėjui vaiką
        </NavLink>
        {this.state.children.length > 0 &&
        this.state.children &&
        this.state.parentDetailsId > 0 ? (
          <div>
            <table className="table">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Vardas</th>
                  <th scope="col">Pavardė</th>
                  <th scope="col">Peržiūrėti/Atnaujinti vaiko duomenis</th>
                  <th scope="col">Peržiūrėti/Atnaujinti prašymą į darželį </th>
                </tr>
              </thead>

              <tbody>
                <ChildrenTablePresentation
                  children={this.state.children}
                  parentId={this.state.userId}
                />
              </tbody>
              {/* )}  */}
            </table>
          </div>
        ) : (
          <p> Tėvas/Globėjas dar nepateikė jokių duomenų apie savo vaikus.</p>
        )}
      </div>
    );
  }
}
