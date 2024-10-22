package com.rayan.salarytracker.entity;

public class ExpenseDto {

    private String description;
    private int amount;
    private String category;
    private String bank;
    private String status;
    private Long salaryId; // Only salary_id, not the full Salary object

    // Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Long salaryId) {
        this.salaryId = salaryId;
    }

    @Override
    public String toString() {
        return "ExpenseDto [description=" + description + ", amount=" + amount + ", category=" + category + ", bank="
                + bank + ", status=" + status + ", salaryId=" + salaryId + "]";
    }

}
