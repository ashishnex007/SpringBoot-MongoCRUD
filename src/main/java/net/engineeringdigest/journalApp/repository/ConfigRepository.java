package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.ConfigEntity;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<ConfigEntity, ObjectId> {

}