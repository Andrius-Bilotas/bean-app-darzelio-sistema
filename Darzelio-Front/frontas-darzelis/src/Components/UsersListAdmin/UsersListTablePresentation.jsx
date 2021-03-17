import React,  { lazy } from 'react';
import { Link } from 'react-router-dom';

import 'bootstrap/dist/js/bootstrap.bundle.min';
const ModalComponent = lazy(() => import('../Modal/ModalComponent'));

const UsersListTablePresentation = ({ users, deleteUser, searchTerm }) => {
  return users
    .filter(
      ({ id, firstname, lastname, email, role, markedForDeletion }, index) => {
        const roleLt =
          role === 'PARENT'
            ? 'Tėvas/Globėjas'
            : role === 'EDU'
            ? 'Švietimo specialistas'
            : role === 'ADMIN'
            ? 'Sistemos administratorius'
            : 'Nenurodyta';

        const markedForDeletionLt =
          markedForDeletion === true
            ? 'Ištrinti'
            : markedForDeletion === false
            ? '-'
            : 'nenurodyta';
        if (searchTerm === '') {
          return (
            { id, firstname, lastname, email, role, markedForDeletion }, index
          );
        } else if (email.toLowerCase().includes(searchTerm.toLowerCase())) {
          return (
            { id, firstname, lastname, email, role, markedForDeletion }, index
          );
        } else if (roleLt.toLowerCase().includes(searchTerm.toLowerCase())) {
          return (
            { id, firstname, lastname, email, role, markedForDeletion }, index
          );
        } else if (
          markedForDeletionLt.toLowerCase().includes(searchTerm.toLowerCase())
        ) {
          return (
            { id, firstname, lastname, email, role, markedForDeletion }, index
          );
        } else if (lastname.toLowerCase().includes(searchTerm.toLowerCase())) {
          return (
            { id, firstname, lastname, email, role, markedForDeletion }, index
          );
        } else if (firstname.toLowerCase().includes(searchTerm.toLowerCase())) {
          return (
            { id, firstname, lastname, email, role, markedForDeletion }, index
          );
        }
      }
    )
    .map(
      ({ id, firstname, lastname, email, role, markedForDeletion }, index) => {
        const roleLt =
          role === 'PARENT'
            ? 'Tėvas/Globėjas'
            : role === 'EDU'
            ? 'Švietimo specialistas'
            : role === 'ADMIN'
            ? 'Sistemos administratorius'
            : 'Nenurodyta';

        const markedForDeletionLt =
          markedForDeletion === true
            ? 'Ištrinti'
            : markedForDeletion === false
            ? '-'
            : 'nenurodyta';

        return (
          <tr key={id}>
            <th scope="row">{index + 1}</th>
            <td>{firstname}</td>
            <td> {lastname}</td>
            <td>{email}</td>
            <td>{roleLt}</td>

            <td>
              <Link
                className="text-decoration-none mr-3"
                to={`/admin/vartotojai/${id}`}
              >
                Atnaujinti duomenis
              </Link>
            </td>
            <td>{markedForDeletionLt}</td>
            <td>
              <button
                className="btn btn-light"
                data-toggle="modal"
                data-target={`#staticBackdrop${id}`}
                value={id}
              >
                Ištrinti vartotoją
              </button>
            </td>
            <td>
              <ModalComponent
                userId={id}
                email={email}
                deleteUser={deleteUser}
              />
            </td>
          </tr>
        );
      }
    );
};
export default UsersListTablePresentation;
