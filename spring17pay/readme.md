# 카카오페이

## 결제 요청

- 서드파티 메세징 서비스
- [카카오 개발자 페이지](https://developers.kakao.com/)

## 결제 요청 프로세스
- Client, Server, PG

1. 결제 요청 C -> S
2. 결제 요청 전달 S -> P
3. 결제 페이지 전달 P -> S
4. 결제 페이지로 리다이렉트 S -> C
5. 결제 완료 C -> P

### 코드정보
- cid
	- 가맹점(결제를 요청할 회사) 코드
	- 제휴를 통해 발급받는 코드
	- 개발 테스트시 사용 코드: TC0ONETIME
	
- partner_order_id
	- 가맹점의 주문번호
	- 서버에서 임의로 만들어내는 결제 고유 ID(테이블 PK 사용)

- partner_user_id
	- 가맹점에서 사용자를 구분하는 ID
	- 서버에서 임의로 만들어내는 사용자 고유 ID(테이블 PK 사용)

### 제품정보
- item_name
	- 판매할 상품의 명칭(사용자 화면에 표시되는 역할)
	- 구매하는 상품이 여러 개인 경우가 있음(OOO 외 ?개 형태로 설정)
	
- quantity	
	- 판매 수량

- total_amount
	- 총 결제 금액. PG사는 개별 결제 금액에 관심이 없음

- tax_free_amount
	- 총 비과세 금액. 업체에 따라 비과세 품목이 있는 경우 설정이 가능.
	- 일반적으로는 0원

### 처리정보
- approval_url
	- 사용자가 카카오페이 결제에 성공했을 때 신호가 들어올 페이지 주소를 작성
	- 반드시 카카오 API에 등록해놓은 도메인으로 시작해야 한다

- fail_url	
	- 사용자가 카카오페이 결제에 실패했을 때 신호가 들어올 페이지 주소를 작성
	- 반드시 카카오 API에 등록해놓은 도메인으로 시작해야 한다

- cancel_url
	- 사용자가 카카오페이 결제를 취소했을 때 신호가 들어올 페이지 주소를 작성
	- 반드시 카카오 API에 등록해놓은 도메인으로 시작해야 한다