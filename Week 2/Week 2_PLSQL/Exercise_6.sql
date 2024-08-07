
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a PL/SQL block using an explicit cursor GenerateMonthlyStatements that retrieves all transactions for the current month and prints a statement for each customer.

DECLARE
    -- Define the explicit cursor to retrieve transactions for the current month
    CURSOR GenerateMonthlyStatements IS
        SELECT c.CustomerID, c.Name, t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        JOIN Transactions t ON a.AccountID = t.AccountID
        ORDER BY c.CustomerID, t.TransactionDate;

BEGIN
    -- Open the cursor and loop through each record
    FOR statement_rec IN GenerateMonthlyStatements LOOP
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || statement_rec.CustomerID);
        DBMS_OUTPUT.PUT_LINE('Name: ' || statement_rec.Name);
        DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || TO_CHAR(statement_rec.TransactionDate, 'YYYY-MM-DD'));
        DBMS_OUTPUT.PUT_LINE('Amount: ' || statement_rec.Amount);
        DBMS_OUTPUT.PUT_LINE('Transaction Type: ' || statement_rec.TransactionType);
        DBMS_OUTPUT.PUT_LINE('-------------------------');
    END LOOP;
    
    -- Commit is not necessary here as we're only printing statements
END;
/


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a PL/SQL block using an explicit cursor ApplyAnnualFee that deducts an annual maintenance fee from the balance of all accounts.

DECLARE
    annual_fee CONSTANT NUMBER := 50; -- Define the annual fee amount
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance
        FROM Accounts;
BEGIN
    -- Open the cursor and loop through each account
    FOR account_rec IN ApplyAnnualFee LOOP
        -- Deduct the annual fee from the account balance
        UPDATE Accounts
        SET Balance = Balance - annual_fee
        WHERE AccountID = account_rec.AccountID;

        -- Display a message for each account processed
        DBMS_OUTPUT.PUT_LINE('Account ID: ' || account_rec.AccountID || ' - Annual fee of ' || annual_fee || ' deducted. New Balance: ' || (account_rec.Balance - annual_fee));
    END LOOP;

    -- Commit the transaction to save changes
    COMMIT;
END;
/

select * from accounts
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a PL/SQL block using an explicit cursor UpdateLoanInterestRates that fetches all loans and updates their interest rates based on the new policy.

DECLARE
    new_interest_rate CONSTANT NUMBER := 1.5;  -- New interest rate policy
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate,CustomerId
        FROM Loans;
BEGIN
    FOR loan_rec IN UpdateLoanInterestRates LOOP
        UPDATE Loans
        SET InterestRate = InterestRate + new_interest_rate
        WHERE LoanID = loan_rec.LoanID;
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || loan_rec.CustomerId || 
                              ' + new interest rate of ' || new_interest_rate || 
                              ' New Interest rate: ' || (loan_rec.InterestRate + new_interest_rate));
    END LOOP;
    
    COMMIT;
END;
/

select * from loans
