package gui4me.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StoreUpdater {

    @Autowired
    StoreService storeService;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateOutdatedStores() {
        List<Store> outdatedStores = storeService.findStoresOutdated();

        for (Store store : outdatedStores) {
            Store updatedStore = storeService.fetchFromReceitaWs(store.getDocument());

            if (updatedStore != null) {
                updatedStore.setId(store.getId());
                storeService.save(updatedStore);
            }
        }
    }
}
