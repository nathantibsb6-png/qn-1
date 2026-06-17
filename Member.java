package com.eden.nathanq1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a library member who may hold multiple active loans.
 */
public class Member {
    private String memberId;
    private String name;
    private final List<Loan> loans;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.loans = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** Returns an unmodifiable view of the member's current loans. */
    public List<Loan> getLoans() {
        return Collections.unmodifiableList(loans);
    }

    void addLoan(Loan loan) {
        loans.add(loan);
    }

    void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    @Override
    public String toString() {
        return String.format("Member[id=%s, name=%s, activeLoans=%d]",
                memberId, name, loans.size());
    }
}
