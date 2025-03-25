class TodoManager {
    private val todoList = mutableListOf<Todo>()
    private var todoId = 1

    fun showMenu() {
        while(true) {
            println("\n==== Todo 관리 시스템 ====")
            println("1. 할 일 추가")
            println("2. 할 일 목록 보기")
            println("2-1. 완료된 할 일만 보기")
            println("2-2. 진행 중인 할 일만 보기")
            println("2-3. 제목순으로 정렬하여 보기")
            println("3. 할 일 완료/미완료 토글")
            println("4. 할 일 삭제")
            println("5. 키워드 검색")
            println("0. 종료")
            print("원하는 작업을 선택하세요: ")

            when (readlnOrNull()) {
                "1" -> addTodo()
                "2" -> showTodoList()
                "2-1" -> showCompletedTodoList()
                "2-2" -> showActivityTodoList()
                "2-3" -> showTodoListSortedByTitle()
                "3" -> toggleTodo()
                "4" -> deleteTodo()
                "5" -> addSearchByKeyword()
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
    private fun showTodoList() {
        displayTodoList(todoList, "할 일 목록")
    }

    // 할 일 완료, 미완료 토글
    private fun toggleTodo() {
        print("완료/미완료 상태를 변경할 할 일의 ID를 입력하세요: ")
        val id = readlnOrNull()?.toIntOrNull()

        val todo = findTodoById(id) ?: return

        val newStatus = !todo.isCompleted
        todo.isCompleted = newStatus
        println("""할 일 상태가 "${if (newStatus) "완료" else "미완료"}"로 변경되었습니다. """)
    }

    // 할 일 삭제
    private fun deleteTodo() {
        print("삭제할 할 일의 ID를 입력하세요: ")
        val id = readlnOrNull()?.toIntOrNull()

        val todo = findTodoById(id) ?: return

        todoList.remove(todo)
        println("할 일이 삭제되었습니다.")
    }

    private fun findTodoById(id: Int?): Todo? {
        if (id == null) {
            println("해당 ID의 할 일을 찾을 수 없습니다.")
            return null
        }

        val todo = todoList.find { it.id == id }

        if (todo == null) {
            println("해당 ID의 할 일을 찾을 수 없습니다.")
        }

        return todo
    }

    // 컬렉션에 대한 연산
    private fun showCompletedTodoList() {
        filterTodoList({ it.isCompleted }, "완료된 할 일")
    }

    private fun showActivityTodoList() {
        filterTodoList({ !it.isCompleted }, "진행 중인 할 일")
    }

    private fun showTodoListSortedByTitle() {
        val sortedTodoList = todoList.sortedBy { it.title }
        displayTodoList(sortedTodoList, "제목순 정렬된 한 일")
    }

    private fun displayTodoList(todoList: List<Todo>, message: String) {
        if (todoList.isEmpty()) {
            println("표시할 할 일이 없습니다.")
            return
        }

        println("\n==== $message ====")
        todoList.forEach { (id, title, description, isCompleted) ->
            val status = if (isCompleted) "[완료]" else "[진행중]"
            println("$id: $status $title - $description")
        }
    }


    private fun filterTodoList(predicate: (Todo) -> Boolean, message: String) {
        val filteredTodoList = todoList.filter(predicate)
        displayTodoList(filteredTodoList, message)
    }

    private fun addSearchByKeyword() {
        print("검색할 키워드 입력: ")
        val keyword = readlnOrNull()?.lowercase() ?: ""

        filterTodoList(
            { it.title.lowercase().contains(keyword) || it.description.lowercase().contains(keyword) },
            "키워드 '$keyword' 검색 결과"
        )
    }
}
