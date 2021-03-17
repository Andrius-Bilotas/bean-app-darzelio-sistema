import React, { Suspense, lazy } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
//our imports
import EduAdminLanding from './Components/EduAdminLanding/EduAdminLanding';
import LandingPage from './Components/LandingPage';
import SysAdminLanding from './Components/SysAdminLanding/SysAdminLanding';
import PrivateRoute from './Configuration/PrivateRoute';
import ParentLanding from './Components/ParentLanding/ParentLanding';
import KindergartenListTableContainer from './Components/KindergartenList/KindergartenListTableContainer';
import PasswordReminder from './Components/Login/ResetPassword/PasswordReminder';
import AddNewPassword from './Components/Login/ResetPassword/AddNewPassword';
import IdleTimerContainer from './Configuration/IdleTimer';
import PublicGuide from './Components/Guide/PublicGuide';
const NoMatch = lazy(() => import('./Components/NoMatch/NoMatchPresentation'));
const KindergartenRegistrationContainer = lazy(() =>
  import('./Components/KindergartenList/KindergartenRegistrationContainer')
);
const UpdateKindergartenFormContainer = lazy(() =>
  import('./Components/KindergartenList/UpdateKindergartenFormContainer')
);
const RegistrationToSystem = lazy(() =>
  import('./Components/RegistrationToSystem/RegistrationToSystem')
);

function App() {
  return (
    <div className="App">
      <BrowserRouter basename={process.env.PUBLIC_URL}>
        <IdleTimerContainer />
        <Suspense fallback={<div>Loading...</div>}>
          <Switch>
            <Route exact path="/" component={LandingPage} />
            <Route path="/login" component={LandingPage} />
            <Route path="/registracija" component={RegistrationToSystem} />
            <Route path="/instrukcija" component={PublicGuide} />
            <Route
              exact
              path="/atstatytiSlaptazodi"
              component={PasswordReminder}
            />
            <Route exact path="/keistislaptazodi" component={AddNewPassword} />
            <PrivateRoute
              exact
              path="/tevai"
              component={ParentLanding}
              role={'PARENT'}
            />
            <PrivateRoute
              path="/tevai/naudotojo-duomenys"
              exact
              component={ParentLanding}
              role={'PARENT'}
            />
            <PrivateRoute
              path="/tevai/registracija"
              exact
              component={ParentLanding}
              role={'PARENT'}
            />
            <PrivateRoute
              path="/tevai/registracija/redaguoti"
              exact
              component={ParentLanding}
              role={'PARENT'}
            />
            <PrivateRoute
              path="/tevai/vaikoregistracija"
              exact
              component={ParentLanding}
              role={'PARENT'}
            />
            <PrivateRoute
              path="/tevai/registracija-i-darzeli"
              exact
              component={ParentLanding}
              role={'PARENT'}
            />
            <PrivateRoute
              path="/tevai/toliau"
              exact
              component={ParentLanding}
              role={'PARENT'}
            />
            <PrivateRoute
              path="/tevai/*"
              exact
              component={ParentLanding}
              role={'PARENT'}
            />
            <PrivateRoute
              exact
              path="/admin/pradzia"
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              exact
              path="/admin/edu"
              component={EduAdminLanding}
              role={'EDU'}
            />
            <PrivateRoute
              path="/admin/edu"
              exact
              component={EduAdminLanding}
              role={'EDU'}
            />
     
            <PrivateRoute
              path="/admin/edu/vaikai"
              exact
              component={EduAdminLanding}
              role={'EDU'}
            />
            <PrivateRoute
              path="/admin/edu/registracijos"
              exact
              component={EduAdminLanding}
              role={'EDU'}
            />
            <PrivateRoute
              path="/admin/edu/darzeliai"
              exact
              component={EduAdminLanding}
              role={'EDU'}
            />
            <PrivateRoute
              path="/admin/edu/darzeliai/registracija"
              exact
              component={EduAdminLanding}
              role={'EDU'}
            />
            <PrivateRoute
              path="/admin/edu/*"
              exact
              component={EduAdminLanding}
              role={'EDU'}
            />
            <PrivateRoute
              exact
              path="/admin/edu/darzeliai"
              component={KindergartenListTableContainer}
              role={'EDU'}
            />
            <PrivateRoute
              path="/admin/edu/darzelioregistracija"
              exact
              component={KindergartenRegistrationContainer}
              role={'EDU'}
            />
            <PrivateRoute
              path="/admin/edu/darzeliai/:id"
              exact
              component={UpdateKindergartenFormContainer}
              role={'EDU'}
            />
            <PrivateRoute
              exact
              path="/admin/vartotojai"
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/vartotojai/:id"
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/eiliutvarkymas"
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              exact
              path="/admin/registracija"
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              exact
              path="/admin/sekminga"
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              exact
              path="/admin/darzelioregistracija"
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              exact
              path="/admin/darzeliai"
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/darzeliai/:id"
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/naudotojo-duomenys"
              component={SysAdminLanding}
              role={'ADMIN'}
            />{' '}
            <PrivateRoute
              path="/admin/naudotojo-duomenys/redaguoti"
              component={SysAdminLanding}
              role={'ADMIN'}
            />{' '}
            <PrivateRoute
              path="/admin/naudotojo-duomenys/redaguoti/slaptazodi"
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              exact
              path="/admin/statistika"
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/duomenys/:id"
              exact
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/duomenys/tevo/:id"
              exact
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/duomenys/vaikai/:id"
              exact
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/vaikai/:id"
              exact
              component={SysAdminLanding}
              role={'ADMIN'}
            />
               <PrivateRoute
              path="/admin/:id/vaikai/:type"
              exact
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/:id/vaikai/registracijos/:type"
              exact
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/vaiko-registracija/:id"
              exact
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/:id/registracija-i-darzeli/:type"
              exact
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/tevo-registracija/:id"
              exact
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/logs"
              exact
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/archyvai"
              exact
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/instrukcija"
              exact
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <PrivateRoute
              path="/admin/vaikai"
              exact
              component={SysAdminLanding}
              role={'ADMIN'}
            />
            <Route path="*" component={NoMatch} />
            <Route component={NoMatch} />
          </Switch>
        </Suspense>
      </BrowserRouter>
    </div>
  );
}

export default App;
