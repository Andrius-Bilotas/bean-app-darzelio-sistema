import React from 'react';
import UserService from '../../Configuration/UserService';
import { Link } from 'react-router-dom';

const NoMatch = (props) => {
  const currentRole = UserService.getRole();

  return (
    <div className="container">
      {currentRole === '[PARENT]' ? (
        <div className=" m-5 text-center ">
          Neteisingas maršrutas. <Link to="/tevai"> Atgal</Link>
        </div>
      ) : currentRole === '[EDU]' ? (
        <div className=" m-5 text-center ">
          Neteisingas maršrutas. <Link to="/admin/edu"> Atgal</Link>
        </div>
      ) : currentRole === '[ADMIN]' ? (
        <div className=" m-5 text-center ">
          Neteisingas maršrutas. <Link to="/admin/pradzia"> Atgal</Link>
        </div>
      ) : (
        <div className=" m-5 text-center ">
          Neteisingas maršrutas{' '}
          <Link to="/login"> Grįžti į prisijungimo puslapį</Link>
        </div>
      )}
    </div>
  );
};

export default NoMatch;
