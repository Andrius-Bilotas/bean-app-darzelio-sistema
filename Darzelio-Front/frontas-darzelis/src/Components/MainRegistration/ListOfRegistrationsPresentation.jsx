import React from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

//our imports
import { API } from '../../Configuration/AppConfig';
import '../../Style/UsersLandings.css';
import { ModalForStopRegToKindergButton } from '../Modal/ModalForStopRegToKindergButton';

const ListOfRegistrationsPresentation = ({
  ChildrenAdmissionQues,
  onClick,
}) => {
  const handleEndClick = () => {
    axios
      .post(`${API}/api/kindergartens/stopadmission`)
      .then((response) => {
        // console.log(response.data);
      })
      .catch((error) => console.log(error));
  };

  return ChildrenAdmissionQues.map(
    (
      {
        id,
        active,
        allQuesConfirmed,
        startDate,
        endDate,
        registrationCount,
        totalSpotsInKindergartens,
      },
      index
    ) => {
      return (
        <tr key={id}>
          <th scope="row">{index + 1}</th>

          <td>{startDate}</td>
          <td>{endDate}</td>
          {active ? <td>Taip</td> : <td>Ne</td>}
          <td>{registrationCount}</td>
          <td>{totalSpotsInKindergartens}</td>
          {allQuesConfirmed ? <td>Taip</td> : <td>Ne</td>}
          <td>
            <Link
              className="btn btn-link-1"
              to={`/admin/edu/priemimai/${id}/eiles`}
            >
              Į sąrašą
            </Link>
          </td>
          <td>
            {active ? (
              <div>
                <button
                  className="mr-4 btn btn-danger"
                  data-toggle="modal"
                  data-target={`#staticBackdrop${id}`}
                  value={id}
                >
                  Stabdyti
                </button>
              </div>
            ) : null}
          </td>
          <td>
            <ModalForStopRegToKindergButton
              QueId={id}
              onClick={handleEndClick}
            />
          </td>
        </tr>
      );
    }
  );
};

export default ListOfRegistrationsPresentation;
