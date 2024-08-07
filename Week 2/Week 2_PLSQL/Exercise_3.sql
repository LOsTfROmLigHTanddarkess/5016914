SELECT * FROM ACCOUNTS
SELECT * FROM EMPLOYEES

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a stored procedure ProcessMonthlyInterest that calculates and updates the balance of all savings accounts by applying an interest rate of 1% to the current balance.

CREATE PROCEDURE ProcessMonthlyInterest
IS
BEGIN
    -- Update all savings accounts by applying a 1% interest rate
    UPDATE Accounts
    SET Balance = Balance * 1.01
    WHERE AccountType = 'Savings';

    COMMIT;
END ProcessMonthlyInterest;
/

CALL ProcessMonthlyInterest()
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a stored procedure UpdateEmployeeBonus that updates the salary of employees in a given department by adding a bonus percentage passed as a parameter.

CREATE PROCEDURE UpdateEmployeeBonus (
    p_department IN VARCHAR2,
    p_bonus_percentage IN NUMBER
)
IS
    v_salary NUMBER;
BEGIN
  --Check if the department exists
    SELECT Salary INTO v_salary FROM Employees WHERE DEPARTMENT = p_department;
    
    -- Update the salary of employees in the given department by adding the bonus percentage
    UPDATE Employees
    SET Salary = Salary * (1 + p_bonus_percentage / 100)
    WHERE Department = p_department;

    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
         DBMS_OUTPUT.PUT_LINE('Department ' || p_department || ' not found');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END UpdateEmployeeBonus;
/

CALL UpdateEmployeeBonus('Finance',10)
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a stored procedure TransferFunds that transfers a specified amount from one account to another, checking that the source account has sufficient balance before making the transfer.

CREATE PROCEDURE TransferFunds (
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
)
IS
    insufficient_funds EXCEPTION;
    v_balance NUMBER;
BEGIN
    -- Check the balance of the from account
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from_account_id FOR UPDATE;

    IF v_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    -- Deduct the amount from the from account
    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_from_account_id;

    -- Add the amount to the to account
    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_to_account_id;

    COMMIT;
EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds for account ' || p_from_account_id);
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END TransferFunds;
/

CALL TransferFunds(5,1,1000);
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
