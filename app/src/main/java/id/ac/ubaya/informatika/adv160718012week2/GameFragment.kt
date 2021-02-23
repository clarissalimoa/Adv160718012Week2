package id.ac.ubaya.informatika.adv160718012week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {
    private var score = 0
    private var num1 = 0
    private var num2 = 0
    private var lives = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if(arguments!=null){
        arguments?.let{
            var playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurnNow.text = "$playerName's Turn"
        }

            generateQuestion()

            btAnswer.setOnClickListener{
//                while(lives>0) {
                    val answer = txtAnswer.text.toString().toInt()
//                    txtCoba.text = txtAnswer.text.toString()
                    if (answer == addition()) {
                        score++
                        txtAnswer.text?.clear()
                        generateQuestion()
                    } else {
                        lives--
                    }
                    if (lives == 0) {
                        val action = GameFragmentDirections.actionResultFragment(score)
                        Navigation.findNavController(it).navigate(action)
                    }
//                }
            }


    }

    fun generateQuestion(){
        num1 = (1..20).random()
        num2 = (1..20).random()
        txtQuestion.text =  "$num1 + $num2"
    }

    fun addition():Int{
        return num1 + num2
    }

    fun addition(x:Int, y:Int):Int{
        return x+y
    }


}