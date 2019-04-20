package controller.util.collectors.impl;

import controller.constants.FrontConstants;
import controller.exception.WrongInputDataException;
import controller.util.collectors.DataCollector;
import domain.Driver;
import domain.User;
import org.apache.log4j.Logger;
import resource_manager.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class DriverDataCollector extends DataCollector<Driver> {

    private static ResourceManager resourceManager = ResourceManager.INSTANCE;

    private static final Logger logger = Logger.getLogger(DriverDataCollector.class);

    @Override
    public Driver retrieveData(HttpServletRequest request) throws WrongInputDataException {
        logger.info("Retrieving driver data from request");
        int counter = 5;
        String name = request.getParameter(FrontConstants.NAME);
        String surname = request.getParameter(FrontConstants.SURNAME);
        String birth = request.getParameter(FrontConstants.BIRTH);
        String licenseTest = request.getParameter(FrontConstants.LICENSE_TEST);
        String salary = request.getParameter(FrontConstants.SALARY);
        Driver driver = new Driver();
        if (name != null && name.matches(resourceManager.getRegex("reg.name"))) {
            driver.setName(name);
            counter--;
        }
        if (surname != null && surname.matches(resourceManager.getRegex("reg.surname"))) {
            driver.setSurname(surname);
            counter--;
        }
        if (birth != null && birth.matches(resourceManager.getRegex("reg.birth"))
                && Date.valueOf(birth).compareTo(new Date(System.currentTimeMillis())) < 0) {
            driver.setBirth(Date.valueOf(birth));
            counter--;
        }
        if (licenseTest != null && licenseTest.matches(resourceManager.getRegex("reg.license"))
                && Date.valueOf(licenseTest).compareTo(new Date(System.currentTimeMillis())) < 0) {
            driver.setLicenseTest(Date.valueOf(licenseTest));
            counter--;
        }
        if (salary != null && salary.matches(resourceManager.getRegex("reg.salary"))) {
            driver.setSalary(Integer.valueOf(salary));
            counter--;
        }
        try {
            User user = new UserDataCollector().retrieveData(request);
            driver.setUser(user);
        } catch (WrongInputDataException e) {
            driver.setUser((User) request.getAttribute(FrontConstants.USER));
            request.setAttribute(FrontConstants.DRIVER, driver);
            throw new WrongInputDataException(e);
        }
        if (counter != 0){
            logger.warn("Not all input forms filled correctly");
            request.setAttribute(FrontConstants.DRIVER, driver);
            throw new WrongInputDataException("Not all input form filled correctly");
        }
        return driver;
    }
}
