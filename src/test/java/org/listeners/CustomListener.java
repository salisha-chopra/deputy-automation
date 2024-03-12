package org.listeners;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CustomListener implements ITestListener {
    public void onStart(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        String methodName = method.getMethodName();
        System.out.println("test execution started for method: " + methodName);
    }

    public void onTestSuccess(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        String methodName = method.getMethodName();
        System.out.println("test execution success for method: " + methodName);
    }

    public void onTestSkipped(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        String methodName = method.getMethodName();
        System.out.println("test execution skipped for method: " + methodName);
    }

    public void onTestFailure(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        String methodName = method.getMethodName();
        System.out.println("test execution failed for method: " + methodName);
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");;
        TakesScreenshot tss = (TakesScreenshot) driver;
        File screenshot = tss.getScreenshotAs(OutputType.FILE);
        int randomNumber = generateRandomValue() ;
        String directory = "screenshots";
        File destinationFile = new File(directory + "\\screenshot" +"failed_" + methodName + "_" +
                String.valueOf(randomNumber) + ".png");
        try {
            FileUtils.copyFile(screenshot, destinationFile);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            // Capture screenshot
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // Attach screenshot to Allure report
            Allure.addAttachment("failed_" + methodName, new ByteArrayInputStream(screenshotBytes));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private int generateRandomValue() {
        int min = 1;
        int max = 9999;
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
