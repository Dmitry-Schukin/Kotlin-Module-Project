sealed class Menu() {
    enum class ArchiveMenuEditor(){
        CREATE_ARCHIVE,
        SELECT_ARCHIVE,
        EXIT;
        companion object {
            fun showArchiveActions(){
                println("\t\tМеню архивов")
                for (action in ArchiveMenuEditor.values()) println("$action")
            }
        }
        override fun toString(): String =
            when (this) {
                CREATE_ARCHIVE -> "1. Создать архив"
                SELECT_ARCHIVE -> "2. Выбрать архив из списка ${NotesApplication.getArchiveStorageSize()}"
                EXIT -> "0. Выход из приложения"
            }
    }
    enum class NotesMenuEditor{
        CREATE_NOTE,
        SELECT_NOTE,
        BACK;
        companion object {
            fun showNoteActions(archive: Archive){
                println("\t\tМеню заметок")
                println("Выбранный архив: ${archive.name}")
                for (action in NotesMenuEditor.values()) {
                    if(action.ordinal==1) println("$action ${archive.getNoteStorageSize()}")
                    else println("$action")
                }
            }
        }
        override fun toString(): String =
            when (this) {
                CREATE_NOTE -> "1. Создать заметку"
                SELECT_NOTE -> "2. Выбрать заметку из списка"
                BACK -> "0. Вернуться в меню архивов"
            }
    }
    enum class NotesMenuBrowser(){
        OPEN_NOTE,
        BACK;
        companion object {
            fun showNoteActions(){
                println("\tМеню чтения заметки")
                for (action in NotesMenuBrowser.values()) println("$action")
            }
        }
        override fun toString(): String =
            when (this) {
                OPEN_NOTE -> "1. Отобразить заметку"
                BACK -> "0. Вернуться в меню заметок"
            }
    }
}