package com.example.checking.repo;

import com.example.checking.domain.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LedgerEntryRepository extends JpaRepository<LedgerEntry, Long> {
    Optional<LedgerEntry> findByTransferIdAndType(String transferId, LedgerEntry.Type type);
}
