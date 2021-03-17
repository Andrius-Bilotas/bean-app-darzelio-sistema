import http from './http-common';

class LogsService {
  //rusiuoja pagal nutylejima pagal data zemyn
  getAll(pageNumber, email, sortByDate) {
    return http.get(`/api/users/logs?page=${pageNumber}&sortby=${sortByDate}&email=${email}`);
  }

  sortByDate(pageNumber, sortByDate) {
    return http.get(
      ` /api/users/logs?page=${pageNumber}&sortby=${sortByDate}`
    );
  }
  //tas pats
  sortByDateAsc(pageNumber) {
    return http.get(
      ` /api/users/logs?page=${pageNumber}&sortby=dateasc`
    );
  }

  sortByDateDesc(pageNumber) {
    return http.get(
      ` /api/users/logs?page=${pageNumber}&sortby=datedesc`
    );
  }


}
export default new LogsService();
