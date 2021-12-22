package zuevsky.userregistration.application.services;

import java.util.HashMap;

public interface MainFunctions<T, K> {
    void getAll(HashMap<T, K> collection);

    void getSpecific(HashMap<T, K> collection, T specificParameter);

    void createNew(HashMap<T, K> collection);

    void updateSpecific(HashMap<T, K> collection, T specificParameter);

    void deleteSpecific(HashMap<T, K> collection, T specificParameter);

    void deleteAll(HashMap<T, K> collection);
}