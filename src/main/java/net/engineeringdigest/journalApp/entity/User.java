package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users") // This annotation is used to indicate that this class is a MongoDB document. as a row in a table in a relational database.
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique = true) // This annotation is used to indicate that this field should be indexed and that the index should be unique, but this is not automatically enforced.
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @DBRef // This is a reference to JournalEntry.
    private List<JournalEntry> journalEntries = new ArrayList<>();

}
