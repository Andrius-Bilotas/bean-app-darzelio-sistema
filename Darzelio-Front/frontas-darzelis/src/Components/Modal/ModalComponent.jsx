import React from 'react';

const ModalComponent = ({ userId, email, deleteUser }) => {
  return (
    <div
      className="modal fade"
      id={`staticBackdrop${userId}`}
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
              {email}
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
          <div className="modal-body">Tikrai norite ištrinti šį vartotoją?</div>
          <div className="modal-footer">
            <button
              onClick={deleteUser}
              type="button"
              data-toggle="modal"
              className="btn btn-danger"
              data-dismiss="modal"
              value={userId}
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

export default ModalComponent;
