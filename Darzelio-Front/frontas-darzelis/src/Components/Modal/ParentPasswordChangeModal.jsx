import React from "react";

const ParentPasswordChangeModal = ({modalState, onClick}) => {

    return (
        <div className={"modal fade" + (modalState ? " show d-block" : "d-none")} tabIndex="-1" role="dialog" id="myModal">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Jūsų slaptažodis nesaugus</h5>
                        <button type="button" className="close" data-dismiss="modal" onClick={onClick} aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div className="modal-body">
                        <p>Pasikeiskite slaptažodį į saugesnį.</p>
                    </div>
                    <div className="modal-footer">
                        <a href={`/bean-app/tevai/duomenys/redaguoti/slaptazodi`} type="button" className="btn">Keisti slaptažodį</a>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default ParentPasswordChangeModal