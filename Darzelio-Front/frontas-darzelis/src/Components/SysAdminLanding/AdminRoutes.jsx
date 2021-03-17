import React from 'react';
import { Switch } from 'react-router';
import PrivateRoute from '../../Configuration/PrivateRoute';
import KindergartenListTableContainer from '../KindergartenList/KindergartenListTableContainer';
import KindergartenRegistrationContainer from '../KindergartenList/KindergartenRegistrationContainer';
import UpdateKindergartenFormContainer from '../KindergartenList/UpdateKindergartenFormContainer';
import RegistrationFormContainer from '../Registration/RegistrationFormContainer';
import RegistrationSuccessPresentation from '../Registration/RegistrationSuccessPresentation';
import UpdateUserFormContainer from '../UsersListAdmin/UpdateUserFormContainer';
import UsersListTableContainer from '../UsersListAdmin/UsersListTableContainer';
import NotFoundPage from '../Utilities/NotFoundPage';
import { SysAdminDashboard } from './SysAdminDashboard';
import { ManageQueuesToKindergartens } from './ManageQueuesToKindergartens';
import UpdateUserDataFormContainer from '../UserData/UpdateUserDataFormContainer';
import UserData from '../UserData/UserData';
import UpdateUserPasswordContainer from '../UserData/UpdateUserPasswordContainer';
import UpdateUserEmailContainer from '../UserData/UpdateUserEmailContainer';
import HorizontalChart from '../Statistics/HorizontalChart';
import UserInformation from '../UsersListAdmin/UserInformation';
import UpdateParentDetailsByAdmn from '../UsersListAdmin/UpdateParentDetailsByAdmn';
import ChildrenTable from '../UsersListAdmin/ChildrenTableByAdmin';
import UpdateChildDataByAdmin from '../UsersListAdmin/UpdateChildDataByAdmin';
import UpdateChildApplicationByAdmin from '../UsersListAdmin/UpdateChildApplicationByAdmin';
import ChildRegistrationToKindergartenByAdmin from '../UsersListAdmin/ChildRegistrationToKindergartenByAdmin';
import ParentRegistrationByAdmin from '../UsersListAdmin/ParentRegistrationByAdmin';
import ListOfRegistrations from '../MainRegistration/ListOfRegistrations';
import EduAdminChildInfoContainer from '../EduAdminLanding/EduAdminChildInfoContainer';

const ChildrenRegistrationByAdmin = React.lazy(() =>
  import('../UsersListAdmin/ChildrenRegistrationByAdmin')
);
const UserLogsList = React.lazy(() => import('../Logs/UserLogsList'));
const ArchiveListByAdmin = React.lazy(() =>
  import('../Archive/ArchiveListByAdmin')
);
const AdminGuide = React.lazy(() => import('../Guide/AdminGuide'));

export const AdminRoutes = () => {
  return (
    <div>
      <Switch>
        <PrivateRoute
          path="/admin/pradzia"
          exact
          component={SysAdminDashboard}
          role={'ADMIN'}
        />
        <PrivateRoute
          exact
          path="/admin/vartotojai"
          component={UsersListTableContainer}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/vartotojai/:id"
          component={UpdateUserFormContainer}
          role={'ADMIN'}
        />
        <PrivateRoute
          exact
          path="/admin/registracija"
          component={RegistrationFormContainer}
          role={'ADMIN'}
        />
        <PrivateRoute
          exact
          path="/admin/sekminga"
          component={RegistrationSuccessPresentation}
          role={'ADMIN'}
        />
        <PrivateRoute
          exact
          path="/admin/darzelioregistracija"
          component={KindergartenRegistrationContainer}
          role={'ADMIN'}
        />
        <PrivateRoute
          exact
          path="/admin/darzeliai"
          component={KindergartenListTableContainer}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/darzeliai/:id"
          component={UpdateKindergartenFormContainer}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/eiliutvarkymas"
          component={ManageQueuesToKindergartens}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/naudotojo-duomenys"
          exact
          component={UserData}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/naudotojo-duomenys/redaguoti"
          exact
          component={UpdateUserDataFormContainer}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/naudotojo-duomenys/redaguoti/slaptazodi"
          exact
          component={UpdateUserPasswordContainer}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/naudotojo-duomenys/redaguoti/pasta"
          exact
          component={UpdateUserEmailContainer}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/statistika"
          exact
          component={HorizontalChart}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/duomenys/:id"
          exact
          component={UserInformation}
          role={'ADMIN'}
        />

        <PrivateRoute
          path="/admin/duomenys/tevo/:id"
          exact
          component={UpdateParentDetailsByAdmn}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/duomenys/vaikai/:id"
          exact
          component={ChildrenTable}
          role={'ADMIN'}
        />

        <PrivateRoute
          path="/admin/:id/vaikai/:type"
          exact
          component={UpdateChildDataByAdmin}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/:id/vaikai/registracijos/:type"
          exact
          component={UpdateChildApplicationByAdmin}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/vaiko-registracija/:id"
          exact
          component={ChildrenRegistrationByAdmin}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/:id/registracija-i-darzeli/:type"
          exact
          component={ChildRegistrationToKindergartenByAdmin}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/tevo-registracija/:id"
          exact
          component={ParentRegistrationByAdmin}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/logs"
          exact
          component={UserLogsList}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/archyvai"
          exact
          component={ArchiveListByAdmin}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/vaikai"
          exact
          component={ListOfRegistrations}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/instrukcija"
          exact
          component={AdminGuide}
          role={'ADMIN'}
        />
          <PrivateRoute
          path="/admin/vaikai/:id"
          exact
          component={EduAdminChildInfoContainer}
          role={'ADMIN'}
        />
        <PrivateRoute
          path="/admin/*"
          exact
          component={NotFoundPage}
          role={'ADMIN'}
        />
      </Switch>
    </div>
  );
};
