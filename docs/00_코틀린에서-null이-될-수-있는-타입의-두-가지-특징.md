# 코틀린에서 null이 될 수 있는 타입(String?와 같은)의 두 가지 특징

[Kotlin으로 Todo 앱 만들기 (콘솔 기반)#String과 String?](../README.md#string과-string) 에서 "일반적인 메서드 직접 호출이 제한됩니다"라는 내용이 나온다. 이것에 대한 보충 설명을 하자면
> `String`과 `String?`는 서로 다른 타입으로 취급됩니다. null이 될 수 있는 타입은 null 값을 허용하지만, 그 대신 일반적인 메서드 직접 호출이 제한된다.

코틀린에서 null이 될 수 있는 타입(`String?`와 같은)은 두 가지 특징을 가진다:

1. `null` 값을 저장할 수 있다. (이것이 허용되는 부분)
2. 해당 객체의 메서드나 프로퍼티에 직접 접근할 수 없다. (이것이 제한되는 부분)

**예를 들어:**
```kotlin
// 일반 String 타입
val normalString: String = "Hello"
println(normalString.length)  // 정상 작동: 5 출력

// Null이 될 수 있는 String? 타입
val nullableString: String? = "Hello"
// println(nullableString.length)  // 컴파일 오류! 직접 접근 불가
```

- 여기서 `nullableString`은 `String?` 타입이기 때문에 `null`일 가능성이 있다.
- 만약 이 변수가 실제로 `null`일 때 `.length`와 같은 메서드를 호출하면 런타임에 NullPointerException이 발생할 것이다.

코틀린은 이런 잠재적 오류를 방지하기 위해 컴파일 시점에서 null이 될 수 있는 타입의 메서드나 프로퍼티에 직접 접근하는 것을 허용하지 않는다.

**대신 안전하게 접근하기 위한 방법을 제공한다:**
```kotlin
// 안전 호출 연산자 사용
println(nullableString?.length)  // nullableString이 null이면 null 출력, 아니면 길이 출력

// null 검사 후 접근
if (nullableString != null) {
    println(nullableString.length)  // 이 블록 안에서는 안전하게 접근 가능
}

// 엘비스 연산자로 기본값 제공
val length = nullableString?.length ?: 0  // null이면 0 사용
```

**요약하자면,** "일반적인 메서드 직접 호출이 제한됩니다"라는 말은 null이 될 수 있는 타입에서는 안전하지 않은 방식으로 메서드나 프로퍼티에 바로 접근할 수 없다는 의미이다. 코틀린은 이러한 제한을 통해 NullPointerException을 방지하여 코드의 안전성을 높이고있다.
