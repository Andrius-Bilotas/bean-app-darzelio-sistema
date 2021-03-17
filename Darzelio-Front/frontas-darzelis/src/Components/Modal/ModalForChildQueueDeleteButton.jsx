import React from 'react';

export const ModalForChildQueueDeleteButton = ({
  childId,
  firstname,
  lastname,
  onClick,
}) => {
  // console.log('modal child id: ' + childId);

  return (
    <div
      className="modal fade"
      id={`staticBackdrop${childId}`}
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
              {firstname} {lastname} 
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
          <div className="modal-body">Tikrai norite pašalinti šį vaiką iš eilės?</div>
          <div className="modal-footer">
            <button
              onClick={onClick}
              type="button"
              data-toggle="modal"
              className="btn btn-danger"
              data-dismiss="modal"
              value={childId}
            >
              Patvirtinti
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
