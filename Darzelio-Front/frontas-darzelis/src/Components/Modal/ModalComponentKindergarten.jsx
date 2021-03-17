import React from 'react';

const ModalComponentKindergarten = ({
  kindergartenId,
  name,
  deleteKindergarten,
}) => {
  

  return (
    <div
      className="modal fade"
      id={`staticBackdrop${kindergartenId}`}
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
              {name}
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
          <div className="modal-body">Tikrai norite ištrinti šį darželį?</div>
          <div className="modal-footer">
            <button
              onClick={deleteKindergarten}
              type="button"
              data-toggle="modal"
              className="btn btn-danger"
              data-dismiss="modal"
              value={kindergartenId}
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

export default ModalComponentKindergarten;
