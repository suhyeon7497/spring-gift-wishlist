# spring-gift-wishlist

### 2단계 step1 기능 요구사항
#### 상품을 추가하거나 수정하는 경우, 클라이언트로부터 잘못된 값이 전달될 수 있다. 잘못된 값이 전달되면 클라이언트가 어떤 부분이 왜 잘못되었는지 인지할 수 있도록 응답을 제공한다.

- [x] 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
- [x] 특수 문자
  - 가능: ( ), [ ], +, -, &, /, _
  - 그 외 특수 문자 사용 불가
- [x] "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.
- [x] controller에 @valid 어노테이션 추가
- [x] thymeleaf에 error message 보이는 곳 추가

### 2단계 step2 기능 요구사항
#### 사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현한다.
- 회원은 이메일과 비밀번호를 입력하여 가입한다.
- 토큰을 받으려면 이메일과 비밀번호를 보내야 하며, 가입한 이메일과 비밀번호가 일치하면 토큰이 발급된다.
- 토큰을 생성하는 방법에는 여러 가지가 있다. 방법 중 하나를 선택한다.
- (선택) 회원을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

#### TodoList
- [x] User Table 생성
- [x] User Class 생성
- [x] UserRequestDto 생성
- [x] TokenResponseDto 생성
- [x] UserDao 생성
- [x] UserController 생성
- [x] AuthService 생성
  - [x] Token validate
- [x] AuthInterceptor 생성
- [x] WebConfig에 등록


### 3단계 기능 요구 사항
#### 이전 단계에서 로그인 후 받은 토큰을 사용하여 사용자별 위시 리스트 기능을 구현한다.
- 위시 리스트에 등록된 상품 목록을 조회할 수 있다.
- 위시 리스트에 상품을 추가할 수 있다.
- 위시 리스트에 담긴 상품을 삭제할 수 있다.

#### TodoList
- [x] LoginUserArgumentResolver 작성
- [x] LoginUser annotation 작성
- [x] LoginUser class 작성
- [x] WebConfig에 추가
- [x] WishRequest 작성
- [x] WishResponse 작성
- [x] Wish Entity 작성
- [ ] WishList에 대한 Table 추가
- [ ] WishDao 작성
- [ ] WishController 작성
- [ ] AuthService 리팩토링
  - [ ] TokenProvider class
  - [ ] TokenParser class
  - [ ] TokenVerifier class
