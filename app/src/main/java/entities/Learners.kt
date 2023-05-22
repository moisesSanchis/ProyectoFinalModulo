package entities

import com.msp.proyectomoises.R



class Learners {
    companion object{
        val learnersList = listOf<Learner>(
            Learner("Que tirar en cada contenedor",R.drawable.ecoembes_1, "https://www.ecoembes.com/es/reduce-reutiliza-y-recicla/que-tirar-en-cada-contenedor"),
            Learner("Que se puede reciclar",R.drawable.ecoembes_2, "https://www.ecoembes.com/es/reduce-reutiliza-y-recicla/que-se-puede-reciclar"),
            Learner("Consciencia Eco",R.drawable.consciencia, "https://www.concienciaeco.com/"),
            Learner("Reciclame",R.drawable.reciclame, "https://www.reciclame.info/"),
            Learner("Terrarecycle",R.drawable.terracycle, "https://www.terracycle.com/es-ES/")


        )
    }




}