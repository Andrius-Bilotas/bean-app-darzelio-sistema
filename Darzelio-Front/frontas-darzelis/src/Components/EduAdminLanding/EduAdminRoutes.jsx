import React, {lazy} from 'react';
import { Switch } from 'react-router';

//our imports
import PrivateRoute from '../../Configuration/PrivateRoute';
import NotFoundPage from '../Utilities/NotFoundPage';
import EduAdminDashboard from './EduAdminDashboard';
import ListOfRegistrations from '../MainRegistration/ListOfRegistrations';
import KindergartenListTableContainer from '../KindergartenList/KindergartenListTableContainer';
import KindergartenRegistrationContainer from '../KindergartenList/KindergartenRegistrationContainer';
import UpdateKindergartenFormContainer from '../KindergartenList/UpdateKindergartenFormContainer';
import UpdateUserDataFormContainer from '../UserData/UpdateUserDataFormContainer';
import UserData from '../UserData/UserData';
import UpdateUserPasswordContainer from '../UserData/UpdateUserPasswordContainer';
import KindergartenTableContainer from '../Queue/KindergartenTableContainer';
import EduAdminChildInfoContainer from './EduAdminChildInfoContainer';
import RegisteredChildrenQueueList from '../ChildrenRegistrationQue/RegisteredChildrenQueueList';

const UpdateUserEmailContainer = lazy(() => import ( '../UserData/UpdateUserEmailContainer'));
const HorizontalChart = lazy(() => import ('../Statistics/HorizontalChart'));
const EduGuide = lazy(() => import ( '../Guide/EduGuide'));
const DeleteData = lazy(() => import ( '../UserData/DeleteData'));

const EduAdminRoutes = () => {
  return (
    <Switch>
      <PrivateRoute
        path="/admin/edu"
        exact
        component={EduAdminDashboard}
        role={'EDU'}
      />

      <PrivateRoute
        path="/admin/edu/vaikai"
        exact
        component={RegisteredChildrenQueueList}
        role={'EDU'}
      />
      <PrivateRoute
        path="/admin/edu/registracijos"
        exact
        component={ListOfRegistrations}
        role={'EDU'}
      />

      <PrivateRoute
        path="/admin/edu/darzeliai"
        exact
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
        path="/admin/edu/naudotojo-duomenys"
        exact
        component={UserData}
        role={'EDU'}
      />
      <PrivateRoute
        path="/admin/edu/duomenys/redaguoti"
        exact
        component={UpdateUserDataFormContainer}
        role={'EDU'}
      />
      <PrivateRoute
        path="/admin/edu/duomenys/redaguoti/slaptazodi"
        exact
        component={UpdateUserPasswordContainer}
        role={'EDU'}
      />

      <PrivateRoute
        path="/admin/edu/priemimai/eiles"
        exact
        component={KindergartenTableContainer}
        role={'EDU'}
      />
      <PrivateRoute
        path="/admin/edu/vaikai/:id"
        exact
        component={EduAdminChildInfoContainer}
        role={'EDU'}
      />

      <PrivateRoute
        path="/admin/edu/duomenys/redaguoti/pasta"
        exact
        component={UpdateUserEmailContainer}
        role={'EDU'}
      />
      <PrivateRoute
        path="/admin/edu/statistika"
        exact
        component={HorizontalChart}
        role={'EDU'}
      />
      <PrivateRoute
        path="/admin/edu/instrukcija"
        exact
        component={EduGuide}
        role={'EDU'}
      />
      <PrivateRoute
        path="/admin/edu/duomenys/istrinti"
        exact
        component={DeleteData}
        role={'EDU'}
      />
      <PrivateRoute
        path="/admin/edu/*"
        exact
        component={NotFoundPage}
        role={'EDU'}
      />
    </Switch>
  );
};
export default EduAdminRoutes;
