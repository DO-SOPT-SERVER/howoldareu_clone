# iOS4-SOPKATHON-Server

## ⭐️ Our Service
---

🔔 서비스 이름: 너 몇살이야

🔔 소개 : 진짜 어른이 되어가는 과정, 너 몇살이야?

<br/><br/>


## 👩‍👧‍👧 Our Team
---

|                **🍀 [김성은](https://github.com/sung-silver)**                 |                **🍀 [박재연](https://github.com/parkjyun)**                 |
  |:-----------------------------------:|:-----------------------------------:|
|                                 Server Developer                                  |                               Server Developer                              |
|        답변 입력 API, 질문 조회 API<br />       |        온보딩 API, 누적 결과 반환 API<br />         |

<br/><br/>


## 📁 Our Project
---
### Tech Stack
- Spring Boot 3.2.0
- Spring Data JPA
- Java 17
- AWS EC2
- AWS RDS - MySQL
<br><br>
### Execution
```
cd Server
cd src/main
mkdir resources
cd resources
vim application.yml
cd ../../../
chmod +x ./gradlew
./gradlew clean build -x test
cd build/libs
java -jar Server-0.0.1-SNAPSHOT.jar

```
<br><br>
### Package Structure
```
|-- 📁 .github
|-- 📁 gradle
|-- 📁 src
	|-- 📁 main
		|-- 📁 java
			|-- 📁 Server
				|-- 📁 common
				|-- 📁 contoller
					|-- 📁 request
					|-- 📁 response
				|-- 📁 domain
				|-- 📁 exception
					|-- 📁 model
				|-- 📁 repository
				|-- 📁 service
		|-- 📁 resources
	|-- 📁 test
	|-- .gitignore
	|-- build.gradle
	|-- gradlew
	|-- gradlew.bat
	|-- README.md
	|-- settings.gradle
|-- 📁 External Libraries
```
<br/><br/>

### Architecture Structure
- 추가 예정


<br><br>
## ✨ Our Convention
---
### ✔️ Commit Convention
- ✅ `[CHORE]` : 동작에 영향 없는 코드 or 변경 없는 변경사항(주석 추가 등)
- ✨ `[FEAT]` : 새로운 기능 구현
- ➕ `[ADD]` : Feat 이외의 부수적인 코드 추가, 라이브러리 추가, 새로운 파일 생성
- 🔨 `[FIX]` : 버그, 오류 해결
- ⚰️ `[DEL]` : 쓸모없는 코드 삭제
- 📝 `[DOCS]` : README나 WIKI 등의 문서 수정
- ✏️ `[CORRECT]` : 주로 문법의 오류나 타입의 변경, 이름 변경시
- ⏪️ `[RENAME]` : 파일 이름 변경시
- ♻️ `[REFACTOR]` : 전면 수정
- 🔀 `[MERGE]`: 다른 브랜치와 병합

ex) commit -m "[FEAT] user API 구현"
<br><br>
### 🌳 Branch Convention
- [feat] : 기능 추가
- [fix] : 에러 수정, 버그 수정
- [docs] : README, 문서
- [refactor] : 코드 리펙토링 (기능 변경 없이 코드만 수정할 때)
- [modify] : 코드 수정 (기능의 변화가 있을 때)
- [chore] : gradle 세팅, 위의 것 이외에 거의 모든 것
ex) feat/#9
<br><br>
### 🌳 branch 전략
- `main` : 메인 브랜치
- `main`에 직접적인 commit, push는 가급적 금지합니다
- 작업 전, 반드시 `main` 브랜치를 pull 받고 시작합니다
  `git pull origin main`
- 기능 개발 시 issue를 만듭니다.
- 기능 개발 시 `feature/#이슈번호` 브랜치를 파서 관리합니다
- 작은 기능별로 `commit message rules`에 따라 커밋을 진행합니다
- 작업 완료 시 `main` 브랜치로 Pull Request를 보냅니다
- 시간 상 특별한 이슈가 없다고 판단되면, 최종적으로 `main` 브랜치로 merge합니다
- 다 쓴 브랜치는 삭제합니다
<br/><br/>

### 🌳 branch 구조

```jsx
- main
   ├── feat/#1
   └── feat/#2
```
<br/><br/>

### ✔️ Code Convention
---
1. 변수는 CamelCase를 기본으로 한다. <br>
2. URL, 파일명 등은 kebab-case를 사용한다. <br>
3. 패키지명은 단어가 달라지더라도 무조건 소문자를 사용한다. <br>
4. ENUM이나 상수는 대문자로 네이밍한다. <br>
5. 함수명은 소문자로 시작하고 동사로 네이밍한다. <br>
6. 클래스명은 명사로 작성하고 UpperCamelCase를 사용한다. <br>
7. 객체 이름을 함수 이름에 중복해서 넣지 않는다. (= 상위 이름을 하위 이름에 중복시키지 않는다.) <br>
8. 컬렉션은 복수형을 사용하거나 컬렉션을 명시해준다. <br>
9. 이중적인 의미를 가지는 단어는 지양한다. <br>
10. 의도가 드러난다면 되도록 짧은 이름을 선택한다. <br>
<br/><br/>
