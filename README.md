# 너의 급여는

## 요구 사항

### 회원

- 회원가입을 할 수 있다.
- 회원 로그인을 할 수 있다.
  - 회원 로그인시 토큰을 이용하여 로그인을 한다.
- 사용자와 관리자로 나뉜다.

### 근무

- 하루 근무 시간을 등록할 수 있다.
- 시급을 바탕으로 하루 급여를 구할 수 있다
- 오늘 하루 발생한 이벤트를 등록할 수 있다.

## 사용 기술 및 환경

Spring boot, Gradle, Jpa, MariaDB, Java8, Spring Security JWT, Rest API(Springfox-swagger-ui)

## 화면 설계

https://ovenapp.io/view/Dm3b4wjnAwtJoaNK2mdLrJFOFy3BAnvR/Lcpqs

## 브랜치 관리 전략

Git Flow를 사용.<br />

- Master : 배포시 사용
- Develop : 다음 배포 버전을 개발하는 브런치
- Feature : 기능 개발을 진행할 때 사용
- Release : 배포를 준비할 때 사용
- Hot-Fix : 배포를 진행한 후 발생한 버그를 수정해야 할 때 사용.

  <br />
  <b>브랜치 관리 전략 참고 문헌</b><br>

- 우아한 형제들 기술 블로그(http://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html)
