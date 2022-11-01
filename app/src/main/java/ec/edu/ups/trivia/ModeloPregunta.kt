package ec.edu.ups.trivia

data class ModeloPregunta(
    val imagen:Int,val question: String, val opcion1: String,
    val opcion2: String, val opcion3: String, val opcion4: String,
    val answer: String
)