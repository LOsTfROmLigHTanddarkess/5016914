--1:

BEGIN
    UPDATE Loans
    SET InterestRate = InterestRate - 1
    WHERE CustomerID IN (
        SELECT CustomerID
        FROM Customers
        WHERE TRUNC(MONTHS_BETWEEN(SYSDATE, DOB) / 12) > 60
    );

    COMMIT;
END;
/



--2:
ALTER TABLE Customers ADD IsVIP VARCHAR2(5) DEFAULT 'FALSE';

BEGIN
    UPDATE Customers
    SET IsVIP = CASE 
                   WHEN Balance > 2000 THEN 'TRUE' 
                   ELSE IsVIP 
                END
    WHERE Balance > 2000;

    COMMIT;
END;
/


--3:
BEGIN
    FOR loan_rec IN (
        SELECT l.LoanID, l.CustomerID, c.Name, l.EndDate
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan ID ' || loan_rec.LoanID ||
                             ' for Customer ' || loan_rec.Name ||
                             ' (Customer ID: ' || loan_rec.CustomerID || ')' ||
                             ' is due on ' || TO_CHAR(loan_rec.EndDate, 'YYYY-MM-DD') || '.');
    END LOOP;
END;
/
