package entities

import com.msp.proyectomoises.R


class Learners {
     val  listLearners = ArrayList<Learner>()

    init {
        val learner1 = Learner(R.drawable.ecoembes_1, "https://www.ecoembes.com/es/reduce-reutiliza-y-recicla/que-tirar-en-cada-contenedor")
        val learner2 = Learner(R.drawable.ecoembes_2, "https://www.ecoembes.com/es/reduce-reutiliza-y-recicla/que-se-puede-reciclar")
        val learner3 = Learner(R.drawable.conciencia_eco, "https://www.concienciaeco.com/")
        val learner4 = Learner(R.drawable.reciclame, "https://www.reciclame.info/")
        val learner5 = Learner(R.drawable.terrarecycle, "https://www.terracycle.com/es-ES/")

        listLearners.add(learner1)
        listLearners.add(learner2)
        listLearners.add(learner3)
        listLearners.add(learner4)
        listLearners.add(learner5)

    }


}