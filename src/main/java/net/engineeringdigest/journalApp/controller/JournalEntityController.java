package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntityController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    // get all entries from the journal
    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String userName) {
        try{
            User user = userService.findByUserName(userName);
            if(user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<JournalEntry> all = user.getJournalEntries();
            return new ResponseEntity<>(all, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // add an entry to the journal
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
        try {
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get an entry by id
    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getEntry(@PathVariable ObjectId id) {
        try {
            JournalEntry je = journalEntryService.getEntry(id);
            if(je != null){
                return new ResponseEntity<>(je, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/id/{userName}/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id, @PathVariable String userName) {
        try {
            JournalEntry journalEntry = journalEntryService.getEntry(id);
            if(journalEntry == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            journalEntryService.deleteEntry(id, userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<JournalEntry> updateEntry(
            @PathVariable ObjectId id,
            @PathVariable String userName,
            @RequestBody JournalEntry newEntry
    ) {
        JournalEntry journalEntry = journalEntryService.getEntry(id);
        if(journalEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        journalEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : journalEntry.getTitle());
        journalEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : journalEntry.getContent());
        journalEntryService.saveEntry(journalEntry);
        return new ResponseEntity<>(journalEntry, HttpStatus.OK);
    }
}
