class Archive(
    var name: String,
    val notes: MutableMap<String, String> = mutableMapOf()
) {
    fun addNote(name: String, note: String): Boolean {
        val oldSize = notes.size

        if (name == "") {
            println("Name should contain at least one letter or number")
            return false
        }

        if (note == "") {
            println("Note should contain at least one letter or number")
            return false
        }

        notes[name] = note

        if (notes.size > oldSize) {
            println("New note created successfully")
            return true
        }
        println("Note with name $name already exists")
        return false
    }

    fun removeNote(name: String): Boolean {
        if (notes.containsKey(name)) {
            notes.remove(name)
            println("Note $name removed successfully")
            return true
        }

        println("There's no note with name $name")
        return false
    }

    fun renameNote(name: String): Boolean {
        if (notes.containsKey(this.name)) {
            notes[name] = notes[this.name].toString()
            println("Note renamed successfully")
            return true
        }

        println("There's no note with name $name")
        return false
    }

    fun copyNote(name: String): Boolean {
        if (notes.containsKey(name)) {
            notes["$name copy"] = notes[name].toString()
            println("Note copied successfully")
            return true
        }

        println("There's no note with name $name")
        return false
    }

    fun editNote(name: String, note: String): Boolean {
        if (notes.containsKey(name)) {
            notes[name] = note
            println("Note edited successfully")
            return true
        }

        println("There's no note with name $name")
        return false
    }

    fun printAllNotes() {
        for (key in notes.keys) {
            println("$key: ${notes[key]}")
        }
    }

    fun printNote(name: String): Boolean {
        if (notes.containsKey(name)) {
            println("$name: ${notes[name]}")
            return true
        }

        println("There's no note with name $name")
        return false
    }

    override fun toString(): String {
        val trace = Thread.currentThread().stackTrace

        var result = "$name\n"

        if (trace[4].toString() == "Archives.printArchive(Archives.kt:89)") {
            if (notes.isNotEmpty()) {
                result += "{\n"
                for (note in notes) {
                    result += "${note.key}: ${note.value},\n"
                }
                result += "}\n"
            } else {
                result += "Empty\n"
            }

            return result
        }
//        println(trace[4])

        if (notes.isEmpty()) {
            return name
        }


        for (note in notes) {
            result += "    ${note.key}\n"
        }

        return result
    }
}