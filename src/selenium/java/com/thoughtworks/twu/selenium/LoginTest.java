package com.thoughtworks.twu.selenium;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LoginTest {
    static WebDriver driver;
    private final String USER = "UserCat";
    private final String USERPASSWORD = "user";

    private final String ADMIN = "AdminCat";
    private final String ADMINPASSWORD = "admin";

    @BeforeClass
    public static void before() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void after() {
        driver.close();
    }

    @Before
    public void setup() throws SQLException {
        logout();
        Database.clean();
        driver.get("http://localhost:8080/TrailBlazers/login");
    }

    private void logout() {
        driver.get("http://localhost:8080/TrailBlazers/logout");
        driver.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void shouldLoginIntoAdminScreenWithAdminCredentials(){
        LoginHelper.loginAs(ADMIN, ADMINPASSWORD, driver);
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/admin"));
    }

    @Test
    public void shouldDenyAccessToAdminScreenWithUserCredentials(){
        LoginHelper.loginAs(USER, USERPASSWORD, driver);
        assertTrue(TestUtils.isElementPresent(driver, By.id("http_403")));
    }

    @Test
    public void shouldLetUserLogoutBackToHomePageAfterBeingDeniedAcsess(){
        LoginHelper.loginAs(USER, USERPASSWORD, driver);
        assertTrue(TestUtils.isElementPresent(driver, By.id("http_403")));
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/"));
    }

    @Test
    public void shouldShowErrorWhenWrongCredentialsAreEntered(){
        LoginHelper.loginAs("blah", "blah", driver);
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/login"));
        assertThat(1, is(driver.findElements(By.className("errorblock")).size()));
    }

    @Test
    public void shouldResetFormWhenResetIsPressed(){
        driver.findElement(By.name("j_username")).sendKeys("blah");
        driver.findElement(By.name("j_password")).sendKeys("blah");
        resetForm();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/login"));
        assertThat("", is(driver.findElement(By.name("j_username")).getText()));
        assertThat("", is(driver.findElement(By.name("j_password")).getText()));
    }

    @Test
    public void shouldLogoutAndNotGiveAccessToAdmin(){
        logout();
        driver.get("http://localhost:8080/TrailBlazers/admin");
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/login"));
    }

    private void resetForm() {
        driver.findElement(By.name("reset")).click();
    }

}
