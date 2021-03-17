import React from "react";
import {withRouter} from "react-router";

import UserService from "../../Configuration/UserService";

const LoginSuccess = (props) => {

    const logOut = (event) => {
    event.preventDefault();
    UserService.deleteRole();
    props.history.push("/");
    }

    return(
        <div className="card">
            <div className="card-body">
                Sveikiname sėkmingai prisijungus!
            </div>
            <button
                type="button"
                className="btn btn-success"
                onClick={logOut}
            >
                Atsijungti
            </button>
        </div>
    )
}
export default withRouter (LoginSuccess);