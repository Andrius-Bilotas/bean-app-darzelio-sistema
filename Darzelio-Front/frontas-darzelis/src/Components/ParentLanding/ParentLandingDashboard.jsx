import React, {Component} from 'react';
import axios from "axios";
import {withRouter} from "react-router";
import {API} from "../../Configuration/AppConfig";
import ParentPasswordChangeModal from "../Modal/ParentPasswordChangeModal"
import "../../Style/ParentLandingDashboard.css"

class ParentLandingDashboard extends Component {
    state = {
        parentDetailsFilled: false,
        childRegistered: false,
        passwordChanged: true,
        admissionActive: true,
        children: [],
        modalState: true
    }

    componentDidMount = () => {
      
        axios
            .get(`${API}/api/users/status`)
            .then((response) => {
                // console.log(response.data);
                this.setState({
                    parentDetailsFilled: response.data.detailsFilled,
                    childRegistered: response.data.childRegistered,
                    children: response.data.children,
                    passwordChanged: response.data.passwordChanged,
                    admissionActive: response.data.admissionActive
                })
              
            })
            .catch((error) => {
                // console.log(error)
            })
    }

    handleShow = () => {
        this.setState({ modalState: !this.state.modalState });
    }

    render() {
        const {
            passwordChanged,
            parentDetailsFilled,
            childRegistered,
            children,
            admissionActive,
            modalState
        } = this.state
        return (
            <div className="mt-5 ml-3">
                {passwordChanged ? (''
                    ) :
                    (
                        <ParentPasswordChangeModal
                        modalState={modalState}
                        onClick={this.handleShow}/>
                    )
             
                }

                {admissionActive ? (''
                    ) :
                    (
                        <div className="alert alert-warning alert-dismissible fade show shadow rounded mb-5"
                             role="alert">
                            <p><strong>DĖMESIO!</strong> Šiuo metu registracija į darželius negalima, bandykite vėliau.
                            </p>
                            <p>Apgailestaujame dėl nepatogumų.</p>
                        </div>
                    )}
                <div>
                    <div className="row mb-3">
                        <h3>Vaiko registracijos į darželius eiga ir statusas</h3>
                    </div>

                    <div className="">

                        {/*Parent card + arrow*/}
                        <div className="row equal mb-3 pb-3 pt-3 shadow-sm border-0 rounded d-flex align-items-center">
                            <div className="d-flex">
                                <div
                                    className="col-sm-9 card shadow-sm bg-white border-0 rounded m-auto">
                                    <h6>Tėvo (globėjo) anketa</h6>
                                    {!parentDetailsFilled ?
                                        (<a href={`/bean-app/tevai/registracija`} className="btn">Pildyti</a>) :
                                        (<a href={`/bean-app/tevai/registracija/redaguoti`} className="btn">Redaguoti</a>)}
                                </div>
                                <div className="col-sm-3 d-flex arrow  m-auto">
                                    <i className="fas fa-chevron-right "></i>
                                </div>
                            </div>
                        </div>

                        {/*Child cards + arrows*/}
                        {childRegistered ? (
                                children.map(child =>
                                    
                                    <div
                                        className="row equal mb-3 pb-3 pt-3 shadow-sm border-0 rounded d-flex align-content-stretch">

                                        <div className="col-12">
                                            <h5>Vaikas:
                                                {' ' + child.firstname + ' ' + child.lastname + ' '} </h5>
                                        </div>
                                        <div
                                            className="col-sm-2 d-flex pb-3 card
                                        shadow-sm bg-white border-0 rounded m-auto">
                                            <h6>Vaiko duomenų anketa</h6>
                                            <button className="btn">
                                                <a href={`/bean-app/tevai/vaikai/${child.childId}`}>Redaguoti</a>
                                            </button>
                                        </div>
                                        <div className="col-sm-2 d-flex pb-3 arrow  m-auto">
                                            <i className="fas fa-chevron-right"></i>
                                        </div>
                                        <div
                                            className="col-sm-2 d-flex pb-3 card
                                        shadow-sm bg-white border-0 rounded m-auto">
                                            <h6>Prašymas registruoti į darželį</h6>
                                            {child.applicationFilled ?
                                                (<a href={`/bean-app/tevai/vaikai/registracijos/${child.childId}`}
                                                    className={admissionActive ? "btn" : "btn disabled"}>Redaguoti</a>) :
                                                (<a href={`/bean-app/tevai/registracija-i-darzeli`}
                                                    className={childRegistered || admissionActive ? "btn" :
                                                        "btn disabled"}>Pildyti</a>)
                                            }
                                        </div>
                                        <div className="col-sm-2 d-flex pb-3 arrow  m-auto">
                                            <i className="fas fa-chevron-right"></i>
                                        </div>
                                        <div
                                            className="col-sm-2 d-flex pb-3 card
                                            shadow-sm bg-white border-0 rounded m-auto">
                                            <h6>Prašymo statusas</h6>
                                            <p><strong>{child.applicationAccepted ? " prašymas priimtas" :
                                                (child.applicationFilled ? " prašymas nepriimtas" : " ")}
                                            </strong></p>
                                        </div>
                                        <div className="col-sm-2 d-flex pb-3 arrow m-auto">
                                            <i className="fas fa-chevron-right"></i>
                                        </div>
                                        {child.applicationFilled ? (
                                            <div
                                                className="col-sm-2 d-flex pb-3 card
													shadow-sm bg-white border-0 rounded m-auto">
                                                <h6>Vaiko registracijos į darželį statusas</h6>
                                                {!child.applicationAccepted ?
                                                    (
                                                        <p>{child.notAcceptedReason}</p>) : (child.acceptedKindergarten !== null ?
                                                        (<p>pateko
                                                            į <strong>{child.acceptedKindergarten}</strong> darželį</p>) :
                                                        (<div>
                                                            <p>Vaiko vieta eilėje darželiuose pagal prioritetus:</p>
                                                            <ol type="1">
                                                                {child.firstPriority ? (
                                                                    <li>{child.firstPriority}: {child.placeInFirstQueue} vieta</li>) : ''}
                                                                {child.secondPriority ? (
                                                                    <li>{child.secondPriority}: {child.placeInSecondQueue} vieta</li>) : ''}
                                                                {child.thirdPriority ? (
                                                                    <li>{child.thirdPriority}: {child.placeInThirdQueue} vieta</li>) : ''}
                                                                {child.fourthPriority ? (
                                                                    <li>{child.fourthPriority}: {child.placeInFourthQueue} vieta</li>) : ''}
                                                                {child.fifthPriority ? (
                                                                    <li>{child.fifthPriority}: {child.placeInFifthQueue} vieta</li>) : ''}
                                                            </ol>
                                                        </div>))
                                                }
                                            </div>
                                        ) : (<div
                                                className="col-sm-2 d-flex pb-3 card
													shadow-sm bg-white border-0 rounded m-auto">
                                            <h6>Vaiko registracijos į darželį statusas</h6>
                                        </div>)}

                                    </div>
                                )) :
                            (<div
                                className="row equal mb-3 pb-3 pt-3 shadow-sm border-0 rounded d-flex align-content-stretch">
                                <div
                                    className="col-sm-2 d-flex pb-3 card shadow-sm bg-white border-0 rounded m-auto">
                                    <p>Vaiko duomenų anketa</p>
                                    <a href={`/bean-app/tevai/vaikoregistracija`}
                                          className={parentDetailsFilled ? "btn" : "btn disabled"}>Pildyti</a>
                                </div>
                                <div
                                    className="col-sm-2 d-flex pb-3 d-flex align-items-center arrow  m-auto">
                                    <i className="fas fa-chevron-right"></i>
                                </div>
                                <div
                                    className="col-sm-2 d-flex pb-3 card shadow-sm bg-white border-0 rounded m-auto">
                                    <p>Prašymas registruoti į darželį</p>
                                    <a href={`/bean-app/tevai/registracija-i-darzeli`}
                                       className={childRegistered ? "btn" : "btn disabled"}>Pildyti</a>
                                </div>
                                <div
                                    className="col-sm-2 d-flex pb-3 align-items-center arrow  m-auto">
                                    <i className="fas fa-chevron-right"></i>
                                </div>
                                <div
                                    className="col-sm-2 d-flex pb-3 card shadow-sm bg-white border-0 rounded m-auto">
                                    <p>Prašymas priimtas</p>
                                </div>
                                <div
                                    className="col-sm-2 d-flex pb-3 align-items-center arrow m-auto">
                                    <i className="fas fa-chevron-right"></i>
                                </div>
                                <div
                                    className="col-sm-2 d-flex pb-3 card shadow-sm bg-white border-0 rounded m-auto">
                                    <p>Vaiko vieta eilėje</p>
                                </div>
                            </div>)}
                        <div className="col-12 mt-2 ml-2">
                            <a href={`/bean-app/tevai/vaikoregistracija`}
                               className={parentDetailsFilled ? "btn" : "btn disabled"}>Pridėti dar vieną vaiką</a>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default withRouter(ParentLandingDashboard)
