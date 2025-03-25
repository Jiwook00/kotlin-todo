# readlnOrNull()과 readLine()
참고: [Kotlin으로 Todo 앱 만들기 (콘솔 기반)#Null 안전성 (TodoManager.kt)](../README.md#null-안전성-todomanagerkt)

- readLine을 사용하면 readlnOrNull() 로 대체하라고 한다. Null 안전성 강화를 위해 새롭게 추가되었다고 한다.
- readlnOrNull() 함수는 사용자가 키보드로 입력을 완료하고 Enter 키를 누를때까지 프로그램 실행을 일시 중지. 사용자 입력을 기다림