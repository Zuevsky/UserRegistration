package zuevsky.userregistration.data;

import java.io.IOException;
import java.util.Map;

public interface FileWorkFunctions<T, K> {
    Map<T, K> getDataFromFile() throws IOException;

    void saveDataToFile() throws IOException;
}
