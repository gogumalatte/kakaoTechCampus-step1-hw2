# kakaoTechCampus-step1-hw2
## Lv 0
### API 명세서 작성
| 기능       | Method | URL             | request                          | response  | 상태코드                                       |
| -------- | ------ | --------------- | -------------------------------- | --------- | ------------------------------------------ |
| 일정 등록    | POST   | /schedules      | 요청 body                          | 등록된 일정 정보 | 200: 정상등록                                  |
| 일정 단건 조회 | GET    | /schedules/{id} | 요청 param                         | 단건 응답 정보  | 200: 정상조회                                  |
| 일정 목록 조회 | GET    | /schedules      | 요청 param (`writer`, `updatedAt`) | 다건 응답 정보  | 200: 정상조회                                  |
| 일정 수정    | PUT    | /schedules/{id} | 요청 body (수정 데이터 + 비밀번호)          | 수정된 일정 정보 | 200: 정상수정<br>403: 비밀번호 불일치<br>404: 존재하지 않음 |
| 일정 삭제    | DELETE | /schedules/{id} | 요청 body (비밀번호)                   | -         | 200: 정상삭제<br>403: 비밀번호 불일치<br>404: 존재하지 않음 |

<br/>
