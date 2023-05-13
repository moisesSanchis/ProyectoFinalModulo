package adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.msp.proyectomoises.R
import entities.Learner

class ViewHolderData(itemView:View):RecyclerView.ViewHolder(itemView) {

    //Obtenemos referencias a los componentes visuales.
    private val image:ImageView = itemView.findViewById(R.id.ivLearnerRecycler)
    private val text:TextView = itemView.findViewById(R.id.tvLearnerRecycler)
    fun translateAssign(learner: Learner){

       /* image.setImageResource(learner.getImage())
        text.setText(learner.get)*/
    }
}