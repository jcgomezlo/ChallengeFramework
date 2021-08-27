package data;

import org.testng.annotations.DataProvider;
import utils.DataDeliver;

public class ListsDataProvider {
    @DataProvider(name = "ListValid")
    public Object[][] ListValid() {
        return DataDeliver.getInstance("data/data.xlsx").getData("Lists");

    }
}
