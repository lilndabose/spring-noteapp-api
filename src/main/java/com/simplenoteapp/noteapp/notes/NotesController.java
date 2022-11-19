package com.simplenoteapp.noteapp.notes;

import com.simplenoteapp.noteapp.NoteException.NotesException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/notes")
@RequiredArgsConstructor
public class NotesController {
    private final NotesRepo notesRepo;
    @GetMapping("/")
    public ResponseEntity<List<Notes>> getAll(){

        return  ResponseEntity.ok(notesRepo.findAll());
    }
    @PostMapping("/add")
    public ResponseEntity<Notes> addNotes(@RequestBody Notes notes){
        return ResponseEntity.ok(notesRepo.save(notes));
    }

    @PutMapping("/")
    public ResponseEntity<Notes> findNotes(@RequestParam String title,@RequestBody  Notes requestBody){
        Optional<Notes> notes;
        try {
            Long id = Long.parseLong(title);
            notes = notesRepo.findById(id);
            notesRepo.save(new Notes(id,requestBody.getTitle(),requestBody.getContent()));


        }catch (Exception e){
             notes = notesRepo.findByTitle(title);
            notesRepo.save(new Notes(notes.get().getId(),requestBody.getTitle(),requestBody.getContent()));

        }
        Notes notes1 = notes.orElseThrow(()->new NotesException("No result found"));
       return ResponseEntity.ok(notes1);
    }

    @DeleteMapping("/")
    public ResponseEntity<Notes> DeleteNotes(@RequestParam String title){
        Optional<Notes> notes;
        try {
            Long id = Long.parseLong(title);
            notes = notesRepo.findById(id);

            notesRepo.deleteById(id);
        }catch (Exception e){
            notes = notesRepo.findByTitle(title);

            notesRepo.deleteByTitle(title);

        }
        return ResponseEntity.ok(notes.get());

    }



}
