package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<JournalEntry> getAll() {
        return journalEntryService.getAllEntries();
    }

    // add an entry to the journal
    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    // get an entry by id
    @GetMapping("/{id}")
    public JournalEntry getEntry(@PathVariable ObjectId id) {
        return journalEntryService.getEntry(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEntry(@PathVariable ObjectId id) {
        journalEntryService.deleteEntry(id);
        return "Entry deleted";
    }

    @PutMapping("/{id}")
    public String updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry journalEntry = journalEntryService.getEntry(id);
        if(journalEntry == null) {
            return "Entry not found";
        }
        journalEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : journalEntry.getTitle());
        journalEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : journalEntry.getContent());
        journalEntryService.saveEntry(journalEntry);
        return "Entry updated";
    }
}
