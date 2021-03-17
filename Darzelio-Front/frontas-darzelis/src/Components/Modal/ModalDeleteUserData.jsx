import React from 'react';

const ModalDeleteUserData = ({ deleteData }) => {
  return (
    <div
      className="modal fade"
      id={`staticBackdrop`}
      data-backdrop="static"
      data-keyboard="false"
      tabIndex="-1"
      aria-labelledby="staticBackdropLabel"
      aria-hidden="true"
    >
      <div className="modal-dialog modal-dialog-centered">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="staticBackdropLabel">
              Ar tikrai norite ištrinti šią paskyrą?
            </h5>
            <button
              type="button"
              className="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div className="modal-body">
            {' '}
            Ištrynus paskyrą jos atkurti nebebus galimybės ir iškarto būsite
            atjungtas iš sistemos.
          </div>
          <div className="modal-footer">
            <button
              onClick={deleteData}
              type="button"
              data-toggle="modal"
              className=" yes btn "
              data-dismiss="modal"
            >
              Taip
            </button>
            <button
              type="button"
              className="btn btn-secondary"
              data-dismiss="modal"
            >
              Atšaukti
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ModalDeleteUserData;
