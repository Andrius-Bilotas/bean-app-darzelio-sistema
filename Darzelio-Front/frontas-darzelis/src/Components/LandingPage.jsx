import React, {Component} from "react";
import LoginFormContainer from "../Components/Login/LoginFormContainer"

//style
import "../Style/LandingPage.css"

export default class LandingPage extends Component {

    render(){
        return(
            <main className="pt-5">
                <div className="text-center align-middle ">
                    <i className="fas fa-school"></i>
                    <h2 className="mt-4">Vaikų darželių informacinė sistema</h2>
                </div>
                <LoginFormContainer/>
            </main>

        )
    }
}