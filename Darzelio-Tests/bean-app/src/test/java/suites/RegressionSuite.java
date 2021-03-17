package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import admintests.TestAdminRegistersEdu;
import admintests.TestAdminRegistersParent;
import edutests.TestEduAddNewKindergarten;
import edutests.TestEduLogin;
import parenttests.TestParentAddChildDetails;
import parenttests.TestParentAddParentDetails;
import parenttests.TestParentChangesUserPassword;
import parenttests.TestParentLogin;
import parenttests.TestParentSubmitKindergartenApplication;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   TestAdminRegistersParent.class,
   TestAdminRegistersEdu.class,
   TestParentLogin.class,
   TestEduLogin.class,
   TestParentAddParentDetails.class,
   TestParentAddChildDetails.class,
   TestParentSubmitKindergartenApplication.class,
   TestEduAddNewKindergarten.class,
   TestParentChangesUserPassword.class

 
})
public class RegressionSuite {

}
