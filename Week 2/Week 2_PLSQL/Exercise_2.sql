select * from customers '''FOR CHECKING PURPOSES'''
select * from loans;
select * from accounts
select * from employees
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a stored procedure SafeTransferFunds that transfers funds between two accounts. 
--Ensure that if any error occurs (e.g., insufficient funds), an appropriate error message is logged and the transaction is rolled back.

CREATE PROCEDURE SafeTransferFunds (
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
)
IS
    insufficient_funds EXCEPTION;
    v_balance NUMBER;
BEGIN
  
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from_account_id FOR UPDATE;

    IF v_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;
    
    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_from_account_id;

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
END SafeTransferFunds;
/

CALL SafeTransferFunds(5,1,1000);

DROP PROCEDURE SafeTransferFunds;
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a stored procedure UpdateSalary that increases the salary of an employee by a given percentage. 
--If the employee ID does not exist, handle the exception and log an error message.

CREATE PROCEDURE UpdateSalary (
    p_employee_id IN NUMBER,
    p_percentage IN NUMBER
)
IS
    v_salary NUMBER;
BEGIN
    -- Check if the employee exists and get the current salary
    SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_employee_id;

    -- Update the salary
    UPDATE Employees
    SET Salary = Salary * (1 + p_percentage / 100)
    WHERE EmployeeID = p_employee_id;

    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Employee ID ' || p_employee_id || ' not found');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END UpdateSalary;
/

CALL UpdateSalary(15,10)

DROP PROCEDURE UpdateSalary
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a stored procedure AddNewCustomer that inserts a new customer into the Customers table. 
--If a customer with the same ID already exists, handle the exception by logging an error and preventing the insertion.

CREATE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_dob IN DATE,
    p_balance IN NUMBER
)
IS
    duplicate_customer EXCEPTION;
    v_check NUMBER;
BEGIN
    --check for duplicate id
    SELECT COUNT(*) INTO v_check FROM CUSTOMERS CU 
    WHERE NOT EXISTS (
    SELECT 1 
    FROM CUSTOMERS CO 
    WHERE CU.CUSTOMERID!=p_customer_id);
    IF v_check = 0 then
      -- Insert the new customer
      INSERT INTO Customers (CustomerID, Name, DOB, Balance)
      VALUES (p_customer_id, p_name, p_dob, p_balance);
      COMMIT;
    else
      raise duplicate_customer;
    end if;

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE duplicate_customer;
    WHEN duplicate_customer THEN
        DBMS_OUTPUT.PUT_LINE('Customer ID ' || p_customer_id || ' already exists');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END AddNewCustomer;
/

CALL AddNewCustomer(19,'Priyanshu Das',TO_DATE('1989-09-25', 'YYYY-MM-DD'),1200)

DROP PROCEDURE AddNewCustomer
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
