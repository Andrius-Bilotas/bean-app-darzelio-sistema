import React from 'react';
import { useHistory } from 'react-router';
import '../../Style/style.css';
import '../../Style/UsersLandings.css';

const NextPage = () => {
  const history = useHistory();
  const addAnotherChild = (e) => {
    e.preventDefault();
    history.push('/tevai/vaikoregistracija');
  };
  const registerToKindergarten = (e) => {
    e.preventDefault();
    history.push('/tevai/registracija-i-darzeli');
  };

  return (
    <div className="container mt-5">
      <div className="">
        {' '}
        <button type="submit" className="btn next" onClick={addAnotherChild}>
          Pridėti kitą vaiką
        </button>
      </div>

      <div className="">
        {' '}
        <button
          type="submit"
          className="btn next"
          onClick={registerToKindergarten}
        >
          Tęsti
        </button>
      </div>
    </div>
  );
};

export default NextPage;
