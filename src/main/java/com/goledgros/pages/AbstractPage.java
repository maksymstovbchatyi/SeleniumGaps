package com.goledgros.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

     protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public abstract boolean isAt();
}
