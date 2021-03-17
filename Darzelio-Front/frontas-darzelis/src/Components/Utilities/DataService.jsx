import http from './http-common';

class DataService {
  // getAll(pageNumber) {
  //     return http.get(`/api/kindergartens/admission/registrations?page=${pageNumber}`)
  // }
  getAll(pageNumber, sort, lastname) {
    return http.get(
      `/api/kindergartens/admission/registrations?page=${pageNumber}&sortby=${sort}&lastname=${lastname}`
    );
  }

  getFromPage(pageNumber) {
    return http.get(
      `/api/kindergartens/admission/registrations?page=${pageNumber}`
    );
  }

  delete(childId) {
    return http.delete(
      `/api/kindergartens/admission/registrations/${childId}/delete`
    );
  }

  findByLastName(lastname, pageNumber) {
    return http.get(
      `/api/kindergartens/admission/registrations?page=${pageNumber}&sortby=&lastname=${lastname}`
    );
  }

  sortByAccepted(pageNumber) {
    return http.get(
      `/api/kindergartens/admission/registrations?page=${pageNumber}&sortby=accepted`
    );
  }

  sortByLastname(pageNumber) {
    return http.get(
      `/api/kindergartens/admission/registrations?page=${pageNumber}&sortby=lastname`
    );
  }

  confirm() {
    return http.post('/api/kindergartens/admission/registrations/confirm');
  }
  // get(id) {
  //     return http.get(`/tutorials/${id}`);
  // }
  //
  // create(data) {
  //     return http.post("/tutorials", data);
  // }
  //
  // update(id, data) {
  //     return http.put(`/tutorials/${id}`, data);
  // }
}
export default new DataService();
