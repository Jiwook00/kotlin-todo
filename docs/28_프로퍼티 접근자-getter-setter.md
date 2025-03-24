## 프로터티(Property)와 접근자(Accessor)의 정의
- 프로터피: 클래스의 상태를 나타내는 변수. 코트린에서는 `var`(변경 가능) 또는 `val`(읽기 전용)로 선언한다.
- 접근자: 프로터피의 값을 읽거나(getter) 설정하는 (seeter) 특별한 함수
## 예제 코드
```kotlin
class Accessible {
    
    // 프로퍼티 1: mutable (변경 가능한 프로퍼티)
    var mutable: String = ""  // String 타입의 프로퍼티 선언 및 초기화
       
        // getter 접근자: 프로퍼티 값을 읽을 때 호출됨
        get() {
            println("mutable:get")  // getter가 호출될 때 메시지 출력
            return field  // 백킹 필드의 현재 값을 반환 (field는 프로퍼티의 실제 저장 공간)
        }
       
        // setter 접근자: 프로퍼티 값을 변경할 때 호출됨
        set(value) {  // value는 새로 할당할 값을 나타내는 매개변수
            println("mutable:set")  // setter가 호출될 때 메시지 출력
            field = value  // 백킹 필드의 값을 새 값으로 업데이트
        }
    
    // 프로퍼티 2: readOnly (읽기 전용 프로퍼티)
    val readOnly: String  // val로 선언했으므로 setter가 없음 (읽기 전용)
        
        // getter 접근자만 존재
        get() {
            println("readOnly:get")  // getter가 호출될 때 메시지 출력
            return mutable  // mutable 프로퍼티의 값을 반환 (자체 백킹 필드 없음)
        }
}

fun main() {
    // 클래스의 인스턴스 생성
    val accessible = Accessible()
    
    // mutable 프로퍼티에 값 할당 (setter 호출됨)
    accessible.mutable = "abc"
    
    // readOnly 프로퍼티 읽기 (getter 호출됨)
    // readOnly 프로퍼티의 getter는 내부적으로 mutable의 getter를 호출하므로 
    // "readOnly:get"과 "mutable:get"이 모두 출력됨
    println(accessible.readOnly)
}
```

### 백킹 필드 (Backing Field)
- `field` 키워드는 프로퍼티의 실제 값이 저장되는 공간(백킹 필드)을 참조
- `mutable` 프로퍼티는 백킹 필드를 가지고 있습니다(`field` 사용).
- `readOnly` 프로퍼티는 백킹 필드가 없습니다(값을 저장하지 않고 `mutable`에서 가져옴).
### 게터와 세터 없이도 가능한가?
게터, 세터를 명시적으로 선언하지 않아도 프로퍼티를 사용할 수 있음.
(자바에서는 사용하는 것이 일반적인 관행?)
- **간결함 우선**: 코틀린은 기본적으로 프로퍼티를 선언하면 자동으로 기본 접근자(게터/세터)를 생성함
- **필요할 때만 커스터마이징**: 코틀린은 특별한 로직이 필요한 경우에만 게터/세터를 직접 구현하도록 권장
- **프로퍼티 중심 설계**: 자바의 메서드 중심 설계와 달리, 코틀린은 상태(프로퍼티)를 중심으로 설계하고 접근자는 필요시에만 커스터마이징

예를 들어, 다음과 같은 경우에만 커스텀 접근자를 사용하는 것이 권장:
- 프로퍼티 접근 시 특별한 로직이 필요할 때 (검증, 로깅 등)
- 계산된 프로퍼티를 구현할 때
- 백킹 필드가 없는 프로퍼티가 필요할 때
- 프로퍼티 접근 시 부수 효과가 필요할 때


