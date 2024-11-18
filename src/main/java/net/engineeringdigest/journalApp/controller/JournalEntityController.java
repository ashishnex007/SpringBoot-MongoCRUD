package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntityController {

    @Autowired
    private JournalEntryService journalEntryService;

    // get all entries from the journal
    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll() {
        try{
            return new ResponseEntity<>(journalEntryService.getAllEntries(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // add an entry to the journal
    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get an entry by id
    @GetMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id) {
        try {
            JournalEntry journalEntry = journalEntryService.getEntry(id);
            if(journalEntry == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            journalEntryService.deleteEntry(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
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
