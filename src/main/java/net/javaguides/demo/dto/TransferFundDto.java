package net.javaguides.demo.dto;

public record TransferFundDto(
        Long fromAccountId ,
        Long toAccountId,
        double amount

) {
}
