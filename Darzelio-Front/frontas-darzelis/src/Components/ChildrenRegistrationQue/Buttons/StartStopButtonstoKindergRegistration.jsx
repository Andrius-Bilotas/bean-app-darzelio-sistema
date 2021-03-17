import React, { useState } from 'react';
import { ModalForStopRegToKindergButton } from '../../Modal/ModalForStopRegToKindergButton';
import axios from 'axios';
import { API } from '../../../Configuration/AppConfig';

export const StartStopButtonstoKindergRegistration = ({
  isActive,
  fetchData,
}) => {
  const [isOpen, setIsOpen] = useState(isActive);

  const handleStartClick = () => {
    axios
      .post(`${API}/api/kindergartens/admission/activate`)
      .then(() => {
        fetchData();
        alert('Sėkmingai paleista registracija į darželius.');
      })
      .catch((error) => console.log(error));
  };
  const handleStopClick = () => {
    axios
      .post(`${API}/api/kindergartens/admission/deactivate`)
      .then(() => {
        fetchData();
        console.log();
      })
      .catch((error) => console.log(error));
  };
  return (
    <div className="pt-3">
      <div></div>
      <button
        type="button"
        className="mr-4 btn text-nowrap"
        onClick={handleStartClick}
      >
        Paleisti registraciją į darželius
      </button>
      <button
        className="mr-4 btn btn-danger"
        data-toggle="modal"
        data-target={`#staticBackdrop`}
      >
        Stabdyti registraciją į darželius
      </button>
      <ModalForStopRegToKindergButton onClick={handleStopClick} />
    </div>
  );
};
