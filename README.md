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

