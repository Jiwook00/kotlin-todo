class TodoManager {
    private val todoList = mutableListOf<Todo>()
    private var todoId = 1

    fun showMenu() {
        while(true) {
            println("\n==== Todo 관리 시스템 ====")
            println("1. 할 일 추가")
            println("2. 할 일 목록 보기")
            println("3. 할 일 완료/미완료 토글")
            println("4. 할 일 삭제")
            println("0. 종료")
            print("원하는 작업을 선택하세요: ")

            when (readlnOrNull()) {
                "1" -> println("addTodo()")
                "2" -> println("showtodoList()")
                "3" -> println("toggleTodo()")
                "4" -> println("deleteTodo()")
                "0" -> return
                else -> println("잘못된 입력입니다.")
            }

        }
    }



}
