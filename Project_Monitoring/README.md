# monitoring

### 모니터링 제어 시스템

진행 기간 : 2020. 05. 22 ~ 2020. 05.24

인원 : 1인

Tool : Eclipse

Java Version : JavaSE-1.8

Tomcat Version : 9.0.33

MySql Version : 8.0

Server
  - client와 Socket통신 구현
  - client에서 보내온 데이터가 프로토콜에 맞게 들어오면 db에 저장기능
  - http프로토콜을 사용하여 외부에서 client id에 동작 제어를 실시하면 해당 제어에 맞는 데이터를 client에 전송
  - 다중 client 연결을 위해 thred사용
  - client와 특정시간마다 연결상태 확인을 통신을 하면서 특정시간안에 응답이 없을 시 client와의 연결 해제 기능 구현
  
client
  - server에 난수데이터를 생성하여(센서 데이터를 표현) 주기적으로 전송
  - 특정시간마다 Server에 데이터를 전송하고 응답이 없으면 reconnect 시도
  - server로 프로토콜에 맞는 데이터가 들어올시 해당 데이터에 맞는 제어 동작 실시
  - reconnect와 server제어 데이터 수신을 확인 할 수 있도록 텍스트창에 출력
  
web-server
  - spring 사용하여 구현
 
html
  - 등록되어있는 client들의 연결상태 확인 및 접속하여 저장되어있는 센서데이터 확인 및 제어 동작 기능

개선사항
  - 추가적인 기능 및 페이지 디자인 개선 필요
  - 기능 추가로 db구조 설계 필요
