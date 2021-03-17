import axios from 'axios';
import React, { Component } from 'react';
import { API } from '../../Configuration/AppConfig';
import '../../Style/style.css';

axios.defaults.withCredentials = true; // leidžia dalintis cookies

export default class UploadPdfContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      children: [],
      //child id
      id: 0,
      firstname: '',
      lastname: '',
      pdf: '',
    };
  }

  getChildren = async () => {
    try {
      const { data } = await axios.get(
        `${API}/api/users/getloggeduserchildren`
      );
      return data;
    } catch (error) {
      // console.log(error);
    }
  };
  async componentDidMount() {
    const childrenData = await this.getChildren();

    let children = childrenData.map((child) => ({
      firstname: child.firstname,
      lastname: child.lastname,
      id: child.id,
    }));
    this.setState({
      children: children,
    });
  }

  handleChange = (event) => {
    this.setState({
      id: event.target.value,
    });
  };

  handleFile = (event) => {
    if (event.target.files[0] && event.target.files[0].size > 10485760) {
      alert(' PDF failo dydis negali viršyti 10 MB!');
    } else if (
      event.target.files[0] &&
      !event.target.files[0].type.match('pdf')
    ) {
      alert('Tik PDF formatas yra priimamas');
    } else {
      this.setState({ pdf: event.target.files[0] });
    }
  };
  handleSubmit = (event) => {
    event.preventDefault();
    let data = new FormData();
    data.append('data', this.state.pdf);
    data.append('id', this.state.id);

    axios
      .post(`${API}/api/users/pdf`, data)
      .then((res) => {
        alert('PDF įkeltas.');
        this.props.history.push('/tevai');
      })
      .catch((error) => {
        if (error.response === undefined) {
          alert('PDF failo dydis negali viršyti 10 MB!');
        } else if (error.response.data === 'Blogas failo formatas') {
          alert('Pasitikrinkite ar įkėlėte tinkamo pdf formato failą.');
        } else if (error.response.status === 400) {
          alert(
            'Nesėkmingas pdf įkelimas. Pasitikrinkite ar pažymėjote vaiką bei pasirinkote pdf failą.'
          );
        }

        //console.log(error.data);
      });
  };

  render() {
    const sortedChildren = [...this.state.children];

    sortedChildren.sort((a, b) => {
      if (a.firstname < b.firstname) {
        return -1;
      }
      if (a.firstname > b.firstname) {
        return 1;
      }
      return 0;
    });
    return (
      <div>
        <div className="container mt-5 shadow p-3 mb-5 bg-white rounded">
          <div className="mb-4">
            <h3>Įkelkite vaiko sveikatos pažymą PDF formatu</h3>
            <p>
              Įkelti galite vienam vaikui vieną dokumentą. Jei įkelsite tam
              pačiam vaikui antrą dokumentą, ankstesnis dokumentas bus
              anuliuojamas.
            </p>
          </div>
          <form
            onSubmit={this.handleSubmit}
            className="form-row"
            encType="multipart/form-data"
          >
            <div className="form-group mb-3 col-4">
              <label htmlFor="selectChild" className="control-label">
                Pasirinkite vaiką*:
              </label>
              <select
                className="form-control"
                id="selectChild"
                name="childId"
                onChange={this.handleChange}
                required
                onInvalid={(e) => {
                  e.target.setCustomValidity('Pasirinkite vaiką.');
                }}
                onInput={(e) => e.target.setCustomValidity('')}
              >
                <option value=""> </option>
                {sortedChildren.map((child) => (
                  <option key={child.id} value={child.id}>
                    {child.firstname + ' ' + child.lastname}
                  </option>
                ))}
              </select>
            </div>
            <div className=" fileUpload form-group mt-4 ml-1 mb-1 col-12">
              <label htmlFor="pdf" className="upload control-label">
                Įkelti dokumentą*
              </label>
              <input
                type="file"
                // accept=".pdf"
                accept="application/pdf"
                className=" file form-control"
                name="pdf"
                id="pdf"
                onChange={this.handleFile}
              />
              {this.state.pdf ? (
                <p className="mt-1">
                  <b>{this.state.pdf.name} </b>
                </p>
              ) : (
                <p></p>
              )}
            </div>
            <div className="form-group mb-3 col-12"> * - privalomi laukai</div>
            <div>
              <button type="submit" className="btn btn-success">
                Įkelti
              </button>
            </div>
          </form>
        </div>
      </div>
    );
  }
}
