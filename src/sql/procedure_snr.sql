create or replace PROCEDURE PROCEDURE_SNR 
(
  P_BOOKID IN NUMBER 
, P_MEMBERID IN NUMBER 
, P_ACCOUNTID IN NUMBER 
, P_INOUT IN NUMBER 
, P_RECORDDATE IN VARCHAR2 
, P_cousor in out sys_refcursor
) AS
    v_originTotal NUMBER;
    v_newTotal NUMBER;
BEGIN    
    select book.total 
    into v_originTotal 
    from book 
    where book.id = P_bookid;
    v_newTotal := v_originTotal + P_INOUT;

    insert into snr(
         bookid
        ,memberid
        ,accountid
        ,inout
        ,recorddate
        ,resulttotal)
    values(
         P_BOOKID
        ,P_MEMBERID
        ,P_ACCOUNTID
        ,P_INOUT
        ,P_RECORDDATE
        ,v_originTotal);
    
    update book 
    set book.total = v_newTotal 
    where book.id = P_BOOKID;
    
    open p_cousor for
    select book.id, book.name, book.price, book.total 
    from book 
    where book.id = P_bookid;
  
END PROCEDURE_SNR;