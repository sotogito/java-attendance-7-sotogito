#### Service
- AttendanceCheckService
- ReAttendanceService
- PrintService
- RiskPrintService
- 
#### Enum
- Function
  - CHECK_ATTENTION
  - RE_ATTENTION
  - PRINT
  - RISK_PRINT
  - QUIT

- AttendanceType
  - ATTENDANCE 출석
  - LATE 지각
  - ABSENCE 결석
  - NOTHING

- Week
  - MON~SUN
  - CHRISTMAS 는 따로 휴일 처리
    - LocalDataTime을 읽고 LocalData로 변환해서 찾기
---

#### 입력 객체
- Student
  - int 지각 횟수
  - int 결석 횟수
  - AttendanceType : 경고,면담, 재적 대상자인지 업데이트한다. - 대상자를 업데이트한다.
  - List<attendance>
    - 내부에 지각 횟수가 3회 이상이면 결석++;
  - comareable - 결석 - 내림차순
    
- attendance (출석을 저장하는 객체)
  - LocalDataTime
  - Week(요일 Enum 으로 저장) - 무슨 요일인지
  - 출석, 지각 여부

- Students
#### 필요(생성)객체
- 조회 객체 : Map or List

- 대상자 Finder
---

#### Result 객체
#### History 객체
- List or Map

---

#### Parser
#### Dto

---

#### Singleton
#### Helper
#### Policy 객체
- PolicyFinder

---

#### Reader
- AttendancesReader
#### Factory