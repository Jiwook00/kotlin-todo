data class Todo(
    val id: Int,
    var title: String,
    var description: String = "",
    var isCompleted: Boolean = false
)

// 참고
//- 아토믹 코틀린 35장: 데이터 클래스
//- 아토믹 코틀린 04장: var와 val
//- 아토믹 코틀린 31장: 이름 붙은 인자와 디폴트 인자