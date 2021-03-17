const UserService = {
  setRole: (role) => {
    localStorage.setItem('role', role);
  },

  getRole: () => {
    return localStorage.getItem('role') || '';
  },

  deleteRole: () => {
    localStorage.clear();
  },
  setId: (id) => {
    localStorage.setItem('id', id);
  },
  getId: () => {
    return localStorage.getItem('id') || '';
  },
};

export default UserService;
