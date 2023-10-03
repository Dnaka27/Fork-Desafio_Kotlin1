class Usuario(val id: Int, var nome: String, var email: String)

enum class Nivel {
    BASICO,
    INTERMEDIARIO,
    AVANCADO
}

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {
    val total_duracao = conteudos.sumOf { it.duracao }

    val nivel =
            when {
                total_duracao > 200 -> Nivel.AVANCADO
                total_duracao > 100 -> Nivel.INTERMEDIARIO
                else -> Nivel.BASICO
            }
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        println("--- Usuario inscrito com sucesso ---")
        println("Usuario: ${usuario.nome}")
    }

    fun descricao(){
        println("-------- Descricão de curso --------")
        println("Nome: $nome")
        println("Carga horaria: $total_duracao horas")
        println("Nivel: $nivel")
        println("Alunos inscritos: ${inscritos.size}")
        for (aluno in inscritos) {
            println("- ${aluno.id}: ${aluno.nome}")
        }
    }
}

fun main() {
    var user1 = Usuario(1, "Elidiana", "elidianaexemplo@gmail.com")
    var user2 = Usuario(2, "Falvo", "falvoexemplo@gmail.com")

    var logica = ConteudoEducacional("Logica de programação", 40)
    var oop = ConteudoEducacional("Programação orientada a objetos", 40)
	var algoritmos = ConteudoEducacional("Algoritmos avançados", 60)

    var python = Formacao("Curso Python básico", listOf(logica, oop))
	var kotlin = Formacao("Curso Kotlin e Java", listOf(logica, oop, algoritmos))
	
	python.matricular(user1)
    kotlin.matricular(user2)

    python.descricao()
    kotlin.descricao()
}
