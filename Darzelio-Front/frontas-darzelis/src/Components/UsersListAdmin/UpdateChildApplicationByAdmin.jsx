import React, { Component, lazy } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';
import { Link } from 'react-router-dom';

const ModalComponentChildren = lazy(() =>
  import('../Modal/ModalComponentChildren')
);

export default class UpdateChildApplicationByAdmin extends Component {
  constructor(props) {
    super(props);
    this.state = {
      // userId
      parentId: '',
      childId: '',
      fifthPriority: '',
      firstPriority: '',
      fourthPriority: '',
      id: '',
      rating: '',
      secondPriority: '',
      thirdPriority: '',

      //child info
      childFirstname: '',
      childLastname: '',
      kindergartenName: '',
      childRating: '',

      kindergartens: [],
    };
  }
  componentDidMount() {
    axios
      .get(`${API}/api/kindergartens/register/${this.props.match.params.type}`)
      .then((res) => {
        this.setState({
          parentId: this.props.match.params.id,
          childId: this.props.match.params.type,
          fifthPriority: res.data.fifthPriority,
          firstPriority: res.data.firstPriority,
          fourthPriority: res.data.fourthPriority,
          id: res.data.id,
          rating: res.data.rating,
          secondPriority: res.data.secondPriority,
          thirdPriority: res.data.thirdPriority,
        });
        return axios.get(`${API}/api/kindergartens`);
      })
      .then((res) => {
        this.setState({
          kindergartens: res.data,
        });
        return axios.get(
          `${API}/api/kindergartens/admission/registrations/${this.props.match.params.type}`
        );
      })
      .then((res) => {
        this.setState({
          childFirstname: res.data.childFirstname,
          childLastname: res.data.childLastname,
          kindergartenName: res.data.kindergartenName,
          childRating: res.data.childRating,
        });
      })
     // .catch((err) => console.log(err));
     .catch((err) =>  {});
  }
  deleteApplication = (event) => {
    axios
      .delete(`${API}/api/kindergartens/register/${this.state.childId}/delete`)
      .then(() => {
        alert('Prašymas buvo ištrintas');
        this.props.history.push(
          `/admin/duomenys/vaikai/${this.state.parentId}`
        );
      })
 // .catch((err) => console.log(err));
 .catch((err) =>  {});
  };
  handleChange = (event) => {
    event.preventDefault();
    this.setState({
      [event.target.name]: event.target.value,
    });
  };
  handleSubmit = (event) => {
    event.preventDefault();
    axios
      .put(`${API}/api/kindergartens/register/${this.state.childId}/update`, {
        childId: this.state.childId,
        fifthPriority: this.state.fifthPriority,
        firstPriority: this.state.firstPriority,
        fourthPriority: this.state.fourthPriority,
        id: this.state.id,
        rating: this.state.rating,
        secondPriority: this.state.secondPriority,
        thirdPriority: this.state.thirdPriority,
      })
      .then((res) => {
        alert('Vaiko prašymas atnaujintas!');
        this.props.history.push(
          `/admin/duomenys/vaikai/${this.state.parentId}`
        );
      })
      .catch((error) => {
        // console.log(error.data);
      });
  };
  render() {
    if (this.state.id > 0) {
      if (this.state.kindergartenName === '') {
        return (
          <div>
            <div className=" container  m-auto shadow p-3 mb-5 bg-white rounded">
              <form className=" p-3 mt-5 " onSubmit={this.handleSubmit}>
                <div className="mb-3">
                  <h4>Atnaujinti vaiko registracijos į darželį formą</h4>
                </div>
                <h5>
                  {' '}
                  Vaikas: {this.state.childFirstname} {this.state.childLastname}
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
                    <option value={this.state.firstPriority}>
                      {' '}
                      {this.state.firstPriority}
                    </option>
                    {this.state.kindergartens.map((kindergarten, index) => {
                      return (
                        <option key={index} value={kindergarten.name}>
                          {kindergarten.name}, {kindergarten.address}
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
                    onChange={this.handleChange}
                    name="secondPriority"
                  >
                    <option value={this.state.secondPriority}>
                      {' '}
                      {this.state.secondPriority}
                    </option>
                    <option defaultValue>-</option>
                    {this.state.kindergartens.map((kindergarten, index) => {
                      return (
                        <option key={index} value={kindergarten.name}>
                          {kindergarten.name}, {kindergarten.address}
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
                    onChange={this.handleChange}
                    name="thirdPriority"
                  >
                    <option value={this.state.thirdPriority}>
                      {' '}
                      {this.state.thirdPriority}
                    </option>
                    <option defaultValue>-</option>
                    {this.state.kindergartens.map((kindergarten, index) => {
                      return (
                        <option key={index} value={kindergarten.name}>
                          {kindergarten.name}, {kindergarten.address}
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
                    onChange={this.handleChange}
                    name="fourthPriority"
                  >
                    <option value={this.state.fourthPriority}>
                      {' '}
                      {this.state.fourthPriority}
                    </option>
                    <option defaultValue>-</option>
                    {this.state.kindergartens.map((kindergarten, index) => {
                      return (
                        <option key={index} value={kindergarten.name}>
                          {kindergarten.name}, {kindergarten.address}
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
                    onChange={this.handleChange}
                    name="fifthPriority"
                  >
                    <option value={this.state.fifthPriority}>
                      {' '}
                      {this.state.fifthPriority}
                    </option>
                    <option defaultValue>-</option>
                    {this.state.kindergartens.map((kindergarten, index) => {
                      return (
                        <option key={index} value={kindergarten.name}>
                          {kindergarten.name}, {kindergarten.address}
                        </option>
                      );
                    })}
                  </select>
                </div>
                <button type="submit" className="mr-4 btn update">
                  Atnaujinti
                </button>
              </form>
              <div className="deleteApplication ">
                <button
                 className="  btn mt-3"
                  id="deleteChildApplication"
                  data-toggle="modal"
                  data-target={`#staticBackdrop${this.state.childId}`}
                  value={this.state.childId}
                >
                  Ištrinti prašymą
                </button>
                <ModalComponentChildren
                  childId={this.state.childId}
                  firstname={this.state.childFirstname}
                  lastname={this.state.childLastname}
                  deleteChild={this.deleteApplication}
                />
              </div>
            </div>
          </div>
        );
      } else {
        return (
          <div>
            {' '}
            <p>
              {' '}
              Prašymo redaguoti negalite. Vaikas priimtas į darželį:{' '}
              <b>{this.state.kindergartenName}</b>, vaiko balas:{' '}
              <b>{this.state.childRating}</b>
            </p>
          </div>
        );
      }
    } else {
      return (
        <p>
          {' '}
          Šiam vaikui dar nėra užpildytas prašymas. Jei norite užpildyti prašymą
          į darželį{' '}
          <Link
            to={`/admin/${this.state.parentId}/registracija-i-darzeli/${this.state.childId}`}
          >
            spauskite čia.
          </Link>
        </p>
      );
    }
  }
}
