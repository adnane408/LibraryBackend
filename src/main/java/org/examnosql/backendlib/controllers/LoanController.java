package org.examnosql.backendlib.controllers;
import org.examnosql.backendlib.models.Loan;
import org.examnosql.backendlib.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> addLoan(@RequestBody Loan loan) {
        Loan savedLoan = loanService.addLoan(loan);
        return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
    }

    @PutMapping("/{loan_id}/{status}")
    public ResponseEntity<Loan> updateLoanStatus(@PathVariable("loan_id") String loanId, @PathVariable String status) {
        Loan updatedLoan = loanService.updateLoanStatus(loanId, status);
        return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
    }

    @DeleteMapping("/{loan_id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable("loan_id") String loanId) {
        loanService.deleteLoan(loanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
}