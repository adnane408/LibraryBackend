package org.examnosql.backendlib.repositories;
import org.examnosql.backendlib.models.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanRepository extends MongoRepository<Loan, String> {
}
