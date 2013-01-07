package com.thoughtworks.twu.selenium;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class AdminTest {

    static WebDriver driver;

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
        Database.clean();
        loginToAdminScreen();
    }

    private void loginToAdminScreen() {
        driver.get("http://localhost:8080/TrailBlazers/login");
        driver.findElement(By.name("j_username")).sendKeys("admin");
        driver.findElement(By.name("j_password")).sendKeys("admin");
        submitForm();
    }

    private void submitForm() {
        driver.findElement(By.name("submit")).click();
    }

    @Test
     public void  shouldTakeUserToItemPageFromAdminScreen(){
        driver.findElement(By.linkText("Add a item")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/item"));
    }

    @Test
    public void  shouldTakeUserToHomeScreen(){
        driver.findElement(By.linkText("Home")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/"));
    }

    @Test
    public void  shouldTakeUserToAdminScreen(){
        driver.findElement(By.linkText("Admin")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/admin"));
    }

    @Test
    public void shouldLogOutUserBackToHomePageWhenLogOutLinkIsClicked(){
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/"));
    }
}
