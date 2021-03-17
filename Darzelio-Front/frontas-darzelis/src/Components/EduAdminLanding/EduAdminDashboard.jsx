import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { API } from '../../Configuration/AppConfig';
import { ModalForStopRegToKindergButton } from '../Modal/ModalForStopRegToKindergButton';
import EduPasswordChangeModal from '../Modal/EduPasswordChangeModal';

const EduAdminDashboard = () => {
  const [littleGroupsRegistrations, setLittleGroupsRegistrations] = useState(
    null
  );
  const [bigGroupsRegistrations, setBigGroupsRegistrations] = useState(null);
  const [totalSpotsInLittleGroups, setTotalSpotsInLittleGroups] = useState(
    null
  );
  const [totalSpotsInBigGroups, setTotalSpotsInBigGroups] = useState(null);
  const [isLocked, setIsLocked] = useState('');
  const [isActive, setIsActive] = useState('');
  const [passwordChanged, setPasswordChanged] = useState(true);
  const [modalState, setModalState] = useState(true);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = () => {
    axios
      .get(`${API}/api/kindergartens/admission/status`)
      .then((response) => {
        setLittleGroupsRegistrations(
          response.data.registrationsInFirstAgeGroup
        );
        setBigGroupsRegistrations(response.data.registrationsInSecondAgeGroup);
        setTotalSpotsInLittleGroups(response.data.spotsInFirstAgeGroups);
        setTotalSpotsInBigGroups(response.data.spotsInSecondAgeGroups);
        setIsLocked(response.data.locked);
        setIsActive(response.data.active);
        setPasswordChanged(response.data.passwordChanged);
      })
      .catch((error) => 
      {})
      // console.log(error));
  };

  const handleStartClick = () => {
    axios
      .post(`${API}/api/kindergartens/admission/activate`)
      .then((response) => {
        setIsActive(response.data);
        alert('Registracija į darželius paleista.');
      })
      .catch((error) => 
      {})
      // console.log(error));
  };
  const handleStopClick = () => {
    axios
      .post(`${API}/api/kindergartens/admission/deactivate`)
      .then((response) => {
        setIsActive(response.data);
        alert('Registracija į darželius sustabdyta.');
        window.location.reload();
      })

      .catch((error) => 
      {})
      // console.log(error));
  };

  const handleModalShow = () => {
    setModalState(false);
  };

  return (
    <div className="container mt-5">
      {passwordChanged ? (
        ''
      ) : (
        <EduPasswordChangeModal
          modalState={modalState}
          onClick={handleModalShow}
        />
      )}
      <div className="row">
        <div className="card col">
          <div className="card-body">
            {littleGroupsRegistrations < 0 ? (
              <p className="card-text">Duomenys atnaujinami</p>
            ) : (
              <h5 className="card-title pb-3">{littleGroupsRegistrations}</h5>
            )}
            <h6 className="card-subtitle mb-2 text-muted">
              2-3 m amžiaus vaikų grupėse
            </h6>
            <p>Viso registracijų į darželius</p>
          </div>
        </div>
        <div className="card col">
          <div className="card-body">
            {bigGroupsRegistrations < 0 ? (
              <p className="card-text">Duomenys atnaujinami</p>
            ) : (
              <h5 className="card-title pb-3">{bigGroupsRegistrations}</h5>
            )}
            <h6 className="card-subtitle mb-2 text-muted">
              3-6 m amžiaus vaikų grupėse
            </h6>
            <p>Viso registracijų į darželius</p>
          </div>
        </div>
        <div className="card col">
          <div className="card-body">
            {totalSpotsInLittleGroups < 0 ? (
              <p className="card-text">Duomenys atnaujinami</p>
            ) : (
              <h5 className="card-title pb-3">{totalSpotsInLittleGroups}</h5>
            )}
            <h6 className="card-subtitle mb-2 text-muted">
              2-3 m amžiaus vaikų grupėse
            </h6>
            <p>Viso vietų darželiuose</p>
          </div>
        </div>
        <div className="card col">
          <div className="card-body">
            {totalSpotsInBigGroups < 0 ? (
              <p className="card-text">Duomenys atnaujinami</p>
            ) : (
              <h5 className="card-title pb-3">{totalSpotsInBigGroups}</h5>
            )}
            <h6 className="card-subtitle mb-2 text-muted">
              3-6 m amžiaus vaikų grupėse
            </h6>
            <p>Viso vietų darželiuose</p>
          </div>
        </div>
      </div>
      <div className="statusas row mt-5 justify-content">
        <div className="col-8 shadow border rounded p-5">
          <div className="">
            <h5 className=" pb-3">
              Registracijų į darželius statusas:{' '}
              {isActive ? 'Registracija atidaryta' : 'Registracija sustabdyta'}
            </h5>
            {/*<h6 className="card-subtitle mb-2 text-muted">*/}
            {/*    </h6>*/}
            <button
              type="button"
              className="mr-4 btn"
              onClick={handleStartClick}
            >
              Paleisti registraciją į darželius
            </button>
            <button
              type="button"
              className="mr-4 btn"
              data-toggle="modal"
              data-target={`#staticBackdrop`}
            >
              Stabdyti registraciją į darželius
            </button>
            <ModalForStopRegToKindergButton onClick={handleStopClick} />
          </div>
        </div>

        <div className="col-4 shadow border rounded">
          <div className="pl-5 pt-4 mt-5 mb-5">
            {isLocked ? (
              <p className="card-title">Vaikų eilė į darželius užrakinta</p>
            ) : (
              ''
            )}
          </div>
        </div>
      </div>
    </div>
  );
};
export default EduAdminDashboard;
