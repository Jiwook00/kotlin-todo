class TodoManager {
    private val todoList = mutableListOf<Todo>()
    private var todoId = 1

    fun showMenu() {
        // 그럴듯한 와일 조건은 없을까?
        while(true) {
            println("\n==== Todo 관리 시스템 ====")
            println("1. 할 일 추가")
            println("2. 할 일 목록 보기")
            println("3. 할 일 완료/미완료 토글")
            println("4. 할 일 삭제")
            println("0. 종료")
            print("원하는 작업을 선택하세요: ")

            when (readlnOrNull()) {
                "1" -> println(addTodo())
                "2" -> println(showtodoList())
                "3" -> println(toggleTodo())
                "4" -> println(deleteTodo())
                "0" -> return
                else -> println("잘못된 입력입니다.")
            }

        }
    }

    private fun addTodo() {
        print("제목을 입력하세요: ")
        val title = readlnOrNull() ?: ""

        print("설명을 입력하세요(선택사항): ")
        val description = readlnOrNull() ?: ""

        val todo = Todo(todoId, title, description)
        todoList.add(todo)
        todoId++

        println("할 일이 추가되었습니다.")
    }

    // 할 일 목록 보기
    private fun showtodoList() {
        if (todoList.isEmpty()) {
            println("등록된 할 일이 없습니다.")
            return
        }

        println("\n==== 할 일 목록 ====")
        for ((id, title, description, isCompleted) in todoList) {
            val status = if (isCompleted) "[완료]" else "[진행중]"
            println("$id: $status $title - $description")
        }

    }

    // 할 일 완료, 미완료 토글
    private fun toggleTodo() {
        print("완료/미완료 상태를 변경할 할 일의 ID를 입력하세요: ")
        val id = readlnOrNull()?.toIntOrNull()

        if (id == null) {
            println("유요한 ID를 입력하세요.")
            return
        }

        val todo = todoList.find { it.id == id }
        // 참고: 44 람다
        // it 부분 참고.
        if (todo == null) {
            println("해당 ID의 할 일을 찾을 수 없습니다.")
            return
        }

        val newStatus = !todo.isCompleted
        todo.isCompleted = newStatus
        println("""할 일 상태가 "${if (newStatus) "완료" else "미완료"}"로 변경되었습니다. """)
    }

    // 할 일 삭제
    private fun deleteTodo() {
        print("삭제할 할 일의 ID를 입력하세요: ")
        val id = readlnOrNull()?.toIntOrNull()
        if (id == null) {
            println("해당 ID의 할 일을 찾을 수 없습니다.")
            return
        }

        val todo = todoList.find { it.id == id }
        if (todo == null) {
            println("해당 ID의 할 일을 찾을 수 없습니다.")
            return
        }

        todoList.remove(todo)
        println("할 일이 삭제되었습니다.")
    }


}
