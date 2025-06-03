import java.util.Scanner

object NotesApplication {
    private var archiveStorage : MutableMap<Int, Archive> = mutableMapOf()
    var scanner = Scanner(System.`in`)

    //region GeneralScreen
    fun openNotesApplication(){
        println("\tДобро пожаловать в приложение \"Заметки\"")
        while(true){
            println("Введите порядковый номер одного из предложенных действий:")
            Menu.ArchiveMenuEditor.showArchiveActions()
            val userAction:String = scanner.nextLine()
            if(userAction.isIntOrNot()) {
                when(userAction.toInt()){
                    0 -> return
                    1 -> createArchive()
                    2 -> chooseArchive()
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

    //region NoteScreen
    private fun openNoteScreen(chosenArchive: Archive):Boolean{
        while(true){
            Menu.NotesMenuEditor.showNoteActions(chosenArchive)
            val userAction:String = scanner.nextLine()
            if(userAction.isIntOrNot()) {
                when(userAction.toInt()){
                    0 -> return true
                    1 -> chosenArchive.addNewNote()
                    2 -> chosenArchive.chooseNoteFromList()
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

    //region Actions called from the GeneralScreen
    private fun createArchive(){
        while(true){
            println("Введите заголовок нового архива:")
            val newArchiveName:String = scanner.nextLine()
            if(newArchiveName.isEmpty()){
                println(Errors.EMPTY_TITLE)
                continue
            } else {
                val key:Int = archiveStorage.size+1
                val newArchive:Archive = Archive(newArchiveName)
                archiveStorage.put(key,newArchive)
                println("Создан новый архив: $key. ${newArchive.name}")
                return
            }
        }
    }
    private fun chooseArchive(){
        while(true){
            showAllArchives()
            if(archiveStorage.isEmpty()) return
            val command:String = scanner.nextLine()
            if(command.isIntOrNot()) {
                val numberIntFormat: Int = command.toInt()
                if (isThereInNumberList(numberIntFormat,archiveStorage)) {
                    val chosenArchive = archiveStorage.get(numberIntFormat)
                    if (chosenArchive != null) {
                        val back:Boolean = openNoteScreen(chosenArchive)
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

    //region archiveStorage state methods
    private fun showAllArchives(){
        when(archiveStorage.size){
            0 -> println(Errors.EMPTY_LIST)
            else -> {
                println("Выбор архива:")
                for (archive in archiveStorage) println("${archive.key}. ${archive.value.name}")
                println("____________________________")
                println("0. Вернуться в меню архивов.")
            }
        }
    }
    fun getArchiveStorageSize():String{
        return if(archiveStorage.isEmpty()){
            "(список архивов пуст)"
        } else {
            "(количество архивов: ${archiveStorage.size})"
        }
    }
    //endregion

    //region Validation methods
    fun <T> isThereInNumberList(chosenNumber:Int, storage: MutableMap<Int,T>):Boolean{
        val keysSet = storage.keys
        return keysSet.contains(chosenNumber)
    }
    fun String.isIntOrNot(): Boolean {
        val commandNumber = toIntOrNull()
        return when (commandNumber) {
            null -> false
            else -> true
        }
    }
    //endregion

}