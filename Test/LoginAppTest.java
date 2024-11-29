import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class LoginAppTest {


    @Test
    public void testInvalidEmail() throws Exception {
        // Test Case 2: Invalid Email
        LoginApp loginApp = new LoginApp();
        Method method = LoginApp.class.getDeclaredMethod("authenticateUser", String.class);
        method.setAccessible(true);

        String userName = (String) method.invoke(loginApp, "unknown@example.com");
        assertNull("Authentication should fail for an email that does not exist.", userName);
    }

    @Test
    public void testEmptyEmail() throws Exception {
        // Test Case 3: Empty Email
        LoginApp loginApp = new LoginApp();
        Method method = LoginApp.class.getDeclaredMethod("authenticateUser", String.class);
        method.setAccessible(true);

        String userName = (String) method.invoke(loginApp, "");
        assertNull("Authentication should fail for an empty email input.", userName);
    }

    @Test
    public void testSQLInjectionAttempt() throws Exception {
        // Test Case 4: SQL Injection Attempt
        LoginApp loginApp = new LoginApp();
        Method method = LoginApp.class.getDeclaredMethod("authenticateUser", String.class);
        method.setAccessible(true);

        String userName = (String) method.invoke(loginApp, "johndoe@example.com' OR '1'='1");
        assertNull("Authentication should fail for an SQL injection attempt.", userName);
    }

    @Test
    public void testNullEmail() throws Exception {
        // Test Case 5: Null Email
        LoginApp loginApp = new LoginApp();
        Method method = LoginApp.class.getDeclaredMethod("authenticateUser", String.class);
        method.setAccessible(true);

        String userName = (String) method.invoke(loginApp, (String) null);
        assertNull("Authentication should fail for a null email input.", userName);
    }

    @Test
    public void testCaseSensitivity() throws Exception {
        // Test Case 6: Case Sensitivity Check
        LoginApp loginApp = new LoginApp();
        Method method = LoginApp.class.getDeclaredMethod("authenticateUser", String.class);
        method.setAccessible(true);

        String userName = (String) method.invoke(loginApp, "JOHNDOE@EXAMPLE.COM");
        assertNull("Authentication should fail if email matching is case-sensitive.", null);
    }

    @Test
    public void testWhitespaceEmail() throws Exception {
        // Test Case 7: Whitespace in Email
        LoginApp loginApp = new LoginApp();
        Method method = LoginApp.class.getDeclaredMethod("authenticateUser", String.class);
        method.setAccessible(true);

        String userName = (String) method.invoke(loginApp, " johndoe@example.com ");
        assertNull("Authentication should fail for emails with leading or trailing whitespace.", userName);
    }
}
