import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import UserService from '../../Configuration/UserService';
import { API } from '../../Configuration/AppConfig';

export default class UserData extends Component {
  handleClickParentEdit = () => {
    this.props.history.push('/tevai/duomenys/redaguoti');
  };
  handleClickParentPassword = () => {
    this.props.history.push('/tevai/duomenys/redaguoti/slaptazodi');
  };
  handleClickParentEmail = () => {
    this.props.history.push('/tevai/duomenys/redaguoti/pasta');
  };
  handleClickParentDelete = () => {
    this.props.history.push('/tevai/duomenys/istrinti');
  };

  handleClickEduEdit = () => {
    this.props.history.push('/admin/edu/duomenys/redaguoti');
  };
  handleClickEduPassword = () => {
    this.props.history.push('/admin/edu/duomenys/redaguoti/slaptazodi');
  };
  handleClickEduEmail = () => {
    this.props.history.push('/admin/edu/duomenys/redaguoti/pasta');
  };
  handleClickEduDelete = () => {
    this.props.history.push('/admin/edu/duomenys/istrinti');
  };

  handleClickAdminEdit = () => {
    this.props.history.push('/admin/naudotojo-duomenys/redaguoti');
  };
  handleClickAdminPassword = () => {
    this.props.history.push('/admin/naudotojo-duomenys/redaguoti/slaptazodi');
  };
  handleClickAdminEmail = () => {
    this.props.history.push('/admin/naudotojo-duomenys/redaguoti/pasta');
  };

  render() {
    const currentRole = UserService.getRole();
    if (currentRole === '[PARENT]') {
      return (
        <div>
          <h5>
            {' '}
            <button className="btn next" onClick={this.handleClickParentEdit}>
              Keisti duomenis
            </button>{' '}
          </h5>
          <h5>
            {' '}
            <button
              className="btn next"
              onClick={this.handleClickParentPassword}
            >
              Keisti slaptažodį
            </button>{' '}
          </h5>
          <h5>
            {' '}
            <button className="btn next" onClick={this.handleClickParentEmail}>
              Keisti el.paštą
            </button>{' '}
          </h5>

          <h5>
            {' '}
            <button className="btn next" onClick={this.handleClickParentDelete}>
              Ištrinti paskyrą
            </button>{' '}
          </h5>
          <h5>
            <a
              href={`${API}/api/users/userdata/download`}
              rel="noopener noreferrer"
              target="_blank"
              className="nav-link download"
            >
              Atsisiųsti savo paskyros duomenis
            </a>{' '}
          </h5>
        </div>
      );
    } else if (currentRole === '[EDU]') {
      return (
        <div>
          <h5>
            {' '}
            <button className="btn next" onClick={this.handleClickEduEdit}>
              Keisti duomenis
            </button>{' '}
          </h5>
          <h5>
            {' '}
            <button className="btn next" onClick={this.handleClickEduPassword}>
              Keisti slaptažodį
            </button>{' '}
          </h5>
          <h5>
            {' '}
            <button className="btn next" onClick={this.handleClickEduEmail}>
              Keisti el.paštą
            </button>{' '}
          </h5>

          <h5>
            {' '}
            <button className="btn next" onClick={this.handleClickEduDelete}>
              Ištrinti paskyrą
            </button>{' '}
          </h5>
          <h5>
            <a
              href={`${API}/api/users/userdata/download`}
              rel="noopener noreferrer"
              target="_blank"
              className="nav-link download"
            >
              Atsisiųsti savo paskyros duomenis
            </a>{' '}
          </h5>
        </div>
      );
    } else if (currentRole === '[ADMIN]') {
      return (
        <div>
          <h5>
            {' '}
            <button className="btn next" onClick={this.handleClickAdminEdit}>
              Keisti duomenis
            </button>{' '}
          </h5>
          <h5>
            {' '}
            <button
              className="btn next"
              onClick={this.handleClickAdminPassword}
            >
              Keisti slaptažodį
            </button>{' '}
          </h5>
          <h5>
            {' '}
            <button className="btn next" onClick={this.handleClickAdminEmail}>
              Keisti el.paštą
            </button>{' '}
          </h5>
        </div>
      );
    }
  }
}
