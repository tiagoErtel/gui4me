package gui4me.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    public Store checkStoreDocAndSave(Store newStore){
        Store store = storeRepository.findByDocument(newStore.getDocument());

        if (store.getId().isEmpty()){
            return storeRepository.save(store);
        } else {
            return store;
        }
    }
}
