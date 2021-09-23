CREATE OR REPLACE PROCEDURE PROCEDURE_STATS 
(
  P_STARTDATE IN VARCHAR2 
, P_ENDDATE IN VARCHAR2 
, P_COUSOR IN OUT SYS_REFCURSOR
) AS 
BEGIN
    open p_cousor for
    select b.name 도서명
         , b.price 가격
         , d.name 거래처명
         , c.name 입출고등록자
         , a.inout 입출고량
         , a.resulttotal 결과재고
         , b.total 현재재고
         , a.recorddate 등록일
    from snr a, book b, member c, account d
    where a.bookid = b.id and a.memberid = c.id and a.accountid = d.id
      and a.recorddate between P_STARTDATE and P_ENDDATE;
  NULL;
END PROCEDURE_STATS;