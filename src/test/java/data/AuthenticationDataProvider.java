package data;

import org.testng.annotations.DataProvider;
import utils.DataDeliver;


public class AuthenticationDataProvider {

    @DataProvider(name = "ValidCredentials")
    public Object[][] ValidCredentials() {
        return DataDeliver.getInstance("data/data.xlsx").getData("ValidCredentials");

    }

    @DataProvider(name = "InvalidCredentials")
    public Object[][] InvalidCredentials() {
        return DataDeliver.getInstance("data/data.xlsx").getData("InvalidCredentials");
    }
}
