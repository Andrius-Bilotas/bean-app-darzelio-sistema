package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edutests.TestEduAddNewKindergarten;
import parenttests.TestParentCanRegisterHimself;
import parenttests.TestParentLogin;
import parenttests.TestParentSubmitKindergartenApplication;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   TestParentCanRegisterHimself.class,
   TestParentLogin.class,
   TestEduAddNewKindergarten.class,
   TestParentSubmitKindergartenApplication.class
 
})
public class SmokeSuite {

}
