package com.simplenoteapp.noteapp.notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotesRepo extends JpaRepository<Notes,Long> {
    Optional<Notes> findByTitle(String title);
    Optional<Notes> deleteByTitle(String title);

}
