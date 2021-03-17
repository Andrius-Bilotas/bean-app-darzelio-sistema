import React, { Component } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';
import '../../Style/UsersLandings.css';
import Loading from '../Loading/Loading';
const ChildrenListTablePresentation = React.lazy(() =>
  import('./ChildrenListTablePresentation')
);

export default class ChildrenListTableContainer extends Component {
  constructor() {
    super();
    this.state = {
      children: [],
      //userId
      id: '',
    };
  }

  componentDidMount() {
    axios
      .get(`${API}/api/users/loggeduserid`)
      .then((response) => {
        this.setState({
          id: response.data,
        });
        return axios.get(`${API}/api/users/getloggeduserchildren`);
      })
      .then((response) => {
        this.setState({
          children: response.data,
        });
      })
      .catch((error) => 
      { if (error.response.status === 403) {
        alert(
          'Jūs neturite prieigos teisių į šitą puslapį. jei manote, kad tai klaida - prisijunkite iš naujo'
        );}})
      // console.log(error));
  }
  render() {
    return (
      <div className="mt-5">
        <div className="mb-4">
          <h4>Vaikai</h4>
        </div>

        {this.state.children.length > 0 ? (
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
                <ChildrenListTablePresentation children={this.state.children} />
              </tbody>
            </table>
          </div>
        ) : (
          <Loading />
        )}
      </div>
    );
  }
}
