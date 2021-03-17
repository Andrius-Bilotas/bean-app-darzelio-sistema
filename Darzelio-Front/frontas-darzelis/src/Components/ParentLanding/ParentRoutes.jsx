import React, {lazy} from 'react';
import { Switch } from 'react-router';

//our imports
import PrivateRoute from '../../Configuration/PrivateRoute';
import ParentLandingDashboard from './ParentLandingDashboard';

import ParentRegistrationFormContainer from '../ParentRegistration/ParentRegistrationFormContainer';
import ChildrenRegistrationFormContainer from '../ChildrenRegistration/ChildrenRegistrationFormContainer';
import MainRegistrationContainer from '../MainRegistration/MainRegistrationContainer';

import UpdateParentRegistrationFormContainer from '../ParentRegistration/UpdateParentRegistrationFormContainer';

import UpdateUserDataFormContainer from '../UserData/UpdateUserDataFormContainer';
import UserData from '../UserData/UserData';
import UpdateUserPasswordContainer from '../UserData/UpdateUserPasswordContainer';
import UploadPdfContainer from '../UploadPDF/UploadPdfContainer';
import ChildrenListTableContainer from '../ChildrenRegistration/ChildrenListTableContainer';
import UpdateChildrenRegistrationFormContainer from '../ChildrenRegistration/UpdateChildrenRegistrationFormContainer';

const UpdateChildrenApplicationByParents = lazy(() => import ( '../ChildrenRegistration/UpdateChildrenApplicationByParents'));
const NotFoundPage  = lazy(() => import ('../Utilities/NotFoundPage'));
const NextPage  = lazy(() => import ( '../ChildrenRegistration/NextPage'));
const ChildrenPdfTable  = lazy(() => import ( '../UploadPDF/ChildrenPdfTable'));
const UpdateUserEmailContainer  = lazy(() => import ( '../UserData/UpdateUserEmailContainer'));
const HorizontalChart  = lazy(() => import ( '../Statistics/HorizontalChart'));
const ConditionsPage  = lazy(() => import ('../Conditions/ConditionsPage'));
const AdmissionRules  = lazy(() => import ( '../Conditions/AdmissionRules'));
const Guide  = lazy(() => import ('../Guide/ParentsGuide'));
const DeleteData  = lazy(() => import ('../UserData/DeleteData'));

const ParentRoutes = () => {
  return (
    <Switch>
      <PrivateRoute
        path="/tevai"
        exact
        component={ParentLandingDashboard}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/naudotojo-duomenys"
        exact
        component={UserData}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/registracija"
        exact
        component={ParentRegistrationFormContainer}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/registracija/redaguoti"
        exact
        component={UpdateParentRegistrationFormContainer}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/vaikoregistracija"
        exact
        component={ChildrenRegistrationFormContainer}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/toliau"
        exact
        component={NextPage}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/registracija-i-darzeli"
        exact
        component={MainRegistrationContainer}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/duomenys/redaguoti"
        exact
        component={UpdateUserDataFormContainer}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/duomenys/redaguoti/slaptazodi"
        exact
        component={UpdateUserPasswordContainer}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/ikelti"
        exact
        component={UploadPdfContainer}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/vaikai"
        exact
        component={ChildrenListTableContainer}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/vaikai/:id"
        exact
        component={UpdateChildrenRegistrationFormContainer}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/vaikai/registracijos/:id"
        exact
        component={UpdateChildrenApplicationByParents}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/pazymos/"
        exact
        component={ChildrenPdfTable}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/duomenys/redaguoti/pasta"
        exact
        component={UpdateUserEmailContainer}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/statistika"
        exact
        component={HorizontalChart}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/salygos"
        exact
        component={ConditionsPage}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/tvarka"
        exact
        component={AdmissionRules}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/instrukcija"
        exact
        component={Guide}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/duomenys/istrinti"
        exact
        component={DeleteData}
        role={'PARENT'}
      />
      <PrivateRoute
        path="/tevai/*"
        exact
        component={NotFoundPage}
        role={'PARENT'}
      />
    </Switch>
  );
};

export default ParentRoutes;
