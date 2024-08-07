

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a trigger UpdateCustomerLastModified that updates the LastModified column of the Customers table to the current date whenever a customer's record is updated.

CREATE   TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE AuditLog (
    AuditID NUMBER GENERATED BY DEFAULT AS IDENTITY,
    TransactionID NUMBER,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(50),
    LogDate DATE
);

--Write a trigger LogTransaction that inserts a record into an AuditLog table whenever a transaction is inserted into the Transactions table.

CREATE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, TransactionDate, Amount, TransactionType, LogDate)
    VALUES (:NEW.TransactionID, :NEW.AccountID, :NEW.TransactionDate, :NEW.Amount, :NEW.TransactionType, SYSDATE);
END;
/

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a trigger CheckTransactionRules that ensures withdrawals do not exceed the balance and deposits are positive before inserting a record into the Transactions table.

CREATE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    IF :NEW.TransactionType = 'Withdrawal' THEN
        -- Check if the withdrawal amount does not exceed the balance
        SELECT Balance INTO v_balance 
        FROM Accounts 
        WHERE AccountID = :NEW.AccountID
        FOR UPDATE;
        
        IF v_balance < :NEW.Amount THEN
            RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance for withdrawal');
        END IF;
        
    ELSIF :NEW.TransactionType = 'Deposit' THEN
        -- Check if the deposit amount is positive
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive');
        END IF;
        
    END IF;
END;
/

