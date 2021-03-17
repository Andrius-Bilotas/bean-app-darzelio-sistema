import React, { Component } from 'react';
import { Pagination } from '@material-ui/lab';
import LogsService from '../Utilities/LogsService';
import '../../Style/UsersLandings.css';
import '../../Style/style.css';

export default class UserLogsList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      logs: [],
      pageNumber: 1,
      totalPages: 0,
      totalLogs: 0,
      searchEmail: '',
      sortByDate: 'datedesc',
    };
  }

  componentDidMount = () => {
    this.retrieveUsersLogsList();
  };

  retrieveUsersLogsList = () => {
    const { pageNumber, searchEmail, sortByDate } = this.state;

    LogsService.getAll(pageNumber, searchEmail, sortByDate)
      .then((res) => {
        this.setState({
          logs: res.data.logs,
          totalPages: res.data.totalPages,
          totalLogs: res.data.totalLogs,
        });
      })
    // .catch((err) => console.log(err));
    .catch((err) =>  {});
  };
  onChange = (event) => {
    const searchEmail = event.target.value;
    this.setState(
      {
        searchEmail: searchEmail,
      },
      () => {
        this.retrieveUsersLogsList();
      }
    );
  };

  handlePageChange = (event, value) => {
    this.setState(
      {
        pageNumber: value,
      },
      () => {
        this.retrieveUsersLogsList();
      }
    );
  };
  handleSortByDate = (event) => {
    event.preventDefault();
    
      this.state.sortByDate === 'datedesc'
        ? this.setState(
            {
              sortByDate: 'dateasc',
            },
            () => {
              this.retrieveUsersLogsList();
            }
          )
        : this.setState(
            {
              sortByDate: 'datedesc',
            },
            () => {
              this.retrieveUsersLogsList();
            }
          );
    
  };

  render() {
    let rowNumber = 20 * this.state.pageNumber - 19;
    return (
      <div className="container mt-5">
        <div className="mb-4">
          <h4> Vartotojų įvykių žurnalas</h4>
        </div>
        <div className="mb-4">
         
            <input
              type="text"
              className="form-control mt-3 col-4"
              placeholder="Ieškoti pagal vartotojo el.paštą"
              name="searchEmail"
              value={this.state.searchEmail}
              onChange={this.onChange}
            />
      
        </div>
        <table className="table mt-4">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Vartotojas</th>
              <th scope="col">Rolė</th>
              <th scope="col">Veiksmas</th>
              <th
                scope="col"
                onClick={this.handleSortByDate}
                className="dateTime"
              >
                Data ir laikas
              </th>
            </tr>
          </thead>
          <tbody>
            {this.state.logs && this.state.logs.length > 0 ? (
              this.state.logs.map(({ id, user, role, action, date }, index) => {
                return (
                  <tr key={id}>
                    <th scope="row">{rowNumber++}</th>
                    {/* <th scope="row">{index + 1}</th> */}
                    <td>{user}</td>
                    <td>{role}</td>
                    <td>{action}</td>
                    <td>{date}</td>
                  </tr>
                );
              })
            ) : (
              <tr>
                <td>...</td>
              </tr>
            )}
          </tbody>
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
