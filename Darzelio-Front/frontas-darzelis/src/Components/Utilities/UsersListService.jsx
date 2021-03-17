import http from './http-common';
class UsersListService {
  //rusiuoja pagal nutylejima pagal data zemyn
  getAll(pageNumber, email) {
    return http.get(`/api/users?page=${pageNumber}&email=${email}`);
  }
  // /api/users?page=pageNumber
  // arba /api/users?page=pageNumber&email=email
}
export default new UsersListService();
