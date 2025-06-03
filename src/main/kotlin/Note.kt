import NotesApplication.scanner

class Note(val noteTitle:String, val noteValue: String) {

    fun showNoteValue(){
        while(true){
            println("Заголовок заметки: $noteTitle")
            println("$noteValue")
            println("Для перехода в меню чтения заметки нажмите Enter...")
            scanner.nextLine()
            return
        }
    }

}