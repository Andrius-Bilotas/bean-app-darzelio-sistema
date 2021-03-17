import React, { Component } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';

export default class EduAdminUpdateChildApplicationContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
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

      kindergartens: [],
    
    };
  }

  componentDidMount() {
    axios
      .get(`${API}/api/kindergartens/register/${this.props.match.params.id}`)
      .then((res) => {
        this.setState({
          childId: this.props.match.params.id,
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
          `${API}/api/kindergartens/admission/registrations/${this.props.match.params.id}`
        );
      })
      .then((res) => {
        this.setState({
          childFirstname: res.data.childFirstname,
          childLastname: res.data.childLastname,
        });
      })
      .catch((error) => 
      {})
      // console.log(error));
  }

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
        this.props.history.push(`/admin/edu/vaikai/${this.state.childId}`);
      })
      .catch((error) => {
        // console.log(error);
      });
  };
  render() {
    return (
      <div className="container">
        <form
          className="shadow p-3 mt-5 bg-white rounded"
          onSubmit={this.handleSubmit}
        >
          <div className="mb-3">
            <h4>Atnaujinkite vaiko registracijos į darželį formą</h4>
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
              onChange={this.handleChange}
              name="secondPriority"
            >
              <option value={this.state.secondPriority}>
                {' '}
                {this.state.secondPriority}
              </option>
              {this.state.kindergartens.map((kindergarten, index) => {
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
              IIi prioriteto darželis
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
              {this.state.kindergartens.map((kindergarten, index) => {
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
              onChange={this.handleChange}
              name="fourthPriority"
            >
              <option value={this.state.fourthPriority}>
                {' '}
                {this.state.fourthPriority}
              </option>
              {this.state.kindergartens.map((kindergarten, index) => {
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
              onChange={this.handleChange}
              name="fifthPriority"
            >
              <option value={this.state.fifthPriority}>
                {' '}
                {this.state.fifthPriority}
              </option>
              {this.state.kindergartens.map((kindergarten, index) => {
                return (
                  <option key={index} value={kindergarten.name}>
                    {kindergarten.name}
                  </option>
                );
              })}
            </select>
          </div>
          <button type="submit" className="mr-4 btn">
            Atnaujinti
          </button>
        </form>
      </div>
    );
  }
}
