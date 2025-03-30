enum class Errors {
    INCORRECT_FORMAT,
    EMPTY_TITLE,
    INCORRECT_ORDINAL_NUMBER,
    EMPTY_LIST,
    EMPTY_NOTE_VALUE;
    override fun toString(): String =
        when (this) {
            INCORRECT_FORMAT -> "Ошибка: Неверный формат ввода данных."
            EMPTY_TITLE -> "Ошибка: Заголовок должен содержать хотя бы один символ."
            INCORRECT_ORDINAL_NUMBER -> "Ошибка: Порядковый номер введенный Вами отсутствует в списке."
            EMPTY_LIST -> "Ошибка: Список пуст."
            EMPTY_NOTE_VALUE -> "Ошибка: Поле значения заметки не может быть пустым."
        }
}