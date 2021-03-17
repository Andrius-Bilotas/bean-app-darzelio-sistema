import React from 'react';
import { withRouter } from 'react-router';
//our imports
import NavigationForAllPages from "../Utilities/NavigationForAllPages";
import "../../Style/UsersLandings.css"
import EduAdminSideBarNavigation from "./EduAdminSideBarNavigation";
import EduAdminRoutes from "./EduAdminRoutes";
import EduAdminlandingFooter from "./EduAdminLandingFooter";


const EduAdminLanding = (props) => {

        return(
                <div className="container-fluid">
                    <header className="m-auto bg-white ">
                        <NavigationForAllPages/>
                    </header>
                    <div className="row pl-3">
                        <aside className="col-lg-2 col-md-4 col-sm-12 border-0 bg-white">
                            <EduAdminSideBarNavigation/>
                        </aside>
                        <main id="content" className="col-lg-10 col-md-8 col-sm-12">
                            <EduAdminRoutes/>
                        </main>
                    </div>
                    <footer className="footer m-auto">
                        <EduAdminlandingFooter/>
                    </footer>
                </div>
        )
}
export default withRouter(EduAdminLanding)

