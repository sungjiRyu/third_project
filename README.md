# 쿨피스(School Fitness)

>학생 대상 체력관리 서비스
><br> 프로젝트 개요 -> <a href="https://github.com/sungjiRyu/third_project/files/11264367/3.prj.pdf"><img src="https://img.shields.io/badge/PPT-F46D01?style=flat&logo=PPT&logoColor=white" /></a>
<a href="https://www.canva.com/design/DAFZamFxwmY/80jjBzyxkYYnk9qE58kZ1g/view?utm_content=DAFZamFxwmY&utm_campaign=designshare&utm_medium=link&utm_source=publishsharelink" target="_blank">


<br>

## 목차
1. [제작 기간 & 제작 인원](#1-제작-기간--제작-인원)
2. [담당 파트](#2-담당-파트)
3. [사용 기술](#3-사용-기술)
4. [ERD 설계](#4-erd-설계)
5. [주요 코드](#5-주요-코드)
6. [단위테스트](#6-단위-테스트)

<br><br><br>

## 1. 제작 기간 & 제작 인원
- 2022/12/28 ~ 2023/02/06
- 참여인원 7명(프론트 3명, 백엔드 4명)

<br><br><br>

## 2. 담당 파트
백엔드 팀원으로 아래의 기능들을 restfulAPI로 구현했습니다.
- 회원가입 : 일반회원가입, 점주회원가입, 유효성 체크
- 로그인 
- 아이디&비밀번호 찾기 : 인증번호 생성, 이메일or전화번호로 인증번호 발송, 임시비밀번호 발급
- 마이페이지 : 내정보 조회/수정, 회원탈퇴

<br><br><br>

## 3. 사용 기술
>**Back-end**<br>
<div align=center>
  <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Conda-Forge&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=Spring Boot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Gradle-02303A?style=flat&logo=Gradle&logoColor=white"/>
  <img src="https://img.shields.io/badge/JPA-59666C?style=flat&logo=JPA&logoColor=white"/>
  <img src="https://img.shields.io/badge/Tomcat-F8DC75?style=flat&logo=Apache Tomcat&logoColor=white"/>
</div>

>**open-api**<br>
<div align=center>
 <img src="https://img.shields.io/badge/KakaoMap-FFCD00?style=flat&logo=KakaoMap&logoColor=white"/>
  <img src="https://img.shields.io/badge/ZXING-4285F4?style=flat&logo=ZXING&logoColor=white"/>
  <img src="https://img.shields.io/badge/CoolSMS-9999FF?style=flat&logo=CoolSMS&logoColor=white"/>
</div>

>**형상관리**<br>
<div align=center>
<img src="https://img.shields.io/badge/GitHub-181717?style=for-the-flat&logo=GitHub&logoColor=white">
<img src="https://img.shields.io/badge/Git-F05032?style=for-the-flat&logo=Git&logoColor=white">
</div>

<br><br><br>

## 4. ERD 설계
![prj01_ERD](https://user-images.githubusercontent.com/116089824/228006446-84c683bd-8ccd-4b07-b786-eb9b99bd55dd.png)


<br><br><br>

## 5. 주요 코드

<br>

### 5.1 아이디 찾기 인증번호 발송(휴대폰)
```
public Map<String, Object> IdAuthNumByPhone(PostFindIdDTO data, HttpSession session) {
        MemberEntity user = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String name = data.getMiName();
        String phoneNum = data.getMiPhoneNum();
        try {
            user = mRepo.findByMiNameAndMiPhoneNum(data.getMiName(), data.getMiPhoneNum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 회원정보가 없을때(탈퇴회원도 동일)
        if(name == null){
            resultMap.put("status", false);
            resultMap.put("message", "이름을 입력해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(phoneNum == null){
            resultMap.put("status", false);
            resultMap.put("message", "전화번호를 입력해 주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if (user == null || user.getMiStatus() == 3) {
            resultMap.put("status", false);
            resultMap.put("message", "등록된 회원정보와 일치하지 않습니다");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else{
            // 인증번호 생성
            Integer certificationNum = GetAuthNum.getAuthNum();
            // 인증번호 메시지로 발송
            sendMessage.sendAuthNumbyPhone(data.getMiPhoneNum(), certificationNum);

            // 입력받은 이름, 전화번호와 일치하는 사용자 정보 seesion에 저장
            // 생성한 인증번호 session 에 저장
            // session에 저장된 정보는 3분후 삭제
            session.setAttribute("authNum", certificationNum);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(60*3);

            resultMap.put("status", true);
            resultMap.put("message", "인증번호가 발송되었습니다. 3분안에 입력해 주세요");
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("authNum", certificationNum);

        }
        return resultMap;
    }
```

<br>

>입력받은 이름과 전화번호로 회원가입된 사용자인지 조회합니다.<br>
>user객체가  null이면 회원가입되지 않은 사용자로 판단하고 오류메세지를 출력합니다.<br>
>유효성 검사를 통과했을 시 getAuthNum 메서드를 통해 4자리 숫자로된 인증번호를 생성합니다.<br>
>외부api를 사용한 sendMessage 메서드를 통해 입력한 전화번호로 인증번호가 전송됩니다.

<br><br><br>

## 6. 단위 테스트

<br>

### 8.1 Postman
![prj01_postman1](https://user-images.githubusercontent.com/116089824/228015932-ac14d530-b403-4354-8986-af8ec32f6055.png)



<br>

<br>
<details>
<summary>기타 postman 테스트 보기</summary>


![화면 캡처 2023-03-28 020912](https://user-images.githubusercontent.com/116089824/228015424-eb2d3315-bca7-45b6-8971-5eb479f578dd.png)
![화면 캡처 2023-03-28 020842](https://user-images.githubusercontent.com/116089824/228015426-abf618c0-8a15-4f1f-b07e-80b76ee28f12.png)
![화면 캡처 2023-03-28 020823](https://user-images.githubusercontent.com/116089824/228015429-3e0afecc-525f-411e-a38b-8287d46c2f9a.png)
![화면 캡처 2023-03-28 020805](https://user-images.githubusercontent.com/116089824/228015433-e24237d3-da1a-4d3d-b44d-62643e06ba27.png)
![화면 캡처 2023-03-28 020740](https://user-images.githubusercontent.com/116089824/228015435-7e6ca6cf-43fe-48e4-acad-f44ffbe9fc94.png)
![화면 캡처 2023-03-28 020717](https://user-images.githubusercontent.com/116089824/228015438-ae8413d5-8609-45ce-ab76-fa3e7512e70b.png)
![화면 캡처 2023-03-28 020658](https://user-images.githubusercontent.com/116089824/228015441-0d52de65-f8e7-43ea-bd9a-b01e26107ce5.png)
![화면 캡처 2023-03-28 020627](https://user-images.githubusercontent.com/116089824/228015443-e4b132eb-02d8-4abf-bb6d-529b59238463.png)


</details>

<br>

