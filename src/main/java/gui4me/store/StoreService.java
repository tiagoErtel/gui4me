package gui4me.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    public Store checkStoreDocAndSave(Store newStore){
        Optional<Store> store = storeRepository.findByDocument(newStore.getDocument());

        if (store.isEmpty()){
            return storeRepository.save(newStore);
        } else {
            return store.get();
        }
    }
}
