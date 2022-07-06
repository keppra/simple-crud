package com.simplecrud.client.router

import androidx.appcompat.app.AppCompatActivity
import com.simplecrud.base.navigation.NavigationActivityStack
import com.simplecrud.base.presentation.BaseNavigation
import com.simplecrud.client.presentation.views.MainActivity

class Router(
    private val context: AppCompatActivity,
    private val navigationStack: NavigationActivityStack
):  BaseNavigation
{

    // Variables //

    private val fragmentManager = context.supportFragmentManager

    // Base methods //

    override fun goBack() {
        if (!isRootFragment()) {
            fragmentManager.popBackStack()
        } else {
            context.finish()
        }
    }

    override fun backToMain() {
        val stackList = navigationStack.getActivityStack()
        val mainActivityPosition = stackList.indexOfFirst { it is MainActivity }
        if (mainActivityPosition != -1) {
            val mainActivityNextPosition = mainActivityPosition + 1
            if ( navigationStack.getActivityStack().size > mainActivityNextPosition) {
                for (i in stackList.size - 1 downTo mainActivityNextPosition) {
                    navigationStack.finishActivityFromStack(stackList[i])
                    navigationStack.deleteActivityFromStack(stackList[i])
                }
            }
        }
    }

    override fun <T> goBackTo(activityClass: Class<T>) {
        TODO("Not yet implemented")
    }

    // Features methods //

    // Private methods //

    private fun isRootFragment() =
        fragmentManager.fragments.size in 0..1

}