package org.examnosql.backendlib.services;
import org.examnosql.backendlib.models.Book;
import org.examnosql.backendlib.models.Loan;
import org.examnosql.backendlib.repositories.BookRepository;
import org.examnosql.backendlib.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookMongoRepository;

    public Loan addLoan(Loan loan) {
        // Check if the book exists
        Optional<Book> bookOpt = bookMongoRepository.findBookByTitle(loan.getLivreId());
        if (!bookOpt.isPresent()) {
            throw new RuntimeException("Book not found");
        }

        // Save the loan and update the loan status
        Loan savedLoan = loanRepository.save(loan);
        return updateLoanStatus(savedLoan.getId(), savedLoan.getStatus());
    }
    public Loan updateLoanStatus(String loanId, String status) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus(status);
        Loan updatedLoan = loanRepository.save(loan);

        if ("retourné".equalsIgnoreCase(status)) {
            System.out.println(loan.getLivreId());
            Book book = bookMongoRepository.findBookByTitle(loan.getLivreId()).orElseThrow(() -> new RuntimeException("Book not found"));
            book.setNmbCopie(book.getNmbCopie() + 1);
            bookMongoRepository.save(book);
        }
        else {
            Book book = bookMongoRepository.findBookByTitle(loan.getLivreId()).orElseThrow(() -> new RuntimeException("Book not found"));
            book.setNmbCopie(book.getNmbCopie() - 1);
            bookMongoRepository.save(book);
        }

        return updatedLoan;
    }

    public void deleteLoan(String loanId) {
        loanRepository.deleteById(loanId);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
