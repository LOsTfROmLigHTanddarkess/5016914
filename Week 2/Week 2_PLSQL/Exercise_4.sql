select * from loans;
select * from accounts

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a function CalculateAge that takes a customer's date of birth as input and returns their age in years.

CREATE FUNCTION CalculateAge (
    p_dob DATE
) RETURN NUMBER
IS
    v_age NUMBER;
BEGIN
    v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END CalculateAge;
/

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

DECLARE
    v_age NUMBER;
BEGIN
    v_age := CalculateAge(TO_DATE('1980-05-18', 'YYYY-MM-DD'));
    DBMS_OUTPUT.PUT_LINE('Age: ' || v_age);
END;
/
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a function CalculateMonthlyInstallment that takes the loan amount, interest rate, and loan duration in years as input and returns the monthly installment amount.

CREATE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount NUMBER,
    p_interest_rate NUMBER,
    p_loan_duration_years NUMBER
) RETURN NUMBER
IS
    v_monthly_interest_rate NUMBER;
    v_number_of_payments NUMBER;
    v_monthly_installment NUMBER;
BEGIN
    v_monthly_interest_rate := p_interest_rate / 12 / 100;
    v_number_of_payments := p_loan_duration_years * 12;

    IF v_monthly_interest_rate = 0 THEN
        v_monthly_installment := p_loan_amount / v_number_of_payments;
    ELSE
        v_monthly_installment := p_loan_amount * v_monthly_interest_rate / (1 - POWER(1 + v_monthly_interest_rate, -v_number_of_payments));
    END IF;

    RETURN v_monthly_installment;
END CalculateMonthlyInstallment;
/

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

DECLARE
    v_monthly_installment NUMBER;
BEGIN
    v_monthly_installment := CalculateMonthlyInstallment(10000, 5, 5);
    DBMS_OUTPUT.PUT_LINE('Monthly Installment: ' || v_monthly_installment);
END;
/
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Write a function HasSufficientBalance that takes an account ID and an amount as input and returns a boolean indicating whether the account has at least the specified amount.

CREATE FUNCTION HasSufficientBalance (
    p_account_id NUMBER,
    p_amount NUMBER
) RETURN BOOLEAN
IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;

    IF v_balance >= p_amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
END HasSufficientBalance;
/

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

DECLARE
    v_has_sufficient_balance BOOLEAN;
BEGIN
    v_has_sufficient_balance := HasSufficientBalance(1, 1500);
    IF v_has_sufficient_balance THEN
        DBMS_OUTPUT.PUT_LINE('The account has sufficient balance.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('The account does not have sufficient balance.');
    END IF;
END;
/
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

