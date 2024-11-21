package net.engineeringdigest.journalApp.cache;

import net.engineeringdigest.journalApp.entity.ConfigEntity;
import net.engineeringdigest.journalApp.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigRepository configRepository;

    public Map<String, String> APP_CACHE;

    // This method is called immediately after the bean is created
    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();
        List<ConfigEntity> all = configRepository.findAll();
        for(ConfigEntity configEntity: all){
            APP_CACHE.put(configEntity.getKey(), configEntity.getValue());
        }
    }

}
