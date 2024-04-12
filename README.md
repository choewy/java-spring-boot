# H2Database

아래 링크에 접속 후 OS에 따라 다운로드
(Windows 외에는 모두 All Platforms로 다운로드)

- https://h2database.com

아래 스크립트로 h2 실행할 수 있음

```zsh
cd ~

mv ~/Downloads/h2 .
chmod 755 ./h2/bin/h2.sh

./h2/bin/h2.sh
```

파일에 직접 접근하는 경우 여러 애플리케이션 간 충돌이 발생할 수 있음
따라서, 아래와 같이 socket으로 통신할 수 있도록 변경

- JDBC URL : jdbc:h2:tcp://localhost/~/h2

## Spring JPAData

- Spring JPAData는 JPA를 편리하게 활용할 수 있도록 도와주는 라이브러리일 뿐이기 떄문에 JPA에 대해서 깊게 공부한 다음 다루는 것을 권장

> 결국 Spring JPAData의 본질은 JPA이기 떄문임.

## AOP

- AOP는 ComponentScan 방식을 적용해도 되고, 직접 Been으로 등록해도 되나, Been으로 직접 등록해주는 방식을 더 선호

> Been에 직접 등록하면 제3자가 파악하기 훨씬 수월하기 때문임.
