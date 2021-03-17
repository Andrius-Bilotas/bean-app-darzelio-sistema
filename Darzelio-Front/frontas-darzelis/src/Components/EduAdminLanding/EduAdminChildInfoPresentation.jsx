import React from 'react';


const EduAdminChildInfoPresentation = ({
  childId,
  mainParentFirstname,
  mainParentLastname,
  mainParentEmail,
  mainParentPhone,
  mainParentAddress,
  mainParentNumberOfKids,
  mainParentStudying,
  mainParentStudyingInstitution,
  mainParentDisabled,
  childFirstname,
  childLastname,
  childAddress,
  childAdopted,
  childBirthdate,
  childRating,
  kindergartenName,
  secondParent,
  secondParentFirstname,
  secondParentLastname,
  secondParentNumberofKids,
  secondParentStudying,
  secondParentStudyingInstitution,
  secondParentDisabled,
  fifthPriority,
  firstPriority,
  fourthPriority,
  id,
  rating,
  secondPriority,
  thirdPriority,
}) => {
  const mokosi =
    mainParentStudying === true
      ? ' Taip'
      : mainParentStudying === false
      ? ' Ne'
      : ' Nenurodyta';

  const nedarbingumas =
    mainParentDisabled === true
      ? ' Taip'
      : mainParentDisabled === false
      ? ' Ne'
      : ' Nenurodyta';

  const ivaikintas =
    childAdopted === true
      ? ' Taip'
      : childAdopted === false
      ? ' Ne'
      : ' Nenurodyta';

  const mokosiAntrasis =
    secondParentStudying === true
      ? ' Taip'
      : secondParentStudying === false
      ? ' Ne'
      : ' Nenurodyta';

  const nedarbingumasAntrasis =
    secondParentDisabled === true
      ? ' Taip'
      : secondParentDisabled === false
      ? ' Ne'
      : ' Nenurodyta';
  return (
    <div>
      <h3 className="mb-4"> Vaiko duomenys:</h3>
      <p>
        {' '}
        <b> Vaiko vardas: </b> {childFirstname} {childLastname}
      </p>
      <p>
        {' '}
        <b>Vaiko gimimo data: </b> {childBirthdate}
      </p>
      <p>
        {' '}
        <b>Vaiko deklaruotas adresas: </b> {childAddress}
      </p>
      <p>
        {' '}
        <b>Vaiko konkursinis balas: </b> {childRating}
      </p>
      <p>
        {' '}
        <b>Darželio pavadinimas (jei vaikas priimtas): </b> {kindergartenName}
      </p>

      <p>
        <b>Šis vaikas yra įvaikintas: </b>
        {ivaikintas}
      </p>

      <h3 className="mb-4 mt-5"> Vaiko tėvų/globėjų duomenys:</h3>
      <p>
        {' '}
        <b>Tėvo/Globėjo vardas: </b> {mainParentFirstname} {mainParentLastname}
      </p>
      <p>
        {' '}
        <b>Tėvo/Globėjo el.paštas: </b> {mainParentEmail}
      </p>
      <p>
        {' '}
        <b>Tėvo/Globėjo Tel.nr:</b> {mainParentPhone}
      </p>
      <p>
        {' '}
        <b>Tėvo/Globėjo adresas:</b> {mainParentAddress}
      </p>
      <p>
        {' '}
        <b>Tėvo/Globėjo auginamų nepilnamečių vaikų skaičius: </b>{' '}
        {mainParentNumberOfKids}
      </p>
      <p>
        {' '}
        <b>Tėvas/Globėjas mokosi bendrojo lavinimo įstaigoje:</b> {mokosi}
      </p>
      {mainParentStudying ? (
        <p>
          {' '}
          <b> Mokymosi įstaigos pavadinimas: </b>{' '}
          {mainParentStudyingInstitution}
        </p>
      ) : null}
      <p>
        {' '}
        <b>Tėvas/Globėjas turi mažesnį nei 40% darbingumo lygį: </b>{' '}
        {nedarbingumas}
      </p>
      {secondParent ? (
        <div>
          <h5 className="mb-4 mt-4">Vaiko antrojo tėvo/globėjo duomenys: </h5>
          <p>
            <b>Vaiko antrojo tėvo/globėjo vardas: </b>
            {secondParentFirstname} {secondParentLastname}
          </p>
          <p>
            <b>
              Vaiko antrojo tėvo/globėjo auginamų nepilnamečių vaikų skaičius:{' '}
            </b>
            {secondParentNumberofKids}
          </p>
          <p>
            <b>
              Vaiko antrojo tėvas/globėjas mokosi bendrojo lavinimo įstaigoje:{' '}
            </b>
            {mokosiAntrasis}
          </p>
          {secondParentStudying ? (
            <p>
              {' '}
              <b>Mokymosi įstaigos pavadinimas: </b>{' '}
              {secondParentStudyingInstitution}
            </p>
          ) : null}
          <p>
            <b>
              {' '}
              Vaiko antrojo tėvas/globėjas turi mažesnį nei 40% darbingumo lygį:{' '}
            </b>

            {nedarbingumasAntrasis}
          </p>
          <p>
            <b>
              {' '}
              Vaiko antrojo tėvas/globėjas mokosi bendrojo lavinimo įstaigoje:
            </b>
            {mokosiAntrasis}
          </p>
        </div>
      ) : (
        <div>
          <h5> Antrojo tėvo/globėjo duomenų vartotojas nenurodė</h5>
        </div>
      )}
      <h3 className="mb-4 mt-5"> Vaiko darželių prioritetai:</h3>
      <div>
        <table className="table">
          <thead>
            <tr>
              <th scope="col">Pirmasis prioritetas</th>
              <th scope="col">Antrasis prioritetas</th>
              <th scope="col">Trečiasis prioritetas</th>
              <th scope="col">Ketvirtasis prioritetas</th>
              <th scope="col">Penktasis prioritetas</th>
              {/* <th scope="col">Redaguoti</th> */}
            </tr>
          </thead>
          <tbody>
            <tr key={childId}>
              <td>{firstPriority}</td>
              <td>{secondPriority}</td>
              <td>{thirdPriority}</td>
              <td>{fourthPriority}</td>
              <td>{fifthPriority}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
};
export default EduAdminChildInfoPresentation;
