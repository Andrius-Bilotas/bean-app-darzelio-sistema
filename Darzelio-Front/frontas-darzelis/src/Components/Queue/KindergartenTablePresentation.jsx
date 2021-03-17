import React from 'react';
import { MDBDataTable } from 'mdbreact';
import '../../Style/style.css';
import '../../Style/LandingPage.css';

const KindergartenTable = ({ kindergartens }) => {
  const data = {
    columns: [
      {
        label: '#',
        field: 'id',
        sort: 'asc',
        width: 150,
      },
      {
        label: 'Darželio pavadinimas',
        field: 'kindergartenName',
        sort: 'asc',
        width: 270,
      },
      {
        label: 'Amžiaus grupė',
        field: 'ageGroup',
        sort: 'asc',
        width: 100,
      },
      {
        label: 'Registracijų skaičius',
        field: 'submissions',
        sort: 'asc',
        width: 150,
      },
      {
        label: 'Viso vietų darželyje',
        field: 'freeSpots',
        sort: 'asc',
        width: 100,
      },
    ],

    rows: kindergartens,
  };

  return (
    <MDBDataTable
      className="table "
      striped
      bordered
      small
      data={data}
      entriesLabel="Rodyti puslapyje"
      searchLabel="Paieška"
      paginationLabel={['Atgal', 'Kitas']}
      infoLabel={['Rodyti nuo', 'iki', 'rezultatų', '']}
     
    />
  );
};
export default KindergartenTable;
