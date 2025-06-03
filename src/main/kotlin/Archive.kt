import NotesApplication.isIntOrNot
import NotesApplication.isThereInNumberList
import NotesApplication.scanner


class Archive (val name:String) {
    private val notesStorage: MutableMap<Int, Note> = mutableMapOf()

    //region Actions called from the NoteScreen
    fun addNewNote() {
        while (true) {
            println("Введите заголовок заметки:")
            val newNoteName: String = scanner.nextLine()
            if (newNoteName.isEmpty()) {
                println(Errors.EMPTY_TITLE)
                continue
            } else {
                val key: Int = notesStorage.size + 1
                while(true){
                    println("Введите текст заметки:")
                    val newNoteValue: String = scanner.nextLine()
                    if (newNoteValue.isEmpty()) {
                        println(Errors.EMPTY_NOTE_VALUE)
                        continue
                    } else {
                        val newNote: Note = Note(newNoteName,newNoteValue)
                        notesStorage.put(key, newNote)
                        println("Создана новая заметка: $key. ${newNote.noteTitle}")
                        return
                    }
                }
            }
        }
    }
    fun chooseNoteFromList() {
        while(true){
            showAllNotes()
            if(notesStorage.isEmpty()) return
            val command:String = scanner.nextLine()
            if(command.isIntOrNot()) {
                val numberIntFormat: Int = command.toInt()
                if (isThereInNumberList(numberIntFormat,notesStorage)) {
                    val chosenNote = notesStorage.get(numberIntFormat)
                    if (chosenNote != null) {
                        val back:Boolean = openNoteMenu(chosenNote)
                        if(back) return
                    }
                } else if(numberIntFormat == 0) {
                    return
                }else{
                    println(Errors.INCORRECT_ORDINAL_NUMBER)
                }
            } else {
                println(Errors.INCORRECT_FORMAT)
                continue
            }
        }
    }
    //endregion

    //region Menu for opening and showing note
    private fun openNoteMenu(chosenNote: Note):Boolean{
        while(true){
            Menu.NotesMenuBrowser.showNoteActions()
            val userAction:String = scanner.nextLine()
            if(userAction.isIntOrNot()) {
                when(userAction.toInt()){
                    0 -> return true
                    1 -> chosenNote.showNoteValue()
                    else -> {
                        println(Errors.INCORRECT_ORDINAL_NUMBER)
                        continue
                    }
                }
            } else {
                println(Errors.INCORRECT_FORMAT)
                continue
            }
        }
    }
    //endregion

    //region notesStorage state methods
    fun getNoteStorageSize(): String {
        return if (this.notesStorage.isEmpty()) {
            "(список заметок пуст)"
        } else {
            "(количество заметок: ${this.notesStorage.size})"
        }
    }

    fun showAllNotes() {
        when (notesStorage.size) {
            0 -> println(Errors.EMPTY_LIST)
            else -> {
                println("Выбор заметки:")
                for (note in notesStorage) println("${note.key}. ${note.value.noteTitle}")
                println("____________________________")
                println("0. Вернуться в меню заметок.")
            }
        }
    }
    //endregion
}