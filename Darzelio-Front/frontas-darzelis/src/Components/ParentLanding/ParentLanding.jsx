import React from "react";
import NavigationForAllPages from "../Utilities/NavigationForAllPages";
import {withRouter} from "react-router";

//our
import ParentSideBarNavigation from "./ParentSideBarNavigation";
import ParentlandingFooter from "./ParentLandingFooter";
import "../../Style/UsersLandings.css"
import ParentRoutes from "./ParentRoutes";

const ParentLanding = (props) => {

                return (
                    <div className="container-fluid">
                        <header className=" m-auto bg-white ">
                            <NavigationForAllPages/>
                        </header>
                        <div className="row pl-3">
                            <aside className="col-lg-2 col-md-4 col-sm-12 border-0 bg-white">
                                <ParentSideBarNavigation/>
                            </aside>
                            <main id="content" className="col-lg-10 col-md-8 col-sm-12">
                                <ParentRoutes/>
                            </main>
                        </div>
                        <footer className="footer pt-3 m-auto">
                            <ParentlandingFooter/>
                        </footer>
                    </div>
                )

}
export default withRouter(ParentLanding);